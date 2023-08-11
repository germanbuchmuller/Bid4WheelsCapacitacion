package com.example.demo.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private UUID uuid;
    private String name;
    private double price;
}