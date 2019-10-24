package asellion.mock.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asellion.manager.AsellionManager;
import asellion.mock.HtmlMethods;

import static asellion.mock.controllers.AsellionController.API_ENDPOINT;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
@Path(API_ENDPOINT)
public final class AsellionController {

    static final String API_ENDPOINT = "api";
    private static final String GET_ALL_PRODUCTS_ENDPOINT = "/products";
    private static final String GET_ONE_PRODUCT_ENDPOINT = "/products/{id}";
    private static final String UPDATE_PRODUCT_ENDPOINT = "/products/{id}";
    private static final String CREATE_PRODUCT_ENDPOINT = "/products";
    private static final String UI_ENDPOINT = "/ui";
    public static final Map<Integer, AsellionManager.ProductResponse> DATA = new HashMap<>();

    private final AsellionManager asellionManager;

    public AsellionController() {
        this.asellionManager = AsellionManager.build();
        DATA.put(0, new AsellionManager.ProductResponse(0, "mock", 1., Timestamp.valueOf(LocalDateTime.now())));
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_ONE_PRODUCT_ENDPOINT)
    public String getOneProduct(@Context UriInfo uriInfo, @Context HttpHeaders hh) {
        try {
            AsellionManager.ProductResponse product = asellionManager.getProduct(Integer.parseInt(uriInfo.getPathParameters().get("id").get(0)));
            Map<String, String> stringStringMap = product.toMap();
            return HtmlMethods.mapAsJson(stringStringMap);
        } catch (RuntimeException e) {
            return HtmlMethods.reportError(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_ALL_PRODUCTS_ENDPOINT)
    public String getAllProducts(@Context UriInfo uriInfo, @Context HttpHeaders hh) {
        List<AsellionManager.ProductResponse> entity = (List<AsellionManager.ProductResponse>) asellionManager.getAllProducts().getEntity();
        Map<String, String> collect = entity.stream().collect(toMap(
            product -> String.valueOf(product.id),
            product -> HtmlMethods.mapAsJson(product.toMap())
        ));
        return collect.entrySet().stream().map(entry -> "\"" + entry.getKey() + "\": " + entry.getValue()).collect(joining(", ", "{", "}"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(UPDATE_PRODUCT_ENDPOINT)
    public String updateProduct(@Context UriInfo uriInfo, @Context HttpHeaders hh, String rawProductRequest) {
        int id = Integer.parseInt(uriInfo.getPathParameters().get("id").get(0)); // Todo: validate
        AsellionManager.ProductRequest productRequest = HtmlMethods.parseRequest(rawProductRequest);
        try {
            asellionManager.updateProduct(id, productRequest);
            return HtmlMethods.reportSuccess();
        } catch (RuntimeException e) {
            return HtmlMethods.reportError(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(CREATE_PRODUCT_ENDPOINT)
    public String createProduct(@Context UriInfo uriInfo, @Context HttpHeaders hh, String rawProductRequest) {
        AsellionManager.ProductRequest productRequest = HtmlMethods.parseRequest(rawProductRequest);
        asellionManager.createProduct(productRequest);
        return HtmlMethods.reportSuccess();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path(UI_ENDPOINT)
    public String uiEndpoint(@Context UriInfo uriInfo, @Context HttpHeaders hh) {
        return getAllProducts(uriInfo, hh);
    }
}
