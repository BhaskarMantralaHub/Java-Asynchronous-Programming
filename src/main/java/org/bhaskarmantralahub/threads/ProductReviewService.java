package org.bhaskarmantralahub.threads;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static org.bhaskarmantralahub.WaitUtil.fixedPause;

@Getter
public class ProductReviewService implements Runnable {

    List<Pair<Integer, Review>> productList;
    private final int productId;

    private Pair<Integer, Review> reviewForTheProduct;

    public ProductReviewService(int productId) {
        this.productId = productId;
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

    @Override
    public void run() {
        reviewForTheProduct = getReviewForTheProduct(productId);
    }
}
