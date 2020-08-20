package com.example.study.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity  // order_detail
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // OrderDetail : User <=>  N : 1
    @ManyToOne
    private User user;  // <-> user_id

    // OrderDetail : Item <= > N : 1
    @ManyToOne
    private Item item;

}


















