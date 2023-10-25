package org.bhaskarmantralahub.synchronous;

import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;

import static org.bhaskarmantralahub.WaitUtil.stopWatch;

@Log
public class ProductService {

    public Product getProductDetails(int productId) {
        stopWatch.start();
        ProductInfoService productService = new ProductInfoService();
        Pair<Integer, ProductDetails> product = productService.getProduct(productId);

        ProductReviewService productReviewService = new ProductReviewService();
        Pair<Integer, Review> reviewForTheProduct = productReviewService.getReviewForTheProduct(productId);
        stopWatch.stop();

        log.info("Total time taken to get product details " + stopWatch.getTime()); //INFO: Total time taken to get product details 2036
        return new Product(product.getRight(), reviewForTheProduct.getRight());
    }

    public static void main(String[] args) {

        ProductService productService = new ProductService();

        Product productDetails = productService.getProductDetails(1);

        log.info("Product is " + productDetails);

    }

}
