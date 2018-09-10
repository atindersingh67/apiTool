package com.tool.apiTool.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Service;

import com.tool.apiTool.common.CommonUtil;
import com.tool.apiTool.common.Constant;
import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.service.IComponentService;
/**
 * The CreateService is a helper service class which will generate Service interface and its implementation class
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Service
public class CreateService implements IComponentService{

	@Override
	public void createComponent(EntityDetail entityDetail) throws IOException {
		createServiceInterFace(entityDetail);
		createServiceClass(entityDetail);
		
	}
	
	private void createServiceClass(EntityDetail entityDetail) throws IOException{
		String packageName=entityDetail.getPackageName()+"."+Constant.SERVICE_DIR;
		File sourceFile   = new File(CommonUtil.getBasePath(entityDetail)+Constant.SERVICE_DIR+"/"+MessageFormat.format(Constant.SERVICE_NAME, entityDetail.getcName()));
		File parent=sourceFile.getParentFile();
		if(!parent.exists()){ 
			parent.mkdirs();
		}
		sourceFile.createNewFile();
		 FileWriter  writer = new FileWriter(sourceFile);
		 String daoName=MessageFormat.format(Constant.DAO_CLASS_NAME_ONLY, entityDetail.getcName());
			String daoObj = daoName.substring(0, 1).toLowerCase() + daoName.substring(1);
		 Map<String, String> params = new HashMap<>();
			params.put("entityName", entityDetail.getcName());
			params.put("packageName", "package "+packageName+"; \n");
			params.put("importStmt", importForServiceClass(entityDetail));
			params.put("methods", methodForServiceClass(entityDetail));
			params.put("daoObj",  daoObj);
			params.put("daoName",  daoName);
			
			StringSubstitutor sub = new StringSubstitutor(params);
			 writer.write(sub.replace(Constant.SERVICE_CLASS));
			 writer.close();
	}
	
	private String importForServiceClass(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		sb.append(importforServiceInterFace(entityDetail));
		sb.append("import " + Constant.IMPORT_MAP.get("autoWired")+"\n");
		sb.append("import " + Constant.IMPORT_MAP.get("service_annotation")+"\n");
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.DAO_DIR+"."+MessageFormat.format(Constant.DAO_CLASS_NAME_ONLY, entityDetail.getcName())  +";\n");
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.SERVICE_DIR+"."+Constant.SERVICE_INTERFACE_DIR+"."+MessageFormat.format(Constant.SERVICE_CLASS_NAME_ONLY, entityDetail.getcName())  +";\n");
		
		return sb.toString();
	}
	
	private String methodForServiceClass(EntityDetail entityDetail){
		String daoName=MessageFormat.format(Constant.DAO_CLASS_NAME_ONLY, entityDetail.getcName());
		daoName = daoName.substring(0, 1).toLowerCase() + daoName.substring(1);
		Map<String, String> params = new HashMap<>();
		params.put("entityName", entityDetail.getcName());
		params.put("idType",  CommonUtil.getIdType(entityDetail));
		params.put("daoObj",  daoName);
		StringSubstitutor sub = new StringSubstitutor(params);
		 
		return sub.replace(Constant.SERVICE_CLASS_METHODS);
	}

	
	private void createServiceInterFace(EntityDetail entityDetail) throws IOException{
		String packageName=entityDetail.getPackageName()+"."+Constant.SERVICE_DIR+'.'+Constant.SERVICE_INTERFACE_DIR;
		File sourceFile   = new File(CommonUtil.getBasePath(entityDetail)+Constant.SERVICE_DIR+"/"+Constant.SERVICE_INTERFACE_DIR+"/"+MessageFormat.format(Constant.SERVICE_INTERFACE_NAME, entityDetail.getcName()));
		File parent=sourceFile.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		
		sourceFile.createNewFile();
		 FileWriter  writer = new FileWriter(sourceFile);
			Map<String, String> params = new HashMap<>();
			params.put("entityName", entityDetail.getcName());
			params.put("packageName", "package "+packageName+"; \n");
			params.put("importStmt", importforServiceInterFace(entityDetail));
			params.put("methods", methodsForServiceInterFace(entityDetail));
			
			StringSubstitutor sub = new StringSubstitutor(params);
			 writer.write(sub.replace(Constant.SERVICE_INTERFACE));
		/* writer.write(MessageFormat.format(Constant.SERVICE_INTERFACE, "package "+packageName+"; \n",
				 importforServiceInterFace(entityDetail),
				 entityDetail.getcName(),
				 methodsForServiceInterFace(entityDetail)
				 ));*/
		 writer.close();
		
	}
	
	public String methodsForServiceInterFace(EntityDetail entityDetail){
		
		Map<String, String> params = new HashMap<>();
		params.put("entityName", entityDetail.getcName());
		params.put("idType",  CommonUtil.getIdType(entityDetail));
		StringSubstitutor sub = new StringSubstitutor(params);
		return sub.replace(Constant.SERVICE_INTERFACE_METHODS);
	}
	public String importforServiceInterFace(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.ENTITY_DIR+"."+entityDetail.getcName()+"; \n");
		sb.append("import " + Constant.IMPORT_MAP.get("util")+"\n");
		return sb.toString();
	}
}
