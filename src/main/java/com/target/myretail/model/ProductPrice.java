package com.target.myretail.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ProductPrice {

    @NotNull(message="Price cannot be empty")
    @Positive
    private Double price;

    @NotNull(message="Currency code cannot be empty")
    @Size(min=3,message="currency code should have atleast 3 characters")
    private String currencyCode;
}
