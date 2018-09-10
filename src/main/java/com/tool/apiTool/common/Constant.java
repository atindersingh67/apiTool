package com.tool.apiTool.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;


public class Constant {
	 /*@Value("${uploadfolder}")*/
	public static String UPLOADED_PATH="upload/";
	
	public static final String DEFAULT_API_PATH="api";
	public static final String ENTITY_DIR="domain";
	
	public static final String DAO_DIR="dao";
	public static final String SERVICE_INTERFACE_DIR="inter";
	
	public static final String SERVICE_DIR="service";
	public static final String CONTROLLER_DIR="controller";
	
	public static final int RANDOM_STRING_SIZE=4;
	
	public static final Map<String, String> IMPORT_MAP = createMap();
    private static Map<String, String> createMap()
    {
        Map<String,String> myMap = new HashMap<String,String>();
        myMap.put("Date",  "java.util.Date;");
        myMap.put("BigDecimal",  "java.math.BigDecimal;");
        myMap.put("List",  "java.util.List;");
        myMap.put("Entity",  "javax.persistence.Entity;");
        myMap.put(ID_DETAIL.GeneratedValue.getValue(),  "javax.persistence.GeneratedValue;");
        myMap.put(ID_DETAIL.GenerationType.getValue(),  "javax.persistence.GenerationType;");
        myMap.put(ID_DETAIL.Id.getValue(),  "javax.persistence.Id;");
        myMap.put("DaoCrud",  "org.springframework.data.repository.CrudRepository;");
        myMap.put("util",  "java.util.*;");
        myMap.put("autoWired",  "org.springframework.beans.factory.annotation.Autowired;");
        myMap.put("controller_annotation",  "org.springframework.web.bind.annotation.*;");
        myMap.put("service_annotation",  "org.springframework.stereotype.Service;");
        return myMap;
    }

    
    public static String GET_METHOD="\t public {0} get{1}()'{'\n \t \t return {2};\n \t '}' \n";
    
    public static String SET_METHOD="\t public void set{0}({1} {2} )'{'\n \t \t  this.{2} =  {2};\n \t '}' \n";
    
    public static String DAO_CLASS_NAME="{0}Dao.java";
    
    public static String DAO_CLASS_NAME_ONLY="{0}Dao";
    
    public static String JPA_DAO="{0} \n {1} public interface {2}Dao extends CrudRepository<{3}, {4}>'{' \n '}'";
    
    public static String SERVICE_INTERFACE_NAME="{0}Service.java";
    
    public static String SERVICE_CLASS_NAME_ONLY="{0}Service";
    
    
    public static String ENTITY_CLASS = "${packageName} \n ${importStmt} \n @Entity \n public class ${entityName} {\n \n ${fields} \n \n  ${methods} } \n";
    
    public static String SERVICE_INTERFACE = "${packageName} \n ${importStmt} \n public interface ${entityName}Service {\n \n ${methods} } \n";
    
    public static String SERVICE_INTERFACE_METHODS = "\t List<${entityName}> getAll${entityName}(); \n \n"
    											+ "\t ${entityName} save${entityName}(${entityName} obj); \n \n"
    											+ "\t Optional<${entityName}> get${entityName}ById(${idType} id);\n \n"
    											+ "\t void delete${entityName}(${idType} id); \n ";
    
    
    public static String SERVICE_NAME="{0}ServiceImpl.java";
    
    public static String CONTROLLER_NAME="{0}Controller";
    
    public static String SERVICE_CLASS="${packageName} \n "
    									+ "${importStmt} \n "
    									+ "@Service \n"
    									+ "public class ${entityName}ServiceImpl implements ${entityName}Service {\n \n "
    									+ "@Autowired \n ${daoName} ${daoObj}; \n"
    									+ "${methods} "
    									+ "} \n";
    
    public static String SERVICE_CLASS_METHODS = "\t public List<${entityName}> getAll${entityName}(){\n \t\t return (List<${entityName}>) ${daoObj}.findAll();\n \t} \n \n"
    													+ "\t public ${entityName} save${entityName}(${entityName} obj) {\n \t\t return ${daoObj}.save(obj); \n\t} \n \n "
    													+ "\t public Optional<${entityName}> get${entityName}ById(${idType} id) {\n \t\t return ${daoObj}.findById(id); \n \t} \n \n "
    													+ "\t public void delete${entityName}(${idType} id) {\n \t\t ${daoObj}.deleteById(id); \n \t} \n \n ";
		
   
    public static String CONTROLLER_CLASS="${packageName} \n "
			+ "${importStmt} \n "
			+ "@RestController\n"
			+ "@RequestMapping(\"${apiPath}\")\n"
			+ "public class ${entityName}Controller {\n \n "
			+ "@Autowired \n ${serviceName} service;\n"
			+ "${methods} "
			+ "} \n";
    
    public static String CONTROLLER_CLASS_METHODS="\n \t @GetMapping \n \t public List<${entityName}> get${entityName}() { \n "
    												+ "\t \t return service.getAll${entityName}(); \n \t } \n \n "
    												+ "\t @PostMapping \n \t public ${entityName} save${entityName}( @RequestBody ${entityName} obj) {\n"
    												+ "\t \t return service.save${entityName}(obj); \n\t } \n\n"
    												+ "\t @GetMapping(value = \"/{id}\") \n"
    												+ "\t public Optional<${entityName}> get${entityName}ById(@PathVariable ${idType} id) {\n"
    												+ "\t \t return service.get${entityName}ById(id); \n \t } \n\n"
    												+ "\t @PutMapping \n"
    												+ "\t public ${entityName} update${entityName}( @RequestBody ${entityName} obj) {\n"
    												+ "\t \t return service.save${entityName}(obj); \n\t } \n\n"
    												+ "\t @DeleteMapping\n"
    												+ "\t public void delete${entityName}(@PathVariable ${idType} id) { \n"
    												+ "\t \t service.delete${entityName}(id); \n\t } \n\n" ;
    public enum ID_DETAIL {
    	Id("Id"), GeneratedValue("GeneratedValue"), GenerationType("GenerationType");

		private final String value;

		ID_DETAIL(final String newValue) {
			value = newValue;
		}

		public String getValue() {
			return value;
		}
	}
}
