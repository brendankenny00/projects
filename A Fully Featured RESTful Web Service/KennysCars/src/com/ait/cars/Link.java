package com.ait.cars;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link {
	@XmlAttribute(name="rel")
	private String rel;
	
	@XmlAttribute(name="uri")
	private String uri;
	
	public String getRel(){
		return rel;
	}
	
	public void setRel(String aRel){
		this.rel=aRel;
	}
	
	public String getUri(){
		return uri;
	}
	
	public void setUri(String uri){
		this.uri=uri;
	}

}
