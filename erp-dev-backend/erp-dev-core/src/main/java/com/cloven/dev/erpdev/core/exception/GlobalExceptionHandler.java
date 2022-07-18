package com.cloven.dev.erpdev.core.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.cloven.dev.erpdev.core.dto.ApiResponse;
import com.cloven.dev.erpdev.core.utils.CoreUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = CoreException.class)
	public ResponseEntity<ApiResponse> coreException(CoreException exception) {
		logger.error(exception.getMessage(), exception);
		ApiResponse response = new ApiResponse();
		response.setError(true);
		response.setMessage(exception.getMessage());
		response.setStatusCode(exception.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(exception.getErrorCode()));
	}

	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiResponse> fileSizeLimitExceededException(MaxUploadSizeExceededException exception) {
		logger.error(exception.getMessage(), exception);
		ApiResponse response = new ApiResponse();
		response.setError(true);
		response.setMessage("File size is exceeds (max size:5MB)");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiResponse> exception(Exception exception) {
		logger.error(exception.getMessage(), exception);
		ApiResponse response = new ApiResponse();
		response.setError(true);
		response.setMessage(exception.getMessage());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * method to send error response from filter
	 * 
	 * @param request
	 * @param response
	 * @param statusCode
	 * @param message
	 */
	public void sendErrorReponse(HttpServletRequest request, HttpServletResponse response, int statusCode,
			String message) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setError(true);
		apiResponse.setUri(request.getRequestURI());
		apiResponse.setMessage(message);
		apiResponse.setStatusCode(statusCode);

		try {
			response.reset();
			response.setStatus(statusCode);
			response.setContentType("application/json");
			response.getWriter().write(CoreUtil.convertObjectToJson(apiResponse));
		} catch (IOException e) {
			throw new CoreException("IO Exception");
		}
	}
}