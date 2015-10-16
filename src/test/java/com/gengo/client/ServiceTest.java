package com.gengo.client;

import com.gengo.client.exceptions.GengoException;

import org.json.JSONObject;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest extends GengoTests {

    @Test
    public void testGetServiceLanguagePairs() throws GengoException, JSONException {

        JSONObject response = client.getServiceLanguagePairs();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetServiceLanguages() throws GengoException, JSONException {

        JSONObject response = client.getServiceLanguages();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }
}
