package info.codingcat.util.httpkitty;

import java.net.URL;

public class HttpKittyImpl implements HttpKitty {


    @Override
    public HttpGetHelper get(URL url) {
        return new HttpGetHelper(url);
    }

    @Override
    public HttpPostHelper post(URL url) {
        return new HttpPostHelper(url);
    }

}
