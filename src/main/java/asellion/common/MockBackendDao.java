package asellion.common;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import asellion.manager.AsellionManager;

import static asellion.mock.controllers.AsellionController.DATA;

public class MockBackendDao implements BackendDao {

    @Override
    public List<AsellionManager.ProductResponse> getAllProducts() {
        return new ArrayList<>(DATA.values());
    }

    @Override
    public AsellionManager.ProductResponse getProduct(int id) {
        AsellionManager.ProductResponse productResponse = DATA.get(id);
        if (productResponse == null) {
            throw new IllegalArgumentException("No product for ID " + id);
        }
        return productResponse;
    }

    @Override
    public void updateProduct(int id, AsellionManager.ProductRequest request) {
        if (DATA.get(id) == null) {
            throw new IllegalArgumentException("You are trying to update a product that doesn't exist. Use the creation endpoint");
        }
        DATA.replace(id, new AsellionManager.ProductResponse(id, request.name, request.currentPrice, Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public void createProduct(AsellionManager.ProductRequest request) {
        int maxId = DATA.keySet().stream().max(Comparator.naturalOrder()).orElse(-1) + 1;
        DATA.put(maxId, new AsellionManager.ProductResponse(maxId, request.name, request.currentPrice, Timestamp.valueOf(LocalDateTime.now())));
    }
}
