package com.gengo.client;

import org.junit.Test;

public class AccountTest extends GengoTests {

    @Test
    public void testGetAccountBalance() throws Exception {
        assertSuccessfulResponse(client.getAccountBalance());
    }

    @Test
    public void testGetAccountStats() throws Exception {
        assertSuccessfulResponse(client.getAccountStats());
    }

    @Test
    public void testGetAccountPreferredTranslators() throws Exception {
        assertSuccessfulResponse(client.getAccountPreferredTranslators());
    }
}
