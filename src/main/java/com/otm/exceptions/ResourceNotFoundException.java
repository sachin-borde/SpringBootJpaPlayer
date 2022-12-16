package com.otm.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	String resourcename;
	String fieldname;
	long fieldValue;

	public ResourceNotFoundException(String resourcename, String fieldname, long fieldValue) {
		super(String.format("%s Not Found With %s : %s", resourcename, fieldname, fieldValue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldValue = fieldValue;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

}
