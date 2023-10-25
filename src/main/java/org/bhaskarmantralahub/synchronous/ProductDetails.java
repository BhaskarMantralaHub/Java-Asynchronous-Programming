package org.bhaskarmantralahub.synchronous;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetails {

    private String name;
    private double price;

}
