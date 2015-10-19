package com.gengo.client;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest extends GengoTests {

    @Test
    public void testGetAccountBalance() throws Exception {

        JSONObject response = client.getAccountBalance();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountStats() throws Exception {

        JSONObject response = client.getAccountStats();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }

    @Test
    public void testGetAccountPreferredTranslators() throws Exception {

        JSONObject response = client.getAccountPreferredTranslators();
        Assert.assertEquals(response.get("opstat").toString(), "ok");
        Assert.assertTrue(response.has("response"));
    }
}
