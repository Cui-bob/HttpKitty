package info.codingcat.util.httpkitty;


import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ HttpGetHelper.class, URL.class })
public class HttpGetHelperTest {

    private URL url;

    private HttpURLConnection connection;

    private InputStream errorStream;

    private InputStream inputStream;

    private OutputStream outputStream;

    private Map<String, List<String>> responseHeaders;

    private HttpGetHelper httpGetHelper;

    @Before
    public void setUp() throws IOException {
        this.url = mock(URL.class);

        this.connection = mock(HttpURLConnection.class);

        responseHeaders = new HashMap<>();
        List<String> testRespHeaderList = new ArrayList<>();
        testRespHeaderList.add("Test1");
        testRespHeaderList.add("Test2");
        responseHeaders.put("TestHeader", testRespHeaderList);

        outputStream = new  ByteArrayOutputStream();

        String inputMessage = "This is the input message";
        inputStream = new ByteArrayInputStream(inputMessage.getBytes());

        String errorMessage = "This is the test error Message";
        errorStream = new ByteArrayInputStream(errorMessage.getBytes());


        when(this.url.openConnection()).thenReturn(connection);
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getErrorStream()).thenReturn(errorStream);
        when(connection.getHeaderFields()).thenReturn(responseHeaders);
        when(connection.getInputStream()).thenReturn(inputStream);
        when(connection.getOutputStream()).thenReturn(outputStream);
//        doNothing().when(connection).connect();

        httpGetHelper = new HttpGetHelper(url);

    }

    @Test
    public void testGetWithHeaderAndAuth() throws IOException {

        httpGetHelper.header("testHeaderKey", "testHeaderValue")
                .auth("TestUsername", "TestPassword", HttpAuthenticationScheme.Basic)
                .shoot();
        Mockito.verify(connection, Mockito.times(1)).setRequestProperty("testHeaderKey", "testHeaderValue");
        Mockito.verify(connection, Mockito.times(1)).setRequestProperty("Authorization", "Basic VGVzdFVzZXJuYW1lOlRlc3RQYXNzd29yZA==");
    }

    @Test
    public void testGetResponse() throws IOException {

        HttpKittyResponse response = httpGetHelper.shoot();
        assertEquals("This is the input message", IOUtils.toString(response.getResponse(), "utf-8"));
        assertEquals("This is the test error Message", IOUtils.toString(response.getErrorMessage(), "utf-8"));
        assertEquals(200, response.getResponseCode());
        assertEquals(1, response.getHeaders().size());
        assertEquals("Test1", response.getHeaders().get("TestHeader").get(0));
        assertEquals("Test2", response.getHeaders().get("TestHeader").get(1));

    }

    @Test
    public void testGetWithCookie() throws IOException {

        httpGetHelper.cookie("Test1", "Test1")
                .cookie("Test2", "Test2")
                .shoot();

        Mockito.verify(connection).setRequestProperty("Cookie", "Test1=Test1;Test2=Test2");

    }



}