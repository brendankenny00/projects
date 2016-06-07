package com.ait.cars;

//import java.util.Date;
import java.sql.Date;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlType(propOrder={"id","make","model","year","milleage","nct","colour","engine_size","ts","link"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="cars")
public class Cars {
	@XmlElement(required=true)
    private int id;
    private String make;
    private String model;
    private String year;
    private String milleage;
    private String nct;
    private String colour;
    private String engine_size;
    private String ts; 
    private List<Link> link;//new arraylist<>();
    
  
    
    public Cars(int id, String make, String model, String year, String milleage, String nct, String colour,
			String engine_size, String ts) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.milleage = milleage;
		this.nct = nct;
		this.colour = colour;
		this.engine_size = engine_size;
		this.ts = ts;
	}

	public Cars(){
    	
    }
    
	public String getTs() {
		return ts;
	}
	
	public void setTs(String ts) {
		this.ts = ts;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMilleage() {
		return milleage;
	}

	public void setMilleage(String milleage) {
		this.milleage = milleage;
	}

	public String getNct() {
		return nct;
	}

	public void setNct(String nct) {
		this.nct = nct;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public String getEngineSize() {
		return engine_size;
	}

	public void setEngineSize(String EngineSize) {
		this.engine_size = EngineSize;
	}

	
	
	public List<Link> getLink(){
		return link;
	}
	
	public void setLink(List<Link>link){
		this.link = link;
	}
		

}

