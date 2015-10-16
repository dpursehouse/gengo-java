package com.gengo.client;

import static org.junit.Assume.assumeNotNull;

import org.junit.BeforeClass;

public class GengoTests {
    static String public_key;
    static String private_key;
    static GengoClient client;

    @BeforeClass
    public static void setup() {
        public_key = System.getenv("GENGO_PUBKEY");
        private_key = System.getenv("GENGO_PRIVKEY");
        assumeNotNull(public_key);
        assumeNotNull(private_key);
        client = new GengoClient(public_key, private_key, true);
    }
}
