/**
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.atomspace.camel.component.tinkerforge.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.atomspace.camel.component.tinkerforge.generator.model.Config;
import org.atomspace.camel.component.tinkerforge.generator.model.Element;
import org.atomspace.camel.component.tinkerforge.generator.model.Packet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Generate {
    
    private static final Logger LOG = Logger.getLogger(Generate.class.getName());
    private ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        System.out.println("GENERATE SOURCES");
        new Generate().generate();
    }
    
    public Generate() { }
    
    public void generate() throws JsonParseException, JsonMappingException, IOException {
        // INIT VELOCITY ENGINE
        VelocityEngine engine;
        Properties velocityProperties = new Properties();
        velocityProperties.setProperty(RuntimeConstants.RESOURCE_LOADER, "cloader");
        velocityProperties.setProperty("cloader.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityProperties.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, Log4JLogChute.class.getName());
        velocityProperties.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM + ".log4j.logger", LOG.getName());
        engine = new VelocityEngine(velocityProperties);
        engine.init();
        Template endpointTemplate = engine.getTemplate("endpoint.vm");
        Template consumerTemplate = engine.getTemplate("consumer.vm");
        Template consumerCallbackImplTemplate = engine.getTemplate("consumer_callback_impl.vm");
        Template producerTemplate = engine.getTemplate("producer.vm");
        Template cameldocTemplate = engine.getTemplate("cameldoc.vm");
        
        
        
        //createClassBundle("org.atomspace.camel.component.tinkerforge", new File("src/test/resources/config/binding/bricklet_io4_config.js"), endpointTemplate, consumerTemplate, producerTemplate, consumerCallbackImplTemplate, cameldocTemplate) ;
        File[] configFiles = new File("src/test/resources/config/binding").listFiles();
        for (File file : configFiles) {
            if(file.toString().endsWith("bricklet_ac_current_config.json") 
                    || file.toString().endsWith("bricklet_co2_config.json")
                    || file.toString().endsWith("bricklet_gas_detector_config.json")
                    || file.toString().endsWith("bricklet_heart_rate_config.json")
                    || file.toString().endsWith("bricklet_oled_128x64_config.json")
                    || file.toString().endsWith("bricklet_oled_64x48_config.json")
                    || file.toString().endsWith("bricklet_ozone_config.json")
                ){
                
            }else if(file.toString().endsWith("json")){
                createClassBundle("org.atomspace.camel.component.tinkerforge", file, endpointTemplate, consumerTemplate, producerTemplate, consumerCallbackImplTemplate, cameldocTemplate) ;
                System.out.println("GENERATE SOURCES for "+file.getAbsolutePath());
            }
        }
        
    }
    
    /**
     * Generate Class Bundle
     */
    private void createClassBundle(String namespace, File configFile, Template endpointTemplate,Template consumerTemplate,Template producerTemplate, Template consumerCallbackImplTemplate, Template cameldocTemplate) throws IOException {
    	InputStream inputStream2 = new FileInputStream(configFile);
        Config config = mapper.readValue(inputStream2 , Config.class);
        
        String subspace = config.name[0].toLowerCase();
        subspace = "device";
        new File("src/main/java/org/atomspace/camel/component/tinkerforge/"+subspace).mkdir();
        createEndpointClass(namespace+"."+subspace, config, endpointTemplate, new File("src/main/java/org/atomspace/camel/component/tinkerforge/"+subspace+"/"+config.name[0]+"Endpoint.java"));
        createConsumerClass(namespace+"."+subspace, config, consumerTemplate, new File("src/main/java/org/atomspace/camel/component/tinkerforge/"+subspace+"/"+config.name[0]+"Consumer.java"), consumerCallbackImplTemplate);
        createProducerClass(namespace+"."+subspace, config, producerTemplate, new File("src/main/java/org/atomspace/camel/component/tinkerforge/"+subspace+"/"+config.name[0]+"Producer.java"));
        createCamelDocument(namespace+"."+subspace, config, cameldocTemplate, new File("src/main/java/org/atomspace/camel/component/tinkerforge/"+subspace+"/"+config.name[0]+".md"));
        
    }
    
    /**
     * Generate Camel Documentation
     */
    private void createCamelDocument(String namespace, Config config, Template template, File file) throws IOException {

        // MAP TINKERFORGE-CONFIG WITH VELOCITY-CONTEXT 
        VelocityContext context = new VelocityContext();
        context.put("namespace", namespace);
        context.put("component", config.name[0]);
        context.put("config_category", config.category);
        
   
        
        StringBuffer parameters = new StringBuffer();
        StringBuffer functions = new StringBuffer();
        StringBuffer callbacks = new StringBuffer();

        Map<String, String> parameterSet = new HashMap<String, String>();
      
 
        for (Packet packet : config.packets) {
            
            if(packet.type.equals("function") 
                    && !packet.name[1].equals("get_response_expected") 
                    && !packet.name[1].equals("set_response_expected")
                    && !packet.name[1].equals("set_response_expected_all") 
                    && !packet.name[1].equals("get_api_version")){
            
                boolean out=false;
                boolean first=true;

                
                StringBuffer functionParameters = new StringBuffer();
                for (Element element : packet.elements) {
                    if(element.inout.equals("in")){
                        if(parameterSet.get(element.name)==null){
                            parameterSet.put(element.name, element.name);
                        } else {
                            for(int i=2; i<99;i++){
                                if(parameterSet.get(element.name+i)==null){
                                    element.name=element.name+i;
                                    parameterSet.put(element.name, element.name);
                                    break;
                                }else{
                                    
                                }
                            }
                        }
                        parameterSet.put(element.name, element.name);
                        parameters.append(String.format("| %20s | %10s |\n", camelCaseName(element.name), javaBigType(element.type, element.num)));
                        if(first==false) functionParameters.append(", ");
                        functionParameters.append(camelCaseName(element.name));
                        if(first==true) first=false;
                    }else if(element.inout.equals("out")){
                        out = true;
                    }
                }
                
                functions.append(String.format("| %20s | %40s |\n", packet.name[0].substring(0, 1).toLowerCase()+packet.name[0].substring(1), functionParameters));
            }else if(packet.type.equals("callback")) {

                    StringBuffer callbackHeaders = new StringBuffer("firedBy");
                    
                    for (Element element : packet.elements) {

                        if(element.inout.equals("out")){
                            callbackHeaders.append(", "+camelCaseName(element.name));
                        }
                    }
                    
                    callbacks.append(String.format("| %20s | %40s |\n", packet.name[0].substring(0, 1).toLowerCase()+packet.name[0].substring(1), callbackHeaders));
            }
            
        }
        context.put("parameters", parameters);
        context.put("functions", functions);
        
        context.put("callbacks", callbacks);
        
        // WRITE FILE
        BufferedWriter writer =  new BufferedWriter(new FileWriter(file));
        //StringWriter writer=new StringWriter();
        template.merge(context, writer);
        writer.close();
        //System.out.println(writer);
        
    }

    /**
     * Generate Producer Class
     */
    private void createProducerClass(String namespace, Config config, Template template, File file) throws IOException {
        // MAP TINKERFORGE-CONFIG WITH VELOCITY-CONTEXT 
        VelocityContext context = new VelocityContext();
        context.put("namespace", namespace);
        context.put("config_name_0", config.name[0]);
        context.put("config_category", config.category);
        
        BufferedWriter writer =  new BufferedWriter(new FileWriter(file)); 
        template.merge(context, writer);
        writer.close();
    }
    
    /**
     * Generate Consumer Class
     */
    private void createConsumerClass(String namespace, Config config, Template template, File file, Template subTemplate) throws IOException {
        // MAP TINKERFORGE-CONFIG WITH VELOCITY-CONTEXT 
        VelocityContext context = new VelocityContext();
        context.put("namespace", namespace);
        context.put("config_name_0", config.name[0]);
        context.put("config_category", config.category);
        
        String importListener = "";
        String implementsListener = null;
        String registerCallbackAll="";
        String registerCallbackIfs="";
        String callbackImpls="";
        for (Packet packet : config.packets) {

            if(packet.type.equals("callback")){
                importListener+= "\nimport com.tinkerforge."+config.category+config.name[0]+"."+packet.name[0]+"Listener;";
                
                if(implementsListener==null) implementsListener = "implements "+packet.name[0]+"Listener";
                else implementsListener+= ", "+packet.name[0]+"Listener";
                
                registerCallbackAll+= "device.add"+packet.name[0]+"Listener(this);\n            ";
                
                registerCallbackIfs+= "if(callback.equals(\""+packet.name[0]+"Listener\")) device.add"+packet.name[0]+"Listener(this);\n                ";
                
                callbackImpls+=createCallbackImpl(config, subTemplate, packet);
                        
            }
        }
        context.put("config_packet_implements_listener", implementsListener);
        context.put("config_packet_import_listener", importListener);
        context.put("config_packet_callback_all", registerCallbackAll);
        context.put("config_packet_callback_ifs", registerCallbackIfs);
        context.put("config_packet_callback_impls", callbackImpls);
        
        
        // 
        BufferedWriter writer =  new BufferedWriter(new FileWriter(file)); 
        template.merge(context, writer);
        writer.close();
    }

    /**
     * Generate Consumer Callback Impl Block
     */
    private String createCallbackImpl(Config config, Template subTemplate, Packet packet) {
        // FIND CALLBACK PARAMETERs
        String methodParameters = null;
        String headerValues = "";
        for (Element element : packet.elements) {
                        
            if(element.inout.equals("out")){
                // METHOD PARAMETERS
                if(methodParameters==null) methodParameters=javaType(element.type, element.num)+" "+element.name;
                else  methodParameters+=", "+javaType(element.type, element.num)+" "+element.name;

                // HEADER VALUES
                headerValues+="exchange.getIn().setHeader(\""+element.name+"\", "+element.name+");\n            ";
                
            }
        }
        
        
        VelocityContext subContext = new VelocityContext();       

        subContext.put("methodName", camelCaseName(packet.name[1])); 
        
        subContext.put("methodParameters", methodParameters);
        subContext.put("headerCallbackValue", config.category+config.name[0]+".CALLBACK_"+packet.name[1].toUpperCase());
        subContext.put("headerValues", headerValues);
        subContext.put("bodyCallbackValue", packet.name[1]);
        StringWriter writer = new StringWriter();
        subTemplate.merge(subContext, writer);
        String implMeth = writer.toString();
        return implMeth;
    }

    /**
     * Generate Endpoint Class
     */
    private void createEndpointClass(String namespace, Config config, Template template, File file) throws IOException {
        // MAP TINKERFORGE-CONFIG WITH VELOCITY-CONTEXT 
        VelocityContext context = new VelocityContext();
        context.put("namespace", namespace);
        context.put("config_name_0", config.name[0]);
        context.put("config_category", config.category);
        context.put("config_description", config.description.en);
        context.put("uriannotation", "@UriEndpoint(scheme = \"tinkerforgegen\", syntax = \"tinkerforgegen:[host[:port]/]"+config.name[0].toLowerCase()+"\", consumerClass = "+config.name[0]+"Consumer.class, label = \"iot\", title = \"Tinkerforge\")");

        StringBuffer parameternames = new StringBuffer();
        StringBuffer parameters = new StringBuffer();
        StringBuffer parameterGetteSetters = new StringBuffer();
        Map<String, String> parameterSet = new HashMap<String, String>();
        parameterSet.put("uid", "uid");	//Requird for endpoint super class
        StringBuffer callFunctions = new StringBuffer();
        // Find possible transfer parameters. 
        for (Packet packet : config.packets) {

            if(packet.type.equals("function") 
                    && !packet.name[1].equals("get_response_expected") 
                    && !packet.name[1].equals("set_response_expected")
                    && !packet.name[1].equals("set_response_expected_all") 
                    && !packet.name[1].equals("get_api_version")){
            
                boolean out=false;
                boolean first=true;
                String callFunctionPre = "            case \""+camelCaseName(packet.name[1])+"\":\n";
                String callFunction = "device."+camelCaseNameFirstGroupLowerCase(packet.name[1],packet.name[0])+"(";
                        
                for (Element element : packet.elements) {
                	
                    if(element.inout.equals("in")){
                    	if (parameterSet.get(element.name)==null) {
                            parameterSet.put(element.name, element.name);
                        } else {
                            for(int i=2; i<99;i++){
                                if(parameterSet.get(element.name+i)==null){
                                    element.name=element.name+i;
                                    parameterSet.put(element.name, element.name);
                                    break;
                                }else{
                                    
                                }
                            }
                        }
                        parameternames.append("    public static final String "+camelCaseName(element.name).toUpperCase()+"=\""+camelCaseName(element.name)+"\";\n");

                        parameters.append("    private "+javaBigType(element.type, element.num)+ " " +camelCaseName(element.name)+";\n");
                        
                        parameterGetteSetters.append("    /**\n");
                        parameterGetteSetters.append("     * "+packet.doc.en.replace("\n", "\n     * ")+"\n");
                        parameterGetteSetters.append("     */\n");
                        parameterGetteSetters.append("    public "+javaBigType(element.type, element.num)+ " " +camelCaseMethodeNameGet(element.name)+"(){\n");
                        parameterGetteSetters.append("        return "+camelCaseName(element.name)+";\n");
                        parameterGetteSetters.append("    }\n\n");
                        parameterGetteSetters.append("    public void "+camelCaseMethodeNameSet(element.name)+"("+javaBigType(element.type, element.num)+ " " +camelCaseName(element.name)+"){\n");
                        parameterGetteSetters.append("        this."+camelCaseName(element.name)+" = "+camelCaseName(element.name)+";\n");
                        parameterGetteSetters.append("    }\n\n");
                        
                        if(first){
                            callFunction += "\n                        getValue("+javaType(element.type, element.num)+".class, \"" +camelCaseName(element.name)+"\", m, "+camelCaseMethodeNameGet(element.name)+"())";
                            first=false;
                        }else{
                            callFunction += ",\n                        getValue("+javaType(element.type, element.num)+".class, \"" +camelCaseName(element.name)+"\", m, "+camelCaseMethodeNameGet(element.name)+"())";
                        }
                        
                    }else if(element.inout.equals("out")){
                        out = true;
                    }

                }
                if(first)callFunction+= ");\n";
                else callFunction+= "\n                    );\n";
                
                callFunction+= "                break;\n\n";
                
                if(out==true){ 
                    callFunction = callFunctionPre+ "                response = " + callFunction;
                }else{
                    callFunction = callFunctionPre+ "                " + callFunction;
                }
                
                callFunctions.append(callFunction);
            }
            
        }
        context.put("parameternames", parameternames);
        context.put("parameters", parameters);
        context.put("parameterGetteSetters", parameterGetteSetters);
        context.put("callFunctions", callFunctions);
        
        // WRITE FILE
        BufferedWriter writer =  new BufferedWriter(new FileWriter(file)); 
        template.merge(context, writer);
        writer.close();
    }
    
    /**
     * mapping to simple Java Type
     */
    private String javaType(String type, int num){
        if(num==1){
            if(type.equals("int8")) return "byte";
            if(type.equals("uint8")) return "short";
            if(type.equals("int16")) return "short";
            
            if(type.equals("uint16")) return "int";
            if(type.equals("int32")) return "int";
            if(type.equals("uint32")) return "long";
            if(type.equals("int64")) return "long";
            if(type.equals("uint64")) return "long";
            if(type.equals("float")) return "float";
            if(type.equals("bool")) return "boolean";
            if(type.equals("char")) return "char";
            if(type.equals("string")) return "String";
        }else if(num>1){
            if(type.equals("int8")) return "byte[]";
            if(type.equals("uint8")) return "short[]";
            if(type.equals("int16")) return "short[]";
            
            if(type.equals("uint16")) return "int[]";
            if(type.equals("int32")) return "int[]";
            if(type.equals("uint32")) return "long[]";
            if(type.equals("int64")) return "long[]";
            if(type.equals("uint64")) return "long[]";
            if(type.equals("float")) return "float[]";
            if(type.equals("bool")) return "boolean[]";
            if(type.equals("char")) return "char[]";
            if(type.equals("string")) return "String";
        }
        return "";
    }

    /**
     * mapping to big Java Type, they can be null
     */
    private String javaBigType(String type, int num){
        if(num==1){
            if(type.equals("int8")) return "Byte";
            if(type.equals("uint8")) return "Short";
            if(type.equals("int16")) return "Short";
            
            if(type.equals("uint16")) return "Integer";
            if(type.equals("int32")) return "Integer";
            if(type.equals("uint32")) return "Long";
            if(type.equals("int64")) return "Long";
            if(type.equals("uint64")) return "Long";
            if(type.equals("float")) return "Float";
            if(type.equals("bool")) return "Boolean";
            if(type.equals("char")) return "Character";
            if(type.equals("string")) return "String";
        }else if(num>1){
            /*if(type.equals("int8")) return "Byte[]";
            if(type.equals("uint8")) return "Short[]";
            if(type.equals("int16")) return "Short[]";
            
            if(type.equals("uint16")) return "Integer[]";
            if(type.equals("int32")) return "Integer[]";
            if(type.equals("uint32")) return "Long[]";
            if(type.equals("int64")) return "Long[]";
            if(type.equals("uint64")) return "Long[]";
            if(type.equals("float")) return "Float[]";
            if(type.equals("bool")) return "Boolean[]";
            if(type.equals("char")) return "Character[]";
            if(type.equals("string")) return "String";*/
            if(type.equals("int8")) return "byte[]";
            if(type.equals("uint8")) return "short[]";
            if(type.equals("int16")) return "short[]";
            
            if(type.equals("uint16")) return "int[]";
            if(type.equals("int32")) return "int[]";
            if(type.equals("uint32")) return "long[]";
            if(type.equals("int64")) return "long[]";
            if(type.equals("uint64")) return "long[]";
            if(type.equals("float")) return "float[]";
            if(type.equals("bool")) return "boolean[]";
            if(type.equals("char")) return "char[]";
            if(type.equals("string")) return "String";
        }
        return "";
    }
    
    /**
     * Convert 'response_expected' to 'responseExpected'
     * AND
     * Convert 'led_on' to 'ledOn'
     */
    private String camelCaseName(String name){
        String camelCaseName = null;
        String[] parts = name.split("_");
        for (String part : parts) {
            if(camelCaseName==null) camelCaseName=part.toLowerCase();
            else camelCaseName+=part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase();
        }
        return camelCaseName;
    }
    
    /**
     * Convert 'set_pwm_frequency, SetPWMFrequency' to 'setPWMFrequency'
     * AND
     * Convert 'is_led_on, IsLEDOn' to 'isLEDOn'
     * AND
     * Convert 'led_on, LEDOn' to 'ledOn'
     * 
     */
    private String camelCaseNameFirstGroupLowerCase(String name, String nameCC){
        
        boolean secoundCharUpperCase = (Character.isUpperCase(nameCC.charAt(1)));
        
        String camelCaseName = null;
        
        if(secoundCharUpperCase){
            String firstGroup = name.split("_")[0];
            camelCaseName = firstGroup.toLowerCase()+nameCC.substring(firstGroup.length());
        }else{
            camelCaseName = nameCC.substring(0, 1).toLowerCase()+nameCC.substring(1);
        }
        
        return camelCaseName;
    }
    
    
    /**
     * Convert 'response_expected' to 'ResponseExpected'
     */
    private String camelCaseNameFirstBig(String name){
        String camelCaseName = null;
        String[] parts = name.split("_");
        for (String part : parts) {
            if(camelCaseName==null) camelCaseName = part.substring(0, 1).toUpperCase() + part.substring(1);
            else camelCaseName+=part.substring(0, 1).toUpperCase() + part.substring(1);
        }
        return camelCaseName;
    }
    
    /**
     * Convert 'response_expected' to 'ResponseExpected'
     */
    private String camelCaseNameOnlyFirstSmall(String name){
        String camelCaseName = null;
        String[] parts = name.split("_");
        for (String part : parts) {
            if(camelCaseName==null) camelCaseName = part.substring(0, 1).toUpperCase() + part.substring(1);
            else camelCaseName+=part.substring(0, 1).toUpperCase() + part.substring(1);
        }
        return camelCaseName;
    }
    
    /**
     * Convert 'response_expected' to 'getResponseExpected'
     */
    private String camelCaseMethodeNameGet(String name){
        return "get"+camelCaseNameFirstBig(name);
    }
    
    /**
     * Convert 'response_expected' to 'setResponseExpected'
     */
    private String camelCaseMethodeNameSet(String name){
        return "set"+camelCaseNameFirstBig(name);
    }
    
}
