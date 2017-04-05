package edu.asu.qstore4s.message;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.Assert;

public class MessageTest {
    @Test
    public void testToString() {
        Map<String, String> messageParams = new HashMap<>();
        messageParams.put("url", "/abc/xyz");
        messageParams.put("status", "Running");
        Message mess = new Message(messageParams);

        Assert.assertEquals("{ \"message\" : { \"url\" : \"/abc/xyz\" , \"status\" : \"Running\" } }",
                mess.toString("application/json"));

        Assert.assertEquals("<message><url>/abc/xyz</url> <status>Running</status> </message>",
                mess.toString("application/xml"));
    }
}
