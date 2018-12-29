package info.codingcat.util.httpkitty;

import java.io.InputStream;
import java.util.Map;

public interface HttpKittyResponse {

    InputStream getResponse();

    String getResponseAsString();

    InputStream getErrorMessage();

    String getErrorMessageAsString();

    int getResponseCode();

    String getHeader(String key);

    Map<String, String> getHeaders();

}
