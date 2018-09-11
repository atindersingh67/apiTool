package com.tool.apiTool.domain;

import java.io.Serializable;
import java.util.List;

import com.tool.apiTool.common.Constant;
/**
 * The EntityDetail is domain class
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
public class EntityDetail implements Serializable{
	private String name;
	private String packageName;
	private List<FieldData> fields;
	private String cName; //Name With First Caps Letter
	
	private String sName; //Name With First small Letter
	
	private String apiPath;
	
	private String parentFolder;
	
	private String outputPath;
	
	
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
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/**
	 * @return the fields
	 */
	public List<FieldData> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<FieldData> fields) {
		this.fields = fields;
	}
	
	
	public String getPath(){
		return this.packageName.replaceAll("\\.", "/")+"/";
	}
	/**
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}
	/**
	 * @param cName the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}
	/**
	 * @return the sName
	 */
	public String getsName() {
		return sName;
	}
	/**
	 * @param sName the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}
	/**
	 * @return the apiPath
	 */
	public String getApiPath() {
		if(apiPath ==null || apiPath.isEmpty()){
			return Constant.DEFAULT_API_PATH;
		}
		return apiPath;
	}
	/**
	 * @param apiPath the apiPath to set
	 */
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	/**
	 * @return the parentFolder
	 */
	public String getParentFolder() {
		return parentFolder;
	}
	/**
	 * @param parentFolder the parentFolder to set
	 */
	public void setParentFolder(String parentFolder) {
		this.parentFolder = parentFolder;
	}
	/**
	 * @return the outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}
	/**
	 * @param outputPath the outputPath to set
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public String getZipName(){
		return this.parentFolder+"zip";
	}
}
