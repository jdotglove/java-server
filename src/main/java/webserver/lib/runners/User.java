package webserver.lib.runners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;

import webserver.lib.pojos.HttpRequest;
import webserver.lib.pojos.HttpResponse;
import webserver.lib.http.HttpDecoder;
import webserver.lib.http.HttpHandler;

import com.sun.net.httpserver.HttpExchange;

import static webserver.lib.helpers.HttpMethod.GET;

public class User {
    public static HttpResponse addToUserQueue(HttpRequest request) {
        System.out.println("--addToUserQueue--");
        System.out.println("--REQUEST--");
        System.out.println(request);
        return new HttpResponse.Builder()
                .setStatusCode(200)
                .addHeader("Content-Type", "text/html")
                .setEntity("<HTML> <P> Hello There... </P> </HTML>")
                .build();
    }

    public static HttpResponse createUserPlaylist(HttpRequest request) {
        System.out.println("--createUserPlaylist--");
        System.out.println("--REQUEST--");
        System.out.println(request);
        return new HttpResponse.Builder()
                .setStatusCode(200)
                .addHeader("Content-Type", "text/html")
                .setEntity("<HTML> <P> Hello There... </P> </HTML>")
                .build();
    }

    public static void getCurrentTrackBreakdown(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), "https://api.spotify.com/v1/me/player/currently-playing", token);

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

    /**
     * @param request.query.token - Spotify auth token to use for the request
     * @return array of user playlist objects
     * @throws IOException
     */
    public static void getUserPlaylists(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), "https://api.spotify.com/v1/me/playlists?limit=50", token);

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

    public static void getUserTopArtists(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), "https://api.spotify.com/v1/me/top/tracks", token);

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

    /**
     * @param request.query.token - Spotify auth token to use for the request
     * @return array of user top track objects
     * @throws IOException
     */
    public static void getUserTopTracks(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), "https://api.spotify.com/v1/me/top/tracks", token);

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

    /**
     * @param request.query.token - Spotify auth token to use for the request
     * @return spotify user object
     * @throws IOException
     */
    public static void loginUser(HttpExchange exchange) throws IOException {
        Map<String, String> queryParams = HttpDecoder.parseQueryParams(exchange.getRequestURI().getQuery());
        String token = queryParams.get("token");


        StringBuffer content = new StringBuffer();
        int responseStatusCode = 200;
        String responseBody = "";
        try {
            HttpURLConnection con = HttpRequest.sendRequest(GET.toString(), "https://api.spotify.com/v1/me", token);

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
