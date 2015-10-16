package com.gengo.client;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;
import org.json.JSONObject;
import org.json.JSONException;

import com.gengo.client.enums.Tier;
import com.gengo.client.payloads.TranslationJob;
import com.gengo.client.exceptions.GengoException;
import com.gengo.client.exceptions.ErrorResponseException;


public class OrderTest extends GengoTests {

    @Test
    public void testGetOrder() throws ErrorResponseException, GengoException, JSONException, InterruptedException {

        // post a job and retrieve the order_id
        List<TranslationJob> jobList = new ArrayList<TranslationJob>();
        TranslationJob job = new TranslationJob("java client test", "This is a short story.", "en", "ja", Tier.STANDARD);
        job.setForceNewTranslation(true);
        jobList.add(job);

        JSONObject postResp = client.postTranslationJobs(jobList, false);
        // Make assertions on POST response
        Assert.assertEquals(postResp.getString("opstat"), "ok");
        Assert.assertTrue(postResp.has("response"));
        JSONObject postResponse = postResp.getJSONObject("response");
        Assert.assertEquals(postResponse.get("job_count"), 1);
        Assert.assertTrue(postResponse.has("credits_used"));
        Assert.assertTrue(postResponse.has("order_id"));
        String orderId = postResponse.getString("order_id");

        // sleep to give jobs-processor time to process this job
        Thread.sleep(10000);
        int id = Integer.parseInt(orderId);
        JSONObject getOrderResp = client.getOrderJobs(id);
        // Make assertions on GET response
        Assert.assertEquals(getOrderResp.getString("opstat"), "ok");
        Assert.assertTrue(getOrderResp.has("response"));

        // get an order that does not exists
        id += 1000;
        try {
            client.getOrderJobs(id);
        }
        catch (ErrorResponseException ex)
        {
            // make sure we get the correct error response
            Assert.assertEquals(ex.getErrorCode(), ErrorResponseException.UNAUTHORIZED_ORDER_ACCESS);
        }
    }
}
