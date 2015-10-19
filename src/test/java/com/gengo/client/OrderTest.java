package com.gengo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.json.JSONObject;

import com.gengo.client.enums.Tier;
import com.gengo.client.payloads.TranslationJob;
import com.gengo.client.exceptions.ErrorResponseException;


public class OrderTest extends GengoTests {

    @Test
    public void testGetOrder() throws Exception {

        // post a job and retrieve the order_id
        List<TranslationJob> jobList = new ArrayList<TranslationJob>();
        TranslationJob job = new TranslationJob("java client test", "This is a short story.", "en", "ja", Tier.STANDARD);
        job.setForceNewTranslation(true);
        jobList.add(job);

        JSONObject postResp = client.postTranslationJobs(jobList, false);
        // Make assertions on POST response
        assertEquals(postResp.getString("opstat"), "ok");
        assertTrue(postResp.has("response"));
        JSONObject postResponse = postResp.getJSONObject("response");
        assertEquals(postResponse.get("job_count"), 1);
        assertTrue(postResponse.has("credits_used"));
        assertTrue(postResponse.has("order_id"));
        String orderId = postResponse.getString("order_id");

        // sleep to give jobs-processor time to process this job
        Thread.sleep(10000);
        int id = Integer.parseInt(orderId);
        JSONObject getOrderResp = client.getOrderJobs(id);
        // Make assertions on GET response
        assertEquals(getOrderResp.getString("opstat"), "ok");
        assertTrue(getOrderResp.has("response"));

        // get an order that does not exists
        id += 1000;
        try {
            client.getOrderJobs(id);
        }
        catch (ErrorResponseException ex)
        {
            // make sure we get the correct error response
            assertEquals(ex.getErrorCode(), ErrorResponseException.UNAUTHORIZED_ORDER_ACCESS);
        }
    }
}
