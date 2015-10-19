package com.gengo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

public class ServiceTest extends GengoTests {

    @Test
    public void testGetServiceLanguagePairs() throws Exception {

        JSONObject response = client.getServiceLanguagePairs();
        assertEquals(response.get("opstat").toString(), "ok");
        assertTrue(response.has("response"));
    }

    @Test
    public void testGetServiceLanguages() throws Exception {

        JSONObject response = client.getServiceLanguages();
        assertEquals(response.get("opstat").toString(), "ok");
        assertTrue(response.has("response"));
    }
}
