package org.bhaskarmantralahub.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemInCart {

    private String name;
    private int id;

    @Builder.Default
    private boolean isExpired = false;
    private double price;
    private int quantity;
}
