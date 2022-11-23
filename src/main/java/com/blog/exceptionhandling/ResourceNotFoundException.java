package com.blog.exceptionhandling;

public class ResourceNotFoundException extends NullPointerException{
   
	String resourceType;
	String fieldName;
	long fieldValue;
	String value;
	public ResourceNotFoundException(String resourceType, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceType,fieldName,fieldValue));
		this.resourceType = resourceType;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceType, String fieldName, String value) {
		super(String.format("%s not found with %s : %s", resourceType,fieldName,value));
		this.resourceType = resourceType;
		this.fieldName = fieldName;
		this.value = value;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
}
