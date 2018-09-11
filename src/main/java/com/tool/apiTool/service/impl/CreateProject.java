package com.tool.apiTool.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.tool.apiTool.common.CommonUtil;
import com.tool.apiTool.common.Constant;
import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.service.IComponentService;
import com.tool.apiTool.service.ICreateService;
/**
 * The CreateProject is a main service class which will further call helper services to generate different components
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@Service
public class CreateProject implements ICreateService {
	
	@Autowired
    private IComponentService createControllerService;

    @Autowired
    private IComponentService createService;
    
    @Autowired
    private IComponentService createDaoService;
    
    @Autowired
    private IComponentService createEntityService;
    
    
    
	@Override
	public String createData(EntityDetail entityDetail) {
		String field=entityDetail.getName();
		entityDetail.setcName(field.substring(0, 1).toUpperCase() + field.substring(1));
		entityDetail.setsName(field.substring(0, 1).toLowerCase() + field.substring(1));
		entityDetail.setParentFolder(entityDetail.getName()+"_"+CommonUtil.generateRandomString(new Random(), Constant.RANDOM_STRING_SIZE));
		File parent   = new File(Constant.UPLOADED_PATH+entityDetail.getParentFolder()+"/"+ entityDetail.getPath());
		if(!parent.exists()){
			parent.mkdirs();
		}
		
		    try {
		    	createEntityService.createComponent(entityDetail);
		    	createDaoService.createComponent(entityDetail);
		    	createService.createComponent(entityDetail);
		    	createControllerService.createComponent(entityDetail);
		    	
		    	File tempFile=new File(Constant.UPLOADED_PATH+entityDetail.getParentFolder());
		    	String zipPath= Constant.UPLOADED_PATH+entityDetail.getParentFolder()+".zip";
		    	zipDirectory(tempFile,zipPath);
		    	FileSystemUtils.deleteRecursively(tempFile);
		    	return zipPath;
			} catch (IOException e) {
				e.printStackTrace();
				 return e.getMessage();
			}
		    
	}
	
	
	
	
	
	  private void zipDirectory(File dir, String zipDirName) {
	        try {
	        	 List<String> filesListInDir = new ArrayList<String>();
	            populateFilesList(dir,filesListInDir);
	            //now zip files one by one
	            //create ZipOutputStream to write to the zip file
	            FileOutputStream fos = new FileOutputStream(zipDirName);
	            ZipOutputStream zos = new ZipOutputStream(fos);
	            for(String filePath : filesListInDir){
	                System.out.println("Zipping "+filePath);
	                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
	                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
	                zos.putNextEntry(ze);
	                //read the file and write to ZipOutputStream
	                FileInputStream fis = new FileInputStream(filePath);
	                byte[] buffer = new byte[1024];
	                int len;
	                while ((len = fis.read(buffer)) > 0) {
	                    zos.write(buffer, 0, len);
	                }
	                zos.closeEntry();
	                fis.close();
	            }
	            zos.close();
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  private void populateFilesList(File dir,List<String> filesListInDir) throws IOException {
	        File[] files = dir.listFiles();
	        for(File file : files){
	            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
	            else populateFilesList(file,filesListInDir);
	        }
	    }
	
}
