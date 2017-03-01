package edu.asu.qstore4s.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

/**
 * 
 * @author Bhargav Desai
 *
 */

@Service
public interface IRepositoryManager {

    /**
     * The method call parser to parse xml; call storagemanager to store
     * relation and appellation event into the database and call converter to
     * convert list of the objects into the database.
     * 
     * @param xml
     * @param type
     * @return
     * @throws URISyntaxException
     * @throws ParserException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws InvalidDataException
     */
    public String processXMLandStoretoDb(String xml, String type) throws URISyntaxException, ParserException,
            IOException, ParseException, JSONException, InvalidDataException;

    /**
     * The method return abstract data for particular id from the databse
     * 
     * @param id
     * @param type
     * @return
     * @throws JSONException
     * @throws InvalidDataException
     */

    String getShallow(String id, String type) throws JSONException, InvalidDataException;

    /**
     * The method return full data for the perticular id from the database.
     * 
     * @param id
     * @param type
     * @return
     * @throws JSONException
     * @throws InvalidDataException
     */
    String getFull(String id, String type) throws JSONException, InvalidDataException;

    /**
     * 
     * @param xml
     * @param type
     * @return
     * @throws URISyntaxException
     * @throws ParserException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws InvalidDataException
     */

    String searchByAppellationId(String xml, String type) throws URISyntaxException, ParserException, IOException,
            ParseException, JSONException, InvalidDataException;

    /**
     * The method get search results from storemanager; convert in to xml using
     * converter and return to controller.
     * 
     * @param xml
     * @param type
     * @return
     * @throws URISyntaxException
     * @throws ParserException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws InvalidDataException
     */

    String search(String xml, String type) throws URISyntaxException, ParserException, IOException, ParseException,
            JSONException, InvalidDataException;

    /**
     * The method get list of relation and appellation node, covert into XML or
     * json using converter class and return to controller.
     * 
     * @param xml
     * @param accept
     * @return
     * @throws ParserException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ParseException
     * @throws InvalidDataException
     * @throws JSONException
     */

    public String getList(String xml, String accept) throws ParserException, IOException, URISyntaxException,
            ParseException, InvalidDataException, JSONException;

    /**
     * This method asynchronously executes the query that is passed and returns
     * the result
     * 
     * @param query
     * @param clazz
     * @param accept
     * @param queryID
     * @return
     * @throws JSONException
     */
    void executeQueryAsync(String query, Class<?> clazz, String accept, String queryID) throws JSONException;

    /**
     * This method executes the query that is passed and returns the result
     * 
     * @param query
     * @param clazz
     * @param accept
     * @return
     * @throws JSONException
     */
    String executeQuery(String query, Class<?> clazz, String accept) throws JSONException;
}
