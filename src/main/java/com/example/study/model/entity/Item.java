package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String title;
    private String content;
    private Integer price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


    // 1: N = item : OrderDetail
    // LAZY : 객체 사용 시점에 SQL을 발생 시켜, 연관 엔티티 조회. 대부분 이 방식 사용
    // EAGER --> 1:1 , n :1 과 같이 1건만 존재할 때 가져오는 방식. 연관된 엔티지 즉시조회로 성능 이슈. 실무에서 사용 하지 않는다.
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
