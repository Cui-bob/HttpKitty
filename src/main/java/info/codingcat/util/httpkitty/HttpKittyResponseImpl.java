package info.codingcat.util.httpkitty;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class HttpKittyResponseImpl implements HttpKittyResponse {

    private InputStream response;

    private InputStream errorMessage;

    private int responseCode;

    private Map<String, List<String>> headers;

    @Override
    public InputStream getResponse() {
        return this.response;
    }


    @Override
    public InputStream getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public int getResponseCode() {
        return this.responseCode;
    }

    @Override
    public List<String> getHeader(String key) {
        return this.headers.get(key);
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public void setResponse(InputStream response) {
        this.response = response;
    }

    public void setErrorMessage(InputStream errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
