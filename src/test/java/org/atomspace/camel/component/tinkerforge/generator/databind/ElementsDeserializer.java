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

import org.atomspace.camel.component.tinkerforge.generator.model.Element;
import org.atomspace.camel.component.tinkerforge.generator.model.Elements;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ElementsDeserializer  extends JsonDeserializer<Elements>{

    @Override
    public Elements deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode jsonNode = oc.readTree(jp);
        Elements elements = new Elements();
        
        for (JsonNode jsonElement : jsonNode) {
            Element element = new Element();
            
            element.name = jsonElement.get(0).asText();
            element.type = jsonElement.get(1).asText();
            element.num = jsonElement.get(2).asInt();
            element.inout = jsonElement.get(3).asText();
                        
            
            elements.add(element );
        }
        
        
        
        return elements;
    }

}
