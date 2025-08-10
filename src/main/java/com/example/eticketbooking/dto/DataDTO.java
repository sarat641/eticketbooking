package com.example.eticketbooking.dto;

import lombok.Data;

@Data
public class DataDTO<T> {
    private T item; // Generic type to hold any data item

}
