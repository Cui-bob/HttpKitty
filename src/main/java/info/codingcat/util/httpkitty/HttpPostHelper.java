package info.codingcat.util.httpkitty;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostHelper extends AbstractHttpHelper {

    private InputStream body;

    protected HttpPostHelper(URL url) {
        super(url);
    }


    public HttpPostHelper body(String body){
        this.body = new ByteArrayInputStream(body.getBytes());
        return this;
    }

    public HttpPostHelper body(InputStream body){
        this.body = body;
        return this;
    }

    @Override
    public HttpKittyResponse shoot() throws IOException {

        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();

        conn.setRequestMethod("POST");

        this.setConnectionHeaders(conn);

        conn.setDoOutput(true); // Default is false

        OutputStream out = conn.getOutputStream();

        IOUtils.copy(this.body, out);
        out.flush();

        HttpKittyResponseImpl httpKittyResponse = new HttpKittyResponseImpl();

        httpKittyResponse.setResponseCode(conn.getResponseCode());
        httpKittyResponse.setErrorMessage(conn.getErrorStream());
        httpKittyResponse.setResponse(conn.getInputStream());
        httpKittyResponse.setHeaders(conn.getHeaderFields());

        return httpKittyResponse;

    }
}
