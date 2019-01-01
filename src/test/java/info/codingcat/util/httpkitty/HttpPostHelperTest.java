package info.codingcat.util.httpkitty;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ HttpPostHelper.class, URL.class })
public class HttpPostHelperTest {

    @Mock
    private URL url;

    @Mock
    private HttpURLConnection connection;

    @Mock
    private InputStream errorStream;

    @Mock
    private InputStream inputStream;

    private OutputStream outputStream;

    @Mock
    private Map<String, List<String>> responseHeaders;

    private HttpPostHelper httpPostHelper;

    @Before
    public void setUp() throws IOException {

        outputStream = new ByteArrayOutputStream();

        when(this.url.openConnection()).thenReturn(connection);
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getErrorStream()).thenReturn(errorStream);
        when(connection.getHeaderFields()).thenReturn(responseHeaders);
        when(connection.getInputStream()).thenReturn(inputStream);
        when(connection.getOutputStream()).thenReturn(outputStream);

        httpPostHelper = new HttpPostHelper(url);

    }

    @Test
    public void testPostBodyWithString() throws IOException {

        String body = "This is Body";
        httpPostHelper.body(body).shoot();
        assertEquals(body, outputStream.toString());

    }

    @Test
    public void testPostBodyWithStream() throws IOException {

        InputStream body = new ByteArrayInputStream("This is Body".getBytes());
        httpPostHelper.body(body).shoot();
        assertEquals("This is Body", outputStream.toString());

    }
}