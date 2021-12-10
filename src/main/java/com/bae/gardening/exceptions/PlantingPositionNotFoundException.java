package com.bae.gardening.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason="No planting position found like that")
public class PlantingPositionNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5229323665079953052L;

	public PlantingPositionNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlantingPositionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PlantingPositionNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PlantingPositionNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PlantingPositionNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
