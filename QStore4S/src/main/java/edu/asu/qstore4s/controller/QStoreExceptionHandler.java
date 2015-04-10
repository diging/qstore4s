package edu.asu.qstore4s.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

/**
 * 
 * @author Bhargav Desai
 *  This class is exception handler class which is catches  all exception thrown by controller.
 */

@ControllerAdvice
public class QStoreExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(QStoreExceptionHandler.class);

	/**
	 * This method handles ParserException which is caused by wrong format of XML.
	 * @param request
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ResponseBody
	@ExceptionHandler(ParserException.class)
	public String parseExcpetionInXML(HttpServletRequest request,
			HttpServletResponse response, ParserException e) {

		logger.error("Error in parsing XML.", e);
		response.setStatus(406);
		return "Error in parsing XML. Please provide valid XML";
	}

	/**
	 * This method handles IOExcption.
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ResponseBody
	@ExceptionHandler(IOException.class)
	public String IOExcpetionInXML(HttpServletResponse response, IOException e) {

		logger.error("There is problem with Database.", e);
		response.setStatus(403);
		return "There is problem with Database. Please try after some time";
	}

	/**
	 * The method handles ParseException which may occur if date format provided in XML is not correct.
	 * @param response
	 * @param e
	 * @return Error message
	 */
	@ResponseBody
	@ExceptionHandler(ParseException.class)
	public String parseExcpetionInXML(HttpServletResponse response,
			ParseException e) {

		logger.error("Date format in XML is not correct.", e);
		response.setStatus(406);
		return "Date format in XML is not correct.";
	}

	/**
	 * The method handles URISynetex Exception.
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ResponseBody
	@ExceptionHandler(URISyntaxException.class)
	public String URISyntexException(HttpServletResponse response,
			URISyntaxException e) {

		logger.error("There is problem with Database.", e);
		response.setStatus(403);
		return "There is problem with Database. Please try after some time";
	}

	/**
	 * The method handles JSON exception which may occur while converting XMl to JSON.
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ResponseBody
	@ExceptionHandler(JSONException.class)
	public String jsonException(HttpServletResponse response, JSONException e) {

		logger.error("Error in converting XML to JSON.", e);
		response.setStatus(500);
		return "Error in converting XML to JSON.";
	}

	/**
	 * The method handles connect exception in case of data base service is not running. 
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ExceptionHandler(ConnectException.class)
	public String connectException(HttpServletResponse response,
			ConnectException e) {

		logger.error("Database is not running.", e);
		response.setStatus(403);
		return "Database is not running. Please try after sometime.";
	}

	/**
	 * This method is called to handle user defined InvalidDataExcetion such as invalid data in xml.
	 * @param request
	 * @param response
	 * @param e
	 * @return Error message
	 */

	@ResponseBody
	@ExceptionHandler(InvalidDataException.class)
	public String InvalidDataException(HttpServletRequest request,
			HttpServletResponse response, Exception e) {

		logger.error(e.getMessage(), e);
		response.setStatus(406);
		return e.getMessage();
	}

	/**
	 * The method handles all exception which not handled by any of the above methods. 
	 * @param request
	 * @param response
	 * @param e
	 * @return Error message
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String exception(HttpServletRequest request,
			HttpServletResponse response, Exception e) {

		logger.error("OOPS!! Something went wrong.", e);
		response.setStatus(500);
		return "OOPS!! Something went wrong. Please try after sometime.";
	}

}
