package info.codingcat.util.httpkitty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface HttpKittyResponse {

    InputStream getResponse();

    String getResponseAsString() throws IOException;

    InputStream getErrorMessage();

    String getErrorMessageAsString() throws IOException;

    int getResponseCode();

    String getHeader(String key);

    Map<String, String> getHeaders();

}
