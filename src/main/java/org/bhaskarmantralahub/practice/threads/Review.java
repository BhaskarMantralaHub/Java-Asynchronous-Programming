package org.bhaskarmantralahub.practice.threads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private int rating;
    private String message;

}
