package asellion.common;

import java.util.List;

import asellion.manager.AsellionManager;

/**
 * This class will contain the real implementation of the database-access object, which will talk to the database to save and retrieve entries
 * It will be built using JDBC
 */
public class RealBackendDao implements BackendDao {
    private static final String ERROR_MESSAGE = "Not implemented yet";

    RealBackendDao() {

    }

    @Override
    public List<AsellionManager.ProductResponse> getAllProducts() {
        throw new IllegalStateException(ERROR_MESSAGE);
    }

    @Override
    public AsellionManager.ProductResponse getProduct(int id) {
        throw new IllegalStateException(ERROR_MESSAGE);
    }

    @Override
    public void updateProduct(int id, AsellionManager.ProductRequest request) {
        throw new IllegalStateException(ERROR_MESSAGE);
    }

    @Override
    public void createProduct(AsellionManager.ProductRequest request) {
        throw new IllegalStateException(ERROR_MESSAGE);
    }
}
