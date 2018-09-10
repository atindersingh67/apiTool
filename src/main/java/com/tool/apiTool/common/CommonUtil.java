package com.tool.apiTool.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Random;

import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.domain.FieldData;

public class CommonUtil {

	public static String getIdType(EntityDetail entityDetail){
		for(FieldData feild: entityDetail.getFields()){
			if(feild.isPk()){
				return feild.getType();
			}
		}
		return null;
	}
	
	
	public static String getBasePath(EntityDetail entityDetail){
		return Constant.UPLOADED_PATH+entityDetail.getParentFolder()+"/"+entityDetail.getPath();
	}
	
	public static String generateRandomString(Random random, int length){
        return random.ints(48,122)
                .filter(i-> (i<57 || i>65) && (i <90 || i>97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
	
	public static void deleteDirectoryStream(Path path) throws IOException {
		  Files.walk(path)
		    .sorted(Comparator.reverseOrder())
		    .map(Path::toFile)
		    .forEach(File::delete);
		}
}
