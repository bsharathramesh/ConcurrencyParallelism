package com.learnjava.completablefuture;

import com.learnjava.completablefuture.ProductServiceUsingCompletableFuture;
import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUsingCompletableFutureExceptionTest {
    @Mock
    private ProductInfoService pisMock;
    @Mock
    private ReviewService rsMock;
    @InjectMocks
    ProductServiceUsingCompletableFuture pscf;

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException("Exception occurred for reviewService !!"));
        Product product = pscf.retrieveProductDetails(productId);

        assertNotNull(product);
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
    }

}
