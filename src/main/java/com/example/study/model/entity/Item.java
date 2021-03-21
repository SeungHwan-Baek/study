package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder    // Builder 패턴으로 객체 생성
@Accessors(chain = true)    // 생성된 객체에 .setXXX() 형태로 set 함수를 쩜연사자 연결해서 지정 할수 있게 해줌.
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

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // item : partner = N : 1
    @ManyToOne
    private Partner partner;

    // 1: N = item : OrderDetail
    // LAZY : 객체 사용 시점에 SQL을 발생 시켜, 연관 엔티티 조회. 대부분 이 방식 사용
    // EAGER --> 1:1 , n :1 과 같이 1건만 존재할 때 가져오는 방식. 연관된 엔티지 즉시조회로 성능 이슈. 실무에서 사용 하지 않는다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
