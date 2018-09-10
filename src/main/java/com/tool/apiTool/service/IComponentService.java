package com.tool.apiTool.service;

import java.io.IOException;

import com.tool.apiTool.domain.EntityDetail;
/**
 * The IComponentService is an interface, all helper service will impliment this interface.
 *
 * @author Atinder
 * @version 1.0
 * @since 2018-09-10
 */
public interface IComponentService {
	void createComponent(EntityDetail entityDetail) throws IOException;
}
