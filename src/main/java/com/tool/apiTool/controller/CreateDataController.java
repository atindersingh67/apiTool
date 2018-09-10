package com.tool.apiTool.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.apiTool.common.Constant;
import com.tool.apiTool.domain.EntityDetail;
import com.tool.apiTool.domain.FieldData;
import com.tool.apiTool.service.ICreateService;

/**
 * The CreateDataController is a rest controller which will generate classes 
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
@RestController
@RequestMapping("create")
public class CreateDataController {

	@Autowired
	ICreateService createService;

	@PostMapping
	public EntityDetail create1(@RequestBody EntityDetail e1) {
		e1.setOutputPath(createService.createData(e1));
		return e1;
	}

	@GetMapping("/download/{name}")
	public ResponseEntity<InputStreamResource> download(@PathVariable String name) {
		File file = new File(Constant.UPLOADED_PATH + name + ".zip");
		InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.APPLICATION_PDF).contentLength(file.length()).body(resource);
	}

}
