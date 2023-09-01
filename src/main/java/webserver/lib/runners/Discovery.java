package webserver.lib.runners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import webserver.lib.http.HttpDecoder;
import webserver.lib.http.HttpHandler;
import webserver.lib.pojos.HttpRequest;
import webserver.lib.pojos.HttpResponse;

import static webserver.lib.helpers.HttpMethod.GET;

public class Discovery {
    public static void getBrowsingCategories(HttpExchange exchange) {
        System.out.println("--getBrowsingCategories--");
        System.out.println("--REQUEST--");
        System.out.println(exchange);
         new HttpResponse.Builder()
                .setStatusCode(200)
                .addHeader("Content-Type", "text/html")
                .setEntity("<HTML> <P> Hello There... </P> </HTML>")
                .build();
    }
    public static void getCategoryPlaylists(HttpExchange exchange, final String category) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");
        Integer page = Integer.parseInt(queryParams.get("page"));

        final Integer limit = 16;
        final Integer offset = page.equals(1) ? 0 : page * limit;

        StringBuffer content = new StringBuffer();
        Integer responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), String.format(
                    "https://api.spotify.com/v1/browse/categories/%s/playlists?limit=%s&offset=%s", category, limit, offset),
                    token);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            responseStatusCode = con.getResponseCode();
            responseBody = content.toString();
            in.close();
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHandler.sendResponse(exchange, responseStatusCode, responseBody);
    }
    public static void getNewReleases(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");
        Integer page = Integer.parseInt(queryParams.get("page"));

        final Integer limit = 16;
        final Integer offset = page.equals(1) ? 0 : page * limit;

        StringBuffer content = new StringBuffer();
        Integer responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), String.format(
                    "https://api.spotify.com/v1/browse/new-releases?limit=%s&offset=%s", limit, offset),
                    token);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            responseStatusCode = con.getResponseCode();
            responseBody = content.toString();
            in.close();
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHandler.sendResponse(exchange, responseStatusCode, responseBody);
    }
}
