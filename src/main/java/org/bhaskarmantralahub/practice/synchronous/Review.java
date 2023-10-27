package org.bhaskarmantralahub.practice.synchronous;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private int rating;
    private String message;

}
