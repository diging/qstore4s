package edu.asu.qstore4s.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.asu.qstore4s.async.manager.IAsyncQueryManager;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;
import edu.asu.qstore4s.message.Message;
import edu.asu.qstore4s.service.IRepositoryManager;

/**
 * Controller class to handle user requests.
 * 
 * @author Bhargav Desai.
 */
@Controller
public class QStore {

    private static final String RETURN_JSON = "application/json";

    private static final String XML = "application/xml";
    private static final String JSON = "application/json";

    private final String RELATION_EVENT = "relationevent";
    private final String APPELLATION_EVENT = "appellationevent";

    private final String RUNNING = "RUNNING";

    private final HashMap<String, Class<?>> classMap = new HashMap<String, Class<?>>() {
        {
            put(RELATION_EVENT, RelationEvent.class);
            put(APPELLATION_EVENT, AppellationEvent.class);
        }
    };

    @Autowired
    private IRepositoryManager repositorymanager;

    @Autowired
    private IAsyncQueryManager asyncQueryManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testStatus(ModelMap model) {
        return "login";
    }

    /**
     * User requests a login page
     * 
     * @return Redirected to the login page
     */

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    /**
     * A valid authenticated user is redirected to the home page.
     * 
     * @return Returned to the home page of Qstore.
     */

    @RequestMapping(value = "auth/welcome", method = RequestMethod.GET)
    public String validUserHandle(ModelMap model, Principal principal) {

        // Get the LDAP-authenticated userid
        String sUserId = principal.getName();
        model.addAttribute("username", sUserId);

        return "home";

    }

    /**
     * A authenticated user is logged out of the system.
     * 
     * @return Redirect to login page
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "login";

    }

    /**
     * Authentication failed. User credentials mismatch causes this request.
     * 
     * @return Redirected to login page
     */

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "login";

    }

    /**
     * The method parse given XML from the post request body and add relation
     * and appellation event into the database
     * 
     * @param request
     * @param response
     * @param xml
     * @param accept
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws ParserException
     * @throws JSONException
     * @throws URISyntaxException
     * @throws InvalidDataException
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processXML(HttpServletRequest request, HttpServletResponse response, @RequestBody String xml,
            @RequestHeader("Accept") String accept) throws ParserException, IOException, URISyntaxException,
                    ParseException, JSONException, InvalidDataException {

        if (xml.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("Please provide XML in body of the post request.").toString(accept);
        }

        String returnString = "";

        if (accept != null && accept.equals(RETURN_JSON)) {
            returnString = repositorymanager.processXMLandStoretoDb(xml, JSON);
        } else {
            returnString = repositorymanager.processXMLandStoretoDb(xml, XML);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(accept);
        return returnString;

    }

    /**
     * The method is called for get request by user to get information of single
     * relation or appellation event.
     * 
     * @param request
     * @param response
     * @param xml
     * @param accept
     * @param shallow
     * @param idString
     * @return String
     * @throws JSONException
     * @throws InvalidDataException
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getData(HttpServletRequest request, HttpServletResponse response,
            @RequestHeader("Accept") String accept, @RequestParam(value = "shallow", defaultValue = "") String shallow,
            @RequestParam(value = "id", defaultValue = "") String idString) throws JSONException, InvalidDataException {

        if (idString.equals("")) {
            throw new InvalidDataException("Please provide id.");
        }

        String trimid = idString.trim();
        String returnString = "";

        if (shallow != null && shallow.equals("true")) {
            returnString = repositorymanager.getShallow(trimid, accept);
        } else {
            returnString = repositorymanager.getFull(trimid, accept);

        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(accept);
        return returnString;

    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public String query(HttpServletRequest request, HttpServletResponse response,
            @RequestHeader("Accept") String accept, @RequestBody String query, @RequestParam("class") String clas,
            @RequestParam("queryID") String queryID) throws JSONException, InterruptedException, ExecutionException {

        Boolean isQueryRunning = asyncQueryManager.isQueryRunning(queryID);

        if (isQueryRunning != null && isQueryRunning) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(accept);
            return new Message(RUNNING).toString(accept);
        }

        query = query.trim();
        if (query.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("Please provide the query to execute the request").toString(accept);
        }

        Class<?> clazz = classMap.get(clas);
        if (clazz == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("Please provide a valid Class to execute the request").toString(accept);
        }

        if (accept != null && accept.equals(RETURN_JSON)) {
            repositorymanager.executeQueryAsync(query, clazz, JSON, queryID);
        } else {
            repositorymanager.executeQueryAsync(query, clazz, XML, queryID);
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(accept);
        return new Message(RUNNING).toString(accept);

    }

    @ResponseBody
    @RequestMapping(value = "/asyncQueryResult", method = RequestMethod.GET)
    public String getAysncQueryResult(@RequestHeader("Accept") String accept, @RequestParam("queryID") String queryID)
            throws InterruptedException, ExecutionException {
        if (!asyncQueryManager.isQueryRunning(queryID)) {
            return asyncQueryManager.getQueryResult(queryID);
        }

        return new Message(RUNNING).toString(accept);

    }

    /**
     * The method is called for get request by user to get information of
     * multiple relation or appellation events.
     * 
     * @param request
     * @param response
     * @param accept
     * @param xml
     * @return
     * @throws JSONException
     * @throws InvalidDataException
     * @throws ParserException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getData(HttpServletRequest request, HttpServletResponse response,
            @RequestHeader("Accept") String accept, @RequestBody String xml) throws JSONException, InvalidDataException,
                    ParserException, IOException, URISyntaxException, ParseException {

        if (xml.equals("")) {
            throw new InvalidDataException("Please provide content in given XML.");
        }

        String returnString = repositorymanager.getList(xml, accept);

        response.setContentType(accept);
        return returnString;

    }

    /**
     * The method is being called upon search parent relation by appellation
     * request from REST and it will call repository to get required result and
     * return it to user.
     * 
     * @param request
     * @param response
     * @param xml
     * @param accept
     * @return
     * @throws ParserException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ParseException
     * @throws JSONException
     * @throws InvalidDataException
     */

    @ResponseBody
    @RequestMapping(value = "/searchByAppellationId", method = RequestMethod.POST)
    public String searchByAppellationId(HttpServletRequest request, HttpServletResponse response,
            @RequestBody String xml, @RequestHeader("Accept") String accept) throws ParserException, IOException,
                    URISyntaxException, ParseException, JSONException, InvalidDataException {

        String trimmedXML = xml.trim();

        if (trimmedXML.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("Please provide XML in body of the post request.").toString(accept);

        } else {
            String returnString = "";
            if (accept != null && accept.equals(RETURN_JSON)) {

                returnString = repositorymanager.searchByAppellationId(xml, JSON);
            } else {
                returnString = repositorymanager.searchByAppellationId(xml, XML);

            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(accept);
            return returnString;
        }
    }

    /**
     * The method is being called upon search request from REST and it will call
     * repository to get required result and return it to user.
     * 
     * @param request
     * @param response
     * @param xml
     * @param accept
     * @return
     * @throws ParserException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ParseException
     * @throws JSONException
     * @throws InvalidDataException
     */

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response, @RequestBody String xml,
            @RequestHeader("Accept") String accept) throws ParserException, IOException, URISyntaxException,
                    ParseException, JSONException, InvalidDataException {

        String trimmedXML = xml.trim();

        if (trimmedXML.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new Message("Please provide XML in body of the post request.").toString(accept);
        } else {
            String returnString = "";
            if (accept != null && accept.equals(RETURN_JSON)) {

                returnString = repositorymanager.search(xml, JSON);
            } else {
                returnString = repositorymanager.search(xml, XML);

            }
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType(accept);
            return returnString;
        }
    }

}
