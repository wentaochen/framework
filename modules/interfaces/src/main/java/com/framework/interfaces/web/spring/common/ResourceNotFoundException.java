package com.framework.interfaces.web.spring.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8431882954457160795L;

	private Long resourceId;

	public ResourceNotFoundException(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getResourceId() {
		return resourceId;
	}

}