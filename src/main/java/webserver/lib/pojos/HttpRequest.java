package webserver.lib.pojos;

import webserver.lib.helpers.HttpMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    private final HttpMethod httpMethod;
    private final URI uri;
    private final Map<String, List<String>> requestHeaders;
    private Map<String, List<String>> queryParams;

    public static Map<String, List<String>> splitQuery(String[] pairs) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();

        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1
                    ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
                    : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;

    }

    private HttpRequest(HttpMethod opCode, URI uri, Map<String, List<String>> requestHeaders) {
        this.httpMethod = opCode;
        this.uri = uri;
        this.requestHeaders = requestHeaders;
        try {
            this.queryParams = splitQuery(uri.getQuery().split("&"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public URI getUri() {
        return uri;
    }

    public Map<String, List<String>> getQueryParams() {
        return queryParams;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Map<String, List<String>> getRequestHeaders() {
        return requestHeaders;
    }

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

    public static class Builder {
        private HttpMethod httpMethod;
        private URI uri;
        private Map<String, List<String>> requestHeaders;

        public Builder() {
        }

        public void setHttpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
        }

        public void setUri(URI uri) {
            this.uri = uri;
        }

        public void setRequestHeaders(Map<String, List<String>> requestHeaders) {
            this.requestHeaders = requestHeaders;
        }

        public HttpRequest build() {
            return new HttpRequest(httpMethod, uri, requestHeaders);
        }
    }
}
