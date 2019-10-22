package asellion.common;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import asellion.manager.AsellionManager;

public class RealBackendDao implements BackendDao {
    private final Algorithm algorithm; // inject the algorithm so that we can choose various variants
    private final JWTVerifier verifier; // inject so we can choose variants

    private static final int TOKEN_VERSION = 1; // This means the Jwt implementation that we are starting with

    RealBackendDao(String secretSignature) {
        try {
            this.algorithm = Algorithm.HMAC512(secretSignature);
            this.verifier = JWT.require(algorithm).build();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Override
    public List<AsellionManager.ProductResponse> getAllProducts() {
        return null;
    }

    @Override
    public AsellionManager.ProductResponse getProduct(int id) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public void updateProduct(int id, AsellionManager.ProductRequest request) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public void createProduct(AsellionManager.ProductRequest request) {
        throw new IllegalStateException("Not implemented yet");
    }
}
