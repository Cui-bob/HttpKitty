package info.codingcat.util.httpkitty;

import java.util.Map;

public class HttpKittyBuilder {



    public static class HttpKittyBuilderHelper{
        private Map<String, String> headers;

        private String url;

        public HttpKittyBuilderHelper(String url){
            this.url = url;
        }

        public HttpKitty build(){
            return null;
        }

        public HttpKittyBuilderHelper header(String key, String value){
            return this;
        }

        public HttpKittyBuilderHelper auth(String username, String password){
            return this;
        }

    }



}
