package info.codingcat.util.httpkitty;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KittyURLBuilder {

    private StringBuilder url = new StringBuilder();

    private Map<String, String> params = new LinkedHashMap<>();

    private List<String> paths = new ArrayList<>();

    private int port = -1;

    public KittyURLBuilder(String protocol, String host) {
        this.url.append(protocol).append("://").append(host.replace("/", ""));
    }

    public KittyURLBuilder port(int port) {
        this.port = port;
        return this;
    }


    public KittyURLBuilder path(String path) {

        while (path.startsWith("/")) {
            path = path.substring(1);
        }

        while (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        this.paths.add(path);


        return this;
    }

    public KittyURLBuilder param(String key, String value) throws UnsupportedEncodingException {
        this.params.put(key, value);
        return this;
    }

    public KittyURLBuilder params(Map<String, String> params) throws UnsupportedEncodingException {
        this.params.putAll(params);
        return this;
    }

    public URL build() throws MalformedURLException {

        // Append port

        if(port != -1){

            this.url.append(':').append(port);

        }

        // Append paths

        for(String path : paths){

            url.append('/').append(path);

        }

        // Append params

        this.url.append('?');

        for(Map.Entry<String, String> param : this.params.entrySet()){

            this.url.append(param.getKey()).append('=').append(param.getValue());
            this.url.append('&');
        }

        this.url.deleteCharAt(this.url.length() - 1);

        return new URL(this.url.toString());

    }

}
