package info.codingcat.util.httpkitty;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class KittyURLBuilder{

    private StringBuilder url = new StringBuilder();

    public KittyURLBuilder(String protocol, String host, String port){
        this.url.append(protocol).append("://").append(host).append(":").append(port);
    }

    public KittyURLBuilder(String protocol, String host, String port, String path){
        this(protocol, host, port);

        if(!path.startsWith("/")) {
            url.append("/");
        }

        url.append(path);
    }

    public KittyURLBuilder param(String key, String value) throws UnsupportedEncodingException {
        if(url.indexOf("?") != -1){
            url.append("?");
        }else{
            url.append("&");
        }
        url.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(value, "UTF-8"));
        return this;
    }

    public KittyURLBuilder params(Map<String, String> params) throws UnsupportedEncodingException {
        for(Map.Entry<String, String> param : params.entrySet()){
            this.param(param.getKey(), param.getValue());
        }

        return this;
    }

    public URL build() throws MalformedURLException {
        return new URL(url.toString());
    }

}
