package org.bhaskarmantralahub.checkout;

import java.util.List;

import static org.bhaskarmantralahub.practice.WaitUtil.fixedPause;

public class PriceValidatorService {

    public boolean isPriceValid(ItemInCart item) {
        fixedPause(500);

        return !List.of(1, 2, 3, 4).contains(item.getId());

    }

}
