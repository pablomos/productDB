package asellion.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import asellion.manager.AsellionManager;

public class MockBackendDao implements BackendDao {

    @Override
    public List<AsellionManager.ProductResponse> getAllProducts() {
        return Collections.singletonList(getProduct(0));
    }

    @Override
    public AsellionManager.ProductResponse getProduct(int id) {
        return new AsellionManager.ProductResponse(
            17,
            "mockProduct",
            10.,
            Timestamp.valueOf(LocalDateTime.now())
        );
    }

    @Override
    public void updateProduct(int id, AsellionManager.ProductRequest request) {
        // Do nothing
    }

    @Override
    public void createProduct(AsellionManager.ProductRequest request) {
        // Do nothing
    }
}
