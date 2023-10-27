package org.bhaskarmantralahub.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private int sessionId;
    private List<ItemInCart> itemsInCart;

}
