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
import java.util.List;
import java.util.Map;

import asellion.manager.AsellionManager;
import asellion.mock.HtmlMethods;

import static asellion.mock.controllers.AsellionController.API_ENDPOINT;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings("unchecked")
@Path(API_ENDPOINT)
public final class AsellionController {

    static final String API_ENDPOINT = "api";
    private static final String GET_ALL_PRODUCTS_ENDPOINT = "/products";
    private static final String GET_ONE_PRODUCT_ENDPOINT = "/products/{id}";
    private static final String UPDATE_PRODUCT_ENDPOINT = "/products/{id}";
    private static final String CREATE_PRODUCT_ENDPOINT = "/products";

    private final AsellionManager asellionManager;

    public AsellionController() {
        this.asellionManager = AsellionManager.build();
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_ONE_PRODUCT_ENDPOINT)
    public String requestAuthorizationCode(@Context UriInfo uriInfo, @Context HttpHeaders hh) {
        AsellionManager.ProductResponse product = asellionManager.getProduct(Integer.parseInt(uriInfo.getPathParameters().get("id").get(0)));
        Map<String, String> stringStringMap = product.toMap();
        return HtmlMethods.mapAsJson(stringStringMap);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(GET_ALL_PRODUCTS_ENDPOINT)
    public String getAllProducts(@Context UriInfo uriInfo, @Context HttpHeaders hh) {
        List<AsellionManager.ProductResponse> entity = (List<AsellionManager.ProductResponse>) asellionManager.getAllProducts().getEntity();
        Map<String, String> collect = entity.stream().collect(toMap(product -> String.valueOf(product.id), product -> HtmlMethods.mapAsJson(product.toMap())));
        return HtmlMethods.mapAsJson(collect);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(UPDATE_PRODUCT_ENDPOINT)
    public String updateProduct(@Context UriInfo uriInfo, @Context HttpHeaders hh, String rawProductRequest) {
        int id = Integer.parseInt(uriInfo.getPathParameters().get("id").get(0)); // Todo: validate
        AsellionManager.ProductRequest productRequest = HtmlMethods.parseRequest(rawProductRequest);
        asellionManager.updateProduct(id, productRequest);
        return HtmlMethods.reportSuccess();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(CREATE_PRODUCT_ENDPOINT)
    public String createProduct(@Context UriInfo uriInfo, @Context HttpHeaders hh, String rawProductRequest) {
        AsellionManager.ProductRequest productRequest = HtmlMethods.parseRequest(rawProductRequest);
        asellionManager.createProduct(productRequest);
        return HtmlMethods.reportSuccess();
    }
}
