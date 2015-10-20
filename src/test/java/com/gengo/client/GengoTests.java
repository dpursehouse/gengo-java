package com.gengo.client;

import static org.junit.Assume.assumeNotNull;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.gengo.client.exceptions.ErrorResponseException;

public class GengoTests {
    static String public_key;
    static String private_key;
    static GengoClient client;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        public_key = System.getenv("GENGO_PUBKEY");
        private_key = System.getenv("GENGO_PRIVKEY");
        assumeNotNull(public_key);
        assumeNotNull(private_key);
        client = new GengoClient(public_key, private_key, true);
    }

    protected static class ErrorResponseCodeMatches extends TypeSafeMatcher<ErrorResponseException> {
        private int code;

        public ErrorResponseCodeMatches(int code) {
            this.code = code;
        }

        @Override
        protected boolean matchesSafely(ErrorResponseException item) {
            return item.getErrorCode() == code;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("expected error response code ").appendValue(code);
        }

        @Override
        protected void describeMismatchSafely(ErrorResponseException item, Description mismatchDescription) {
            mismatchDescription.appendText("was ").appendValue(item.getErrorCode());
        }
    }
}
