package com.learnjava.completablefuture;


import com.learnjava.completablefuture.ProductServiceUsingCompletableFuture;
import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceUsingCompletableFutureTest {
    ProductInfoService pis = new ProductInfoService();
    ReviewService rs = new ReviewService();
    ProductServiceUsingCompletableFuture piscf = new ProductServiceUsingCompletableFuture(pis, rs);

    @Test
    void retrieveProductDetails_Test() {

        String productId = "ABCD1234";

        Product product = piscf.retrieveProductDetails(productId);
        System.out.println("product: " + product);

        assertNotNull(product);
        assertFalse(product.getProductInfo().getProductOptions().isEmpty());
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2_Test() {
        String productId = "ABCD1234";
        startTimer();

        CompletableFuture<Product> product_cf = piscf.retrieveProductDetails_approach2(productId);

        product_cf.thenAccept((product -> {
            assertNotNull(product);
            assertFalse(product.getProductInfo().getProductOptions().isEmpty());
            assertNotNull(product.getReview());
        })).join();
        timeTaken();
    }


}
