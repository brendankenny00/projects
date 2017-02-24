package com.ait.cars;


import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Cars {

    private int id;

    private String make;

    private String model;

    private String year;

    private String milleage;
    
    private Date nct;
    
    private String colour;

    private String engine_size;
    
    private String description;
    
    private String edit;
    
    private String picture;

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

	public Date getNCT() {
		return nct;
	}

	public void setNCT(Date nct) {
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
		

}

