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
import com.tool.apiTool.common.Constant.ID_DETAIL;
import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.domain.FieldData;
import com.tool.apiTool.service.IComponentService;
/**
 * The CreateEntityService is a helper service class which will generate Domain class
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Service
public class CreateEntityService implements IComponentService{

	@Override
	public void createComponent(EntityDetail entityDetail) throws IOException {
		File sourceFile   = new File(CommonUtil.getBasePath(entityDetail)+Constant.ENTITY_DIR+"/"+entityDetail.getcName()+".java");
		File parent=sourceFile.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		sourceFile.createNewFile();
		 FileWriter  writer = new FileWriter(sourceFile);
		 String packageName=entityDetail.getPackageName()+"."+Constant.ENTITY_DIR;
		 
		 Map<String, String> params = new HashMap<>();
			params.put("entityName", entityDetail.getcName());
			params.put("packageName", "package "+packageName+"; \n");
			params.put("importStmt", importForEntity(entityDetail));
			params.put("methods", createGetSet(entityDetail));
			params.put("fields", createFields(entityDetail));
			
			StringSubstitutor sub = new StringSubstitutor(params);
			 writer.write(sub.replace(Constant.ENTITY_CLASS));
			 writer.close();
			 
			 
		 writer.close();
		
	}
	
	private String importForFeilds(EntityDetail entityDetail){
		if(entityDetail.getFields().isEmpty()){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		
		for(FieldData feild: entityDetail.getFields()){
			if(Constant.IMPORT_MAP.containsKey(feild.getType())){
				sb.append("import "+Constant.IMPORT_MAP.get(feild.getType())+"\n");
			}
			if(feild.isPk()){
				sb.append("import "+Constant.IMPORT_MAP.get(ID_DETAIL.Id.getValue())+"\n");
				sb.append("import "+Constant.IMPORT_MAP.get(ID_DETAIL.GeneratedValue.getValue())+"\n");
				sb.append("import "+Constant.IMPORT_MAP.get(ID_DETAIL.GenerationType.getValue())+"\n");
			}
			
			/**********************Creating fields*****************************/
			
			String field=feild.getName();
			field = field.substring(0, 1).toUpperCase() + field.substring(1);
		}
		
		return sb.toString();
	}

	
	
	private String importForEntity(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		sb.append(importForFeilds(entityDetail));
		sb.append("import "+Constant.IMPORT_MAP.get("Entity")+"\n");
		return sb.toString();
	}
	
	
	private String createFields(EntityDetail entityDetail){
			StringBuffer sb=new StringBuffer();
		
		for(FieldData feild: entityDetail.getFields()){
			String field=feild.getName();
			field = field.substring(0, 1).toLowerCase() + field.substring(1);
			if(feild.isPk()){
				sb.append("\t @Id \n");
				sb.append("\t @GeneratedValue(strategy = GenerationType.AUTO) \n");
			}
			sb.append("\t private " + feild.getType()+" " + feild.getName()+"; \n");
			
		}
		return sb.toString();
	}
	private String createGetSet(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		
		for(FieldData feild: entityDetail.getFields()){
			String fieldName=feild.getName();
			String smallName=fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
			fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			
			sb.append(MessageFormat.format(Constant.GET_METHOD,feild.getType(),fieldName,smallName));
			
			sb.append(MessageFormat.format(Constant.SET_METHOD,fieldName,feild.getType(),smallName));
		}
		return sb.toString();
	}
}
