package com.gengo.client;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest extends GengoTests {

    @Test
    public void testGetServiceLanguagePairs() throws Exception {

        JSONObject response = client.getServiceLanguagePairs();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetServiceLanguages() throws Exception {

        JSONObject response = client.getServiceLanguages();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }
}
