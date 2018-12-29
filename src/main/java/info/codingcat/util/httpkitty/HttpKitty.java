package info.codingcat.util.httpkitty;

import java.io.OutputStream;
import java.util.Map;

public interface HttpKitty {

    HttpKittyResponse get();

    HttpKittyResponse get(Map<String, String> params);

    HttpKittyResponse post(String body);

    HttpKittyResponse post(OutputStream body);

}
