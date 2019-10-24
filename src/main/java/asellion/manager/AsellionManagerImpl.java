package asellion.manager;

import javax.ws.rs.core.Response;
import java.util.List;

import asellion.common.BackendDao;
import asellion.common.MockBackendDao;

public class AsellionManagerImpl implements AsellionManager {

    private final BackendDao backendDao;

    AsellionManagerImpl() {
        this.backendDao = new MockBackendDao();
    }

    @Override
    public Response getAllProducts() {
        List<ProductResponse> products = backendDao.getAllProducts();
        return Response.ok(products).build();
    }

    @Override
    public ProductResponse getProduct(int id) {
        return backendDao.getProduct(id);
    }

    @Override
    public void updateProduct(int id, ProductRequest request) {
        backendDao.updateProduct(id, request);
    }

    @Override
    public void createProduct(ProductRequest request) {
        backendDao.createProduct(request);
    }
}
