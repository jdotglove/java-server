package webserver.lib.helpers;

import webserver.lib.pojos.HttpRequest;
import webserver.lib.pojos.HttpResponse;

public interface RequestRunner {
    HttpResponse run(HttpRequest request);
}
