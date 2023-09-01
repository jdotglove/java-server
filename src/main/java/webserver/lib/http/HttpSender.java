package webserver.lib.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpSender {
    public static HttpURLConnection sendRequest(final String method, final String uri, final String token) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

        return con;
    }
}
