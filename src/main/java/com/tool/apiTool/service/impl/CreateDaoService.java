package com.tool.apiTool.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.tool.apiTool.common.CommonUtil;
import com.tool.apiTool.common.Constant;
import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.service.IComponentService;
/**
 * The CreateDaoService is a helper service class which will generate DAO class
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Service
public class CreateDaoService implements IComponentService{

	@Override
	public void createComponent(EntityDetail entityDetail) throws IOException {
File sourceFile   = new File(CommonUtil.getBasePath(entityDetail)+Constant.DAO_DIR+"/"+MessageFormat.format(Constant.DAO_CLASS_NAME, entityDetail.getcName()));
		
		File parent=sourceFile.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		sourceFile.createNewFile();
		 FileWriter  writer = new FileWriter(sourceFile);
		 writer.write(MessageFormat.format(Constant.JPA_DAO, "package "+entityDetail.getPackageName()+"."+Constant.DAO_DIR+";\n",
				 importForDAO(entityDetail),
				 entityDetail.getcName(),
				 entityDetail.getcName(),
				CommonUtil.getIdType(entityDetail)
				 	)
		    );
		 writer.close();
		
	}
	
	private String importForDAO(EntityDetail entityDetail){
		StringBuffer sb=new StringBuffer();
		
		sb.append("import "+Constant.IMPORT_MAP.get("DaoCrud")+"\n");
		sb.append("import "+entityDetail.getPackageName()+"."+Constant.ENTITY_DIR+"."+entityDetail.getcName()+"; \n");
		return sb.toString();
	}

}
