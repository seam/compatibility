package org.jboss.seam.compat.jaxrs;

import static org.junit.Assert.assertEquals;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AbstractRestClientTest {

    protected HttpClient client = new HttpClient();

    protected void test(String url, int expectedStatus, String expectedBody, String accept) throws Exception {
        GetMethod get = new GetMethod(url);
        get.setRequestHeader("Accept", accept);
        assertEquals(expectedStatus, client.executeMethod(get));
        if (expectedBody != null) {
            assertEquals(expectedBody, get.getResponseBodyAsString());
        }
    }

    protected void test(String url, int expectedStatus, String expectedBody) throws Exception {
        test(url, expectedStatus, expectedBody, "text/plain");
    }
}
