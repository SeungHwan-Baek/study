package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
 * T타입(Genric type)을 data로 갖는 Heder 클래스 정의
 * 사용될때는 Header 형태로 사용하지만, 내부적으로 Request / Respose 형태로 Body 를 채운다
 */
public class Header<T> {

    // api 통신 시간
    private LocalDateTime transactionTime;
    // api 응답 코드
    private String resultCode;
    // api 부가 설명
    private String description;

    private T data;

    // OK
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    // ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
