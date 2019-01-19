package info.codingcat.util.httpkitty;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetHelper extends AbstractHttpHelper{

    protected HttpGetHelper(URL url) {
        super(url);
    }

    @Override
    public HttpKittyResponse shoot() throws IOException {

        HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();

        conn.setRequestMethod("GET");

        this.setConnectionHeaders(conn);

        HttpKittyResponseImpl httpKittyResponse = new HttpKittyResponseImpl();

        httpKittyResponse.setResponseCode(conn.getResponseCode());
        httpKittyResponse.setErrorMessage(conn.getErrorStream());
        httpKittyResponse.setResponse(conn.getInputStream());
        httpKittyResponse.setHeaders(conn.getHeaderFields());

        return httpKittyResponse;
    }

}
