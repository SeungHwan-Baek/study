package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"item","user"})     // OrderDeail 에도 Item,User가 있고, User,Item에도 OrderDetail이 변수로 있어서 상호 ToString 무한 반복하여 overflow 발생하는것을 방지
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // N : 1 = OrderDetail : User
    @ManyToOne
    private User user;      // OrderDetail 과 N : 1 관계인 User를 지정

    // N : 1 = OrderDetail : User
    @ManyToOne
    private Item item;      // OrderDetail 과 N : 1 관계인 Item을 지정

}
