package org.bhaskarmantralahub.threadpool_executorservice;

import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.concurrent.*;

import static org.bhaskarmantralahub.WaitUtil.stopWatch;

@Log
public class ProductService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Product getProductDetails(int productId) throws ExecutionException, InterruptedException, TimeoutException {
        stopWatch.start();
        ProductInfoService productService = new ProductInfoService();
        Future<Pair<Integer, ProductDetails>> product = executorService.submit(() -> productService.getProduct(productId));

        ProductReviewService productReviewService = new ProductReviewService();
        Future<Pair<Integer, Review>> reviewForTheProduct = executorService.submit(() -> productReviewService.getReviewForTheProduct(productId));
        executorService.shutdown();
        stopWatch.stop();

        log.info("Total time taken to get product details " + stopWatch.getTime()); //INFO: Total time taken to get product details 10
        return new Product(product.get(2, TimeUnit.SECONDS).getRight(), reviewForTheProduct.get().getRight());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ProductService productService = new ProductService();

        Product productDetails = productService.getProductDetails(1);

        log.info("Product is " + productDetails);

    }

}
