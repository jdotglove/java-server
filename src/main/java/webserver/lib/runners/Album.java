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

import static webserver.lib.helpers.HttpMethod.GET;

public class Album {
    public static void getAlbum(HttpExchange exchange, final String albumId) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");

        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), String.format(
                    "https://api.spotify.com/v1/albums/%s", albumId),
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
    public static void getAlbumTracks(HttpExchange exchange, final String albumId) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), String.format(
                    "https://api.spotify.com/v1/albums/%s/tracks", albumId),
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
