package org.bhaskarmantralahub.threads;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;

@Getter
public class ProductInfoService implements Runnable {

    private final List<Pair<Integer, ProductDetails>> productList;
    private final int productId;

    private Pair<Integer, ProductDetails> productDetailsPair;

    public ProductInfoService(int productId) {
        this.productId = productId;
        productList = List.of(Pair.of(1, new ProductDetails("P1", 100)), Pair.of(2, new ProductDetails("P2", 200)));
    }

    public Pair<Integer, ProductDetails> getProduct(int productId) {

        fixedPause(1000);

        return productList
                .stream()
                .filter(product -> product.getLeft() == productId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id"));
    }

    @Override
    public void run() {
        productDetailsPair = getProduct(productId);
    }
}
