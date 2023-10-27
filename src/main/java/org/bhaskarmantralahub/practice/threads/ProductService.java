package org.bhaskarmantralahub.practice.threads;

import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;

import static org.bhaskarmantralahub.practice.WaitUtil.stopWatch;

@Log
public class ProductService {

    public Product getProductDetails(int productId) throws InterruptedException {
        stopWatch.start();
        ProductInfoService productService = new ProductInfoService(productId);
        Thread productServiceThread = new Thread(productService);

        ProductReviewService productReviewService = new ProductReviewService(productId);
        Thread productReviewServiceThread = new Thread(productReviewService);

        productServiceThread.start();
        productReviewServiceThread.start();


        productServiceThread.join();
        productReviewServiceThread.join();

        Pair<Integer, ProductDetails> product = productService.getProductDetailsPair();
        Pair<Integer, Review> reviewForTheProduct = productReviewService.getReviewForTheProduct();
        stopWatch.stop();

        log.info("Total time taken to get product details " + stopWatch.getTime()); //INFO: Total time taken to get product details 1016
        return new Product(product.getRight(), reviewForTheProduct.getRight());
    }

    public static void main(String[] args) throws InterruptedException {

        ProductService productService = new ProductService();

        Product productDetails = productService.getProductDetails(1);

        log.info("Product is " + productDetails);

    }

}
