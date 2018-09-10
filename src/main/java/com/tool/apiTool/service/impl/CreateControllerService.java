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
 * The CreateControllerService is a helper service class which will generate controller
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Service
public class CreateControllerService implements IComponentService{

	@Override
	public void createComponent(EntityDetail entityDetail) throws IOException{
		String packageName=entityDetail.getPackageName()+"."+Constant.CONTROLLER_DIR;
		File sourceFile   = new File(CommonUtil.getBasePath(entityDetail)+Constant.CONTROLLER_DIR+"/"+MessageFormat.format(Constant.CONTROLLER_NAME, entityDetail.getcName())+".java");
		File parent=sourceFile.getParentFile();
		if(!parent.exists()){ 
			parent.mkdirs();
		}
		sourceFile.createNewFile();
		 FileWriter  writer = new FileWriter(sourceFile);
		 String service=MessageFormat.format(Constant.SERVICE_CLASS_NAME_ONLY, entityDetail.getcName());
		 Map<String, String> params = new HashMap<>();
		 params.put("entityName", entityDetail.getcName());
			params.put("packageName", "package "+packageName+"; \n");
			params.put("importStmt", importForControllerClass(entityDetail));
			params.put("methods", methodForController(entityDetail));
			params.put("serviceName",  service);
			params.put("apiPath",  entityDetail.getApiPath()+"/"+entityDetail.getsName());
			StringSubstitutor sub = new StringSubstitutor(params);
			 writer.write(sub.replace(Constant.CONTROLLER_CLASS));
			 writer.close();
		
	}
	private String methodForController(EntityDetail entityDetail){

		Map<String, String> params = new HashMap<>();
		params.put("entityName", entityDetail.getcName());
		params.put("idType",  CommonUtil.getIdType(entityDetail));
		StringSubstitutor sub = new StringSubstitutor(params);
		 
		return sub.replace(Constant.CONTROLLER_CLASS_METHODS);
	}
	
	
	private String importForControllerClass(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.ENTITY_DIR+"."+entityDetail.getcName()+"; \n");
		sb.append("import " + Constant.IMPORT_MAP.get("util")+"\n");
		sb.append("import " + Constant.IMPORT_MAP.get("autoWired")+"\n");
		sb.append("import " + Constant.IMPORT_MAP.get("controller_annotation")+"\n");
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.SERVICE_DIR+"."+Constant.SERVICE_INTERFACE_DIR+"."+MessageFormat.format(Constant.SERVICE_CLASS_NAME_ONLY, entityDetail.getcName())  +";\n");
		
		return sb.toString();
	}
}
