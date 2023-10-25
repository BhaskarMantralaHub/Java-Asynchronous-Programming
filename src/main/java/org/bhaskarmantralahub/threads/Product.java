package org.bhaskarmantralahub.threads;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private ProductDetails productDetails;
    private Review review;

}
