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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 1 : N  = User : OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")  // User와 1 :N 인 OrderDetail 상호 지정하고 매핑 변수 지정
    private List<OrderDetail> orderDetailList;             // 1:N 이므로 N개인 OrderDetail을 받도록 List로 지정

}
