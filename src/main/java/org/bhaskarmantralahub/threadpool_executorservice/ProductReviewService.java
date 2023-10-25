package org.bhaskarmantralahub.threadpool_executorservice;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;

public class ProductReviewService {

    List<Pair<Integer, Review>> productList;

    public ProductReviewService() {
        productList = List.of(Pair.of(1, new Review(4, "Good")), Pair.of(2, new Review(3, "Ok ok")));
    }

    public Pair<Integer, Review> getReviewForTheProduct(int productId) {
        fixedPause(1000);

        return productList
                .stream()
                .filter(product -> product.getLeft() == productId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id"));
    }

}
