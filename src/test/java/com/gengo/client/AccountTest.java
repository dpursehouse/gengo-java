package com.gengo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

public class AccountTest extends GengoTests {

    @Test
    public void testGetAccountBalance() throws Exception {

        JSONObject response = client.getAccountBalance();
        assertEquals(response.get("opstat").toString(), "ok");
        assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountStats() throws Exception {

        JSONObject response = client.getAccountStats();
        assertEquals(response.get("opstat").toString(), "ok");
        assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountPreferredTranslators() throws Exception {

        JSONObject response = client.getAccountPreferredTranslators();
        assertEquals(response.get("opstat").toString(), "ok");
        assertTrue(response.has("response"));
    }
}
