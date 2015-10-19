package com.gengo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.Integer;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.gengo.client.exceptions.ErrorResponseException;
import com.gengo.client.payloads.TranslationJob;
import com.gengo.client.payloads.FileJob;
import com.gengo.client.enums.Tier;

import org.json.JSONObject;
import org.junit.Test;

public class JobsTest extends GengoTests {

    @Test
    public void testPostJobsText() throws Exception {
        // POST a text job
        List<TranslationJob> jobList = new ArrayList<TranslationJob>();
        TranslationJob job = new TranslationJob("java client test", "This is a short story.", "en", "ja", Tier.STANDARD);
        TranslationJob job2 = new TranslationJob("java client test 2", "This is another short story.", "en", "ja", Tier.STANDARD);
        job.setForceNewTranslation(true);
        job2.setForceNewTranslation(true);
        jobList.add(job);
        jobList.add(job2);
        JSONObject postResp = client.postTranslationJobs(jobList, true);
        // Make assertions on POST response
        assertEquals(postResp.getString("opstat"), "ok");
        assertTrue(postResp.has("response"));
        JSONObject postResponse = postResp.getJSONObject("response");
        assertEquals(postResponse.get("job_count"), 2);
        assertTrue(postResponse.has("credits_used"));
        assertTrue(postResponse.has("order_id"));
        String orderId = postResponse.getString("order_id");

        // GET order
        // We have to sleep to give jobs processor some time to process the jobs,
        // then the IDs should be in jobs_available
        Thread.sleep(10000);
        JSONObject getOrderResp = client.getOrderJobs(Integer.parseInt(orderId));
        // Make assertions on GET response
        assertEquals(getOrderResp.getString("opstat"), "ok");
        assertTrue(getOrderResp.has("response"));
        JSONObject getOrderResponse = getOrderResp.getJSONObject("response");
        String jobId = getOrderResponse.getJSONObject("order").getJSONArray("jobs_available").getString(1);

        // GET job
        JSONObject getJobResp = client.getTranslationJob(Integer.parseInt(jobId));
        assertEquals(getJobResp.getString("opstat"), "ok");
        assertTrue(getJobResp.has("response"));
        JSONObject getJobResponse = getJobResp.getJSONObject("response");
        String bodySrc = getJobResponse.getJSONObject("job").getString("body_src");
        assertEquals(bodySrc, "This is a short story.");
    }

    @Test
    public void testPostJobsFiles() throws Exception {
        Map<String, String> filePaths = new HashMap<String, String>();
        FileJob job1 = new FileJob("someslug", "file_job_1", "en", "ja", Tier.STANDARD);
        FileJob job2 = new FileJob("someslug2", "file_job_2", "en", "ja", Tier.STANDARD);
        filePaths.put("file_job_1", "examples/testfiles/test_file1.txt");
        filePaths.put("file_job_2", "examples/testfiles/test_file2.txt");
        List<FileJob> jobList = new ArrayList<FileJob>();
        jobList.add(job1);
        jobList.add(job2);
        JSONObject rsp = client.determineTranslationCostFiles(jobList, filePaths);
        assertEquals(rsp.getString("opstat"), "ok");
        assertTrue(rsp.has("response"));
    }

    @Test
    public void testPostTranslationJobs() throws Exception {
        // there is no Afrikaans source language, so we expect this to raise an exception
        TranslationJob job = new TranslationJob("label_test", "'n Bietjie afrikaanse teks", "af", "es", Tier.STANDARD);
        List<TranslationJob> jobList = new ArrayList<TranslationJob>();
        jobList.add(job);
        exception.expect(ErrorResponseException.class);
        exception.expectMessage("1551: language service not supported");
        client.postTranslationJobs(jobList, false);
    }
}
