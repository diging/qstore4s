package edu.asu.qstore4s.message;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class MessageTest {

    Map<String, String> messageParams = new HashMap<>();
    Message mess = new Message(messageParams);

    @Before
    public void setup() {
        messageParams.put("url", "/abc/xyz");
        messageParams.put("status", "Running");
    }

    @Test
    public void test_toStringXML_success() {

        Assert.assertEquals("<message><url>/abc/xyz</url> <status>Running</status> </message>",
                mess.toString("application/xml"));
    }

    @Test
    public void test_toStringJSON_success() {
        Assert.assertEquals("{ \"message\" : { \"url\" : \"/abc/xyz\" , \"status\" : \"Running\" } }",
                mess.toString("application/json"));
    }
}
