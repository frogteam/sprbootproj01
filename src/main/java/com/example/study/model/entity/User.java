package com.example.study.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
//@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "account")
    private String account;

    private String email;

    private String phoneNumber;  // DB 필드명은 phone_number 인데,  자동으로 매칭이 되어준다!

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // User : OrderDetail <=> 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")  // mappedBy = "user" <-- OrderDetail 의 멤버변수 user 와 동일한 이름이어야 한다.
    private List<OrderDetail> orderDetailList;
}






