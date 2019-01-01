package info.codingcat.util.httpkitty;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HttpKittyResponse {

    InputStream getResponse();

    InputStream getErrorMessage();

    int getResponseCode();

    List<String> getHeader(String key);

    Map<String, List<String>> getHeaders();

}
