package com.gengo.client;

import com.gengo.client.exceptions.GengoException;

import org.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest extends GengoTests {

    @Test
    public void testGetAccountBalance() throws GengoException, JSONException {

        JSONObject response = client.getAccountBalance();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountStats() throws GengoException, JSONException {

        JSONObject response = client.getAccountStats();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountPreferredTranslators() throws GengoException, JSONException {

        JSONObject response = client.getAccountPreferredTranslators();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }
}
