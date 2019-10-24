package asellion.mock;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asellion.manager.AsellionManager;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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

    public static AsellionManager.ProductRequest parseRequest(String rawRequest) {
        String trimmedRequest = rawRequest.trim();
        Map<String, String> keyValueMap = Arrays.stream(trimmedRequest.substring(1, trimmedRequest.length() - 1).split(","))
                                                .map(String::trim)
                                                .map(f -> f.replace('\"', ' '))
                                                .map(f -> f.split(":"))
                                                .collect(toMap(f -> f[0].trim(), f -> f[1].trim()));
        return new AsellionManager.ProductRequest(keyValueMap.get("name"), Double.parseDouble(keyValueMap.get("currentPrice")));
    }

}
