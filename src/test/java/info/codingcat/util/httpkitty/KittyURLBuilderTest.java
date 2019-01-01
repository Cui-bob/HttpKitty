package info.codingcat.util.httpkitty;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class KittyURLBuilderTest {

    private KittyURLBuilder builder;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testBuildWithoutPort() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        String url = builder.build().toString();
        assertEquals("https://google.ca", url);
    }

    @Test
    public void testBuildWithPort() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca").port(443);
        String url = builder.build().toString();
        assertEquals("https://google.ca:443", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSeparatelySpecified() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("test1");
        builder.path("test2");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneString() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("test1/test2");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneStringStartsWithASlash() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("/test1/test2");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneStringEndsWithASlash() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("test1/test2/");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneStringStartsWithMultipleSlashes() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("///test1/test2");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneStringEndsWithMultipleSlashes() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("test1/test2///");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithoutPortWithPathSpecifiedInOneStringStartsAndEndsWithMultipleSlashes() throws MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.path("///test1/test2///");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2", url);
    }

    @Test
    public void testBuildWithOneParam() throws MalformedURLException, UnsupportedEncodingException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.param("key", "value");
        String url = builder.build().toString();
        assertEquals("https://google.ca?key=value", url);
    }

    @Test
    public void testBuildWithMultipleParamsSeparately() throws MalformedURLException, UnsupportedEncodingException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.param("key", "value");
        builder.param("key2", "value2");
        String url = builder.build().toString();
        assertEquals("https://google.ca?key=value&key2=value2", url);
    }

    @Test
    public void testBuildWithMultipleParams() throws MalformedURLException, UnsupportedEncodingException {
        builder = new KittyURLBuilder("https", "google.ca");
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", "value2");
        builder.params(map);
        String url = builder.build().toString();
        assertTrue(
                url.equals("https://google.ca?key=value&key2=value2")
                        ||
                        url.equals("https://google.ca?key2=value2&key=value")
        );
    }

    @Test
    public void testBuildWithMultipleParamsAndPaths() throws UnsupportedEncodingException, MalformedURLException {
        builder = new KittyURLBuilder("https", "google.ca");
        builder.param("key1", "value1");
        builder.param("key2", "value2");
        builder.path("test1");
        builder.path("test2");
        String url = builder.build().toString();
        assertEquals("https://google.ca/test1/test2?key1=value1&key2=value2", url);

    }

}