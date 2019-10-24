package asellion.mock;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import asellion.manager.AsellionManager;

import static java.util.stream.Collectors.joining;

public class HtmlMethods {

    private HtmlMethods() {
        // Do nothing; this makes sure the class cannot be instantiated
    }

    public static String reportSuccess() {
        return "{\"result\": \"success\"}";
    }

    public static String reportError(RuntimeException e) {
        Map<String, String> map = new HashMap<>();
        map.put("result", "error");
        map.put("message", e.getMessage());
        return mapAsJson(map);
    }

    public static String mapAsJson(Map<String, String> map) {
        return map.entrySet().stream().map(entry -> "\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"").collect(joining(", ", "{", "}"));
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
