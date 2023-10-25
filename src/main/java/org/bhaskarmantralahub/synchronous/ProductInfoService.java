package org.bhaskarmantralahub.synchronous;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;

public class ProductInfoService {

    List<Pair<Integer, ProductDetails>> productList;

    public ProductInfoService() {
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

}
