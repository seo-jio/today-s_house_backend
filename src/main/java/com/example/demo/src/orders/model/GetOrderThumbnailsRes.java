package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class GetOrderThumbnailsRes {
    List<OrderThumbnail> orderThumbnails;
}
