package com.example.study.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Item_old {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    // Item : OrderDetail <= > 1 : N
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // FetchType 두가지 ★★
    //  LAZY   : 지연로딩
    //  EAGER  : 즉시 로딩

    // 생성되는 쿼리문이 다르다
    
    // LAZY 의 경우
    // '연관관계 설정한 테이블에 대해 지금 당장 로딩하진 않겠다'
    //  -> SELECT * FROM item WHERE id = ?
    
    // EAGER 의 경우.  join 발생
    //  '즉시 모든것(연관관계의 테이블들) 데이터도 로딩 하겠다'
    // -> select item0_.id as id1_0_0_, item0_.content as content2_0_0_, item0_.name as name3_0_0_, item0_.price as price4_0_0_, orderdetai1_.item_id as item_id3_1_1_, orderdetai1_.id as id1_1_1_, orderdetai1_.id as id1_1_2_, orderdetai1_.item_id as item_id3_1_2_, orderdetai1_.order_at as order_at2_1_2_, orderdetai1_.user_id as user_id4_1_2_, user2_.id as id1_2_3_, user2_.account as account2_2_3_, user2_.created_at as created_3_2_3_, user2_.created_by as created_4_2_3_, user2_.email as email5_2_3_, user2_.phone_number as phone_nu6_2_3_, user2_.updated_at as updated_7_2_3_, user2_.updated_by as updated_8_2_3_ from item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id where item0_.id=?
    // join..
    //  item_id = order_detail.item_id
    //  user_id = order_detail.user_id
    //  where item.id = ?


    // EAGER 는 ..
    // 테이블의 데이터의 양이 많은 경우  모든 테이블에 join 걸어 가져오기 때문에 성능저하 혹은 가져오지 못할 위험성

    // 연관관계 설정은 LAZY 추천

    // EAGER 는 1:1, M:1 관계 등 (즉, 한건만 발생할때 ) 에 추천

}























