package info.codingcat.util.httpkitty;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractHttpHelper {

    protected Map<String, String> headers = new HashMap<>();

    protected URL url;

    protected AbstractHttpHelper(URL url){
        this.url = url;
    }

    protected Map<String, String> cookies = new LinkedHashMap<>();

    public AbstractHttpHelper header(String key, String value){
        headers.put(key, value);
        return this;
    }

    public AbstractHttpHelper headers(Map<String, String> headers){
        this.headers.putAll(headers);
        return this;
    }

    public AbstractHttpHelper auth(String username, String password, HttpAuthenticationScheme scheme) throws IOException {

        StringBuilder auth = new StringBuilder();

        switch (scheme){
            case Basic:
                byte[] base64Encode = Base64.getEncoder().encode((username + ":" + password).getBytes());
                auth.append("Basic").append(' ').append(new String(base64Encode));
                break;
            default:
                throw new IOException("Scheme not supported!");
        }

        headers.put("Authorization", auth.toString());
        return this;
    }

    public abstract HttpKittyResponse shoot() throws IOException;

    protected void setConnectionHeaders(HttpURLConnection conn){

        for(Map.Entry<String, String> header : this.headers.entrySet()){

            conn.setRequestProperty(header.getKey(), header.getValue());

        }

        StringBuilder cookieHeader = new StringBuilder();

        for (Map.Entry<String, String> cookie : cookies.entrySet()){

            if(cookieHeader.length() != 0){
                cookieHeader.append(";");
            }

            cookieHeader.append(cookie.getKey()).append("=").append(cookie.getValue());

        }

        conn.setRequestProperty("Cookie", cookieHeader.toString());

    }

    public AbstractHttpHelper cookie(String key, String value){
        this.cookies.put(key, value);
        return this;
    }

}
