package asellion.common;

import java.util.List;

import asellion.manager.AsellionManager;

public interface BackendDao {
    List<AsellionManager.ProductResponse> getAllProducts();
    AsellionManager.ProductResponse getProduct(int id);
    void updateProduct(int id, AsellionManager.ProductRequest request);
    void createProduct(AsellionManager.ProductRequest request);
}
