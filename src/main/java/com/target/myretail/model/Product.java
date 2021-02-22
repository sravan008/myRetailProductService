package com.target.myretail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

@Data
@ToString
@AllArgsConstructor
public class Product {

    @Id
    @NotNull
    public String id;

    @NotNull
    private String name;

    ProductPrice productPrice;






}
