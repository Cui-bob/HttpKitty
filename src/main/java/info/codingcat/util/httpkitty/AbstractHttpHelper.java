package info.codingcat.util.httpkitty;

import java.io.IOException;

import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHttpHelper {

    private Map<String, String> headers = new HashMap<>();

    private URL url;

    protected AbstractHttpHelper(URL url){
        this.url = url;
    }

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
                auth.append("Basic").append(' ').append(Arrays.toString(base64Encode));
                break;
            default:
                throw new IOException("Scheme not supported!");
        }

        headers.put("Authorization", auth.toString());
        return this;
    }

    public abstract HttpKittyResponse shoot();

}
