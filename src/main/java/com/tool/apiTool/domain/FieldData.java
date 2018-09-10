package com.tool.apiTool.domain;

import java.io.Serializable;
/**
 * The FieldData is domain class which will carry all fields details 
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
public class FieldData  implements Serializable{
	
	private String name;
	private String type;
	private boolean notNull;
	private boolean pk;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the notNull
	 */
	public boolean isNotNull() {
		return notNull;
	}
	/**
	 * @param notNull the notNull to set
	 */
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	/**
	 * @return the pk
	 */
	public boolean isPk() {
		return pk;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public FieldData(String name, String type, boolean notNull, boolean pk) {
		super();
		this.name = name;
		this.type = type;
		this.notNull = notNull;
		this.pk = pk;
	}
	
	public FieldData(){
		
	}

}
