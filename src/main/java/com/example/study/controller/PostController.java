package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // POST 방식은 HTML <Form>, ajax 검색 시  사용함
    // json, xml, multipart-form 등으로 사용 가능

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        // 입력받은 SearchParam 객체를 return
        return searchParam;
    }

}
