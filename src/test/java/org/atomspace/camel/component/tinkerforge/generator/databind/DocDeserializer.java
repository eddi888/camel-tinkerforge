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
package org.atomspace.camel.component.tinkerforge.generator.databind;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.atomspace.camel.component.tinkerforge.generator.model.Doc;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class DocDeserializer  extends JsonDeserializer<Doc>{

    @Override
    public Doc deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode jsonNode = oc.readTree(jp);
        Doc doc = new Doc();
        
        for (JsonNode jsonDoc : jsonNode) {

        	if(jsonDoc.isTextual()){
        		doc.type=jsonDoc.textValue();
        		
        	}else if(jsonDoc.isObject()){
        		Iterator<Entry<String, JsonNode>> fields = jsonDoc.fields();
        		while(fields.hasNext()){
        			Entry<String, JsonNode> next = fields.next();
            		if(next.getKey().equals("de")){
        				doc.de=next.getValue().textValue();
        			}else if(next.getKey().equals("en")){
        				doc.en=next.getValue().textValue();	
        			}
        		}
        	}        
        }
        
        return doc;
    }

}
