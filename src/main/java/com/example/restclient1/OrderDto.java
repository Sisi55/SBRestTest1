package com.example.restclient1;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private String orderNo;
    private Long amount;
    private LocalDateTime orderDateTime;

    @Builder
    public OrderDto(String orderNo, Long amount, LocalDateTime orderDateTime){
        this.orderNo = orderNo;
        this.amount = amount;
        this.orderDateTime = orderDateTime;
    }
}
