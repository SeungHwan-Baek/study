package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data                   // lombok에서 get,set,생성자,hasCode ,toString 등을 만들어 줌
@AllArgsConstructor     // 모든 변수에 대한 생성자 생성
public class SearchParam {

    private String account;
    private String email;
    private int page;

    // { "accout" : "", "email" : "", "page" :0 }  JSON 형태 받기

}
