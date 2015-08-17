package org.atomspace.camel.component.tinkerforge.generator.model;

import org.atomspace.camel.component.tinkerforge.generator.databind.DocDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = DocDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {
	
	public String type;
	public String de;
	public String en;
	
}


