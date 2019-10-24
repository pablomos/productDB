package asellion.mock;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

import asellion.manager.AsellionManager;

public class HtmlMethods {

    private HtmlMethods() {
        // Do nothing; this makes sure the class cannot be instantiated
    }

    public static String reportSuccess() { // Todo: simplify
        StringBuilder b = new StringBuilder("<html> <title>Hello Jersey</title><body><p>");
        b.append("HTTP/1.1 ").append(Response.Status.OK).append(" ").append(Response.Status.OK.getReasonPhrase())
         .append("<br>Content-Type: application/json;charset=UTF-8<br>Cache-Control: no-store<br>Pragma: no-cache<br><br>");
        Map<String, String> stringStringMap = Collections.singletonMap("result", "success");
        return b.append(mapAsJson(stringStringMap)).append("</p></body></html> ").toString();
    }

    public static String mapAsJson(Map<String, String> map) {
        StringBuilder b = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            b.append("\t\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\",");
        }
        b.append("}");
        return b.toString();
    }

    public static String mapAsJsonHtml(Map<String, String> map) {
        StringBuilder b = new StringBuilder("{");
        b.append("<br>");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            b.append("\t\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\",").append("<br>");
        }
        b.append("}").append("<br>");
        return b.toString();
    }

    public static AsellionManager.ProductRequest parseRequest(String rawRequest) {
        // Todo: fix
        return new AsellionManager.ProductRequest("something", 42.);
    }

}
