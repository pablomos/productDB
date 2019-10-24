package asellion.manager;

import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public interface AsellionManager {

    Response getAllProducts();
    ProductResponse getProduct(int id);
    void updateProduct(int id, ProductRequest request);
    void createProduct(ProductRequest request);

    final class ProductRequest {
        public final String name;
        public final double currentPrice; // Todo: migrate to Amount

        public ProductRequest(String name, double currentPrice) {
            this.name = name;
            this.currentPrice = currentPrice;
        }
    }

    final class ProductResponse {
        public final int id;
        private final String name;
        private final double currentPrice;
        private final Timestamp lastUpdate;

        public ProductResponse(int id, String name, double currentPrice, Timestamp lastUpdate) {
            this.id = id;
            this.name = name;
            this.currentPrice = currentPrice;
            this.lastUpdate = lastUpdate;
        }

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(id));
            map.put("name", name);
            map.put("currentPrice", String.valueOf(currentPrice));
            map.put("lastUpdate", lastUpdate.toString());
            return map;
        }
    }

    static AsellionManager build() {
        return new AsellionManagerImpl();
    }
}
