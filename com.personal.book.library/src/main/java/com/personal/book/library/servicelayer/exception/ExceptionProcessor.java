package com.personal.book.library.servicelayer.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.personal.book.library.servicelayer.model.ServiceExceptionDetails;


@EnableWebMvc
@ControllerAdvice(basePackages = {"com.personal.book.library"})
public class ExceptionProcessor {

	@ExceptionHandler(ServiceLayerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ServiceExceptionDetails processServiceLayerException(HttpServletRequest httpServletRequest, ServiceLayerException exception) {
        
		String errorMessage = exception.getMessage();
		String detailedErrorMessage = exception.getDetailedMessageDescription();
         
        String errorURL = httpServletRequest.getRequestURL().toString();
         
        return new ServiceExceptionDetails(errorMessage, detailedErrorMessage, errorURL);
    }
 
	@ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceExceptionDetails processDataAccessException(HttpServletRequest httpServletRequest, DataAccessException exception) {
        
		String detailedErrorMessage = exception.getMessage();
         
        String errorURL = httpServletRequest.getRequestURL().toString();
         
        return new ServiceExceptionDetails("", detailedErrorMessage, errorURL);
    }
 
	@ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceExceptionDetails processDataAccessException(HttpServletRequest httpServletRequest, SQLException exception) {
        
		String detailedErrorMessage = exception.getMessage();
         
        String errorURL = httpServletRequest.getRequestURL().toString();
         
        return new ServiceExceptionDetails("", detailedErrorMessage, errorURL);
    }
 
	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceExceptionDetails processMissingServletRequestParameterException(HttpServletRequest httpServletRequest, MissingServletRequestParameterException exception) {
		
		String missingParameterName = exception.getParameterName();
		String missingParameterType = exception.getParameterType();

		String errorMessage = "Missing parameter: [name: " + missingParameterName + ", type: " + missingParameterType + "]";
		
		String detailedErrorMessage = exception.getMessage();
         
        String errorURL = httpServletRequest.getRequestURL().toString();
         
        return new ServiceExceptionDetails(errorMessage, detailedErrorMessage, errorURL);
    }
	
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ServiceExceptionDetails processHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
    	
    	String detailedErrorMessage = exception.getMessage();

        return new ServiceExceptionDetails("", detailedErrorMessage, "");
    }

	
}
