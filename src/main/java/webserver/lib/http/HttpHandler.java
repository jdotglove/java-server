package webserver.lib.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;

/**
 * Handle HTTP Request Response lifecycle.
 */
public class HttpHandler {

    public static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes();
        exchange.sendResponseHeaders(statusCode, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    // private final Map<String, RequestRunner> routes;

    // public HttpHandler(final Map<String, RequestRunner> routes) {
    //     this.routes = routes;
    // }
    // public void handleConnection(final InputStream inputStream, final OutputStream outputStream) throws IOException {
    //     final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    //     System.out.println("Here1");
    //     Optional<HttpRequest> request = HttpDecoder.decode(inputStream);
    //     System.out.println(request.isPresent());
    //     request.ifPresentOrElse((r) -> handleRequest(r, bufferedWriter), () -> handleInvalidRequest(bufferedWriter));

    //     bufferedWriter.close();
    //     inputStream.close();
    // }
    // private void handleInvalidRequest(final BufferedWriter bufferedWriter) {
    //     System.out.println("Here2");
    //     HttpResponse notFoundResponse = new HttpResponse.Builder().setStatusCode(400).setEntity("Invalid Request...").build();
    //     ResponseWriter.writeResponse(bufferedWriter, notFoundResponse);
    // }

    // private boolean containsRoute(final String routeKey) {
    //     //final Set<String> routeSet = routes.keySet();
    //     return routes.containsKey(routeKey);
    // }

    // private void handleRequest(final HttpRequest request, final BufferedWriter bufferedWriter) {
    //     final String routeKey = request.getHttpMethod().name().concat(request.getUri().getRawPath());
    //     System.out.println("Here");
    //     System.out.println(routeKey);
    //     if (containsRoute(routeKey)) {
    //         ResponseWriter.writeResponse(bufferedWriter, routes.get(routeKey).run(request));
    //     } else {
    //         // Not found
    //         ResponseWriter.writeResponse(bufferedWriter, new HttpResponse.Builder().setStatusCode(404).setEntity("Route Not Found...").build());
    //     }
    // }
}
