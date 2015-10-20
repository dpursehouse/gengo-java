package com.gengo.client;

import org.junit.Test;

public class ServiceTest extends GengoTests {

    @Test
    public void testGetServiceLanguagePairs() throws Exception {
        assertSuccessfulResponse(client.getServiceLanguagePairs());
    }

    @Test
    public void testGetServiceLanguages() throws Exception {
        assertSuccessfulResponse(client.getServiceLanguages());
    }
}
