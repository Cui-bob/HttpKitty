package info.codingcat.util.httpkitty;

import java.net.URL;

public interface HttpKitty {

    HttpGetHelper get(URL url);

    HttpPostHelper post(URL url);

}
