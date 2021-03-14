package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController                 // Controller로 사용하겠다.
@RequestMapping("/api")      // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path ="/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter")    // http://localhost:8080/api/getParameter?id=1234&password=yyymmdd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password ="bbb";         // 내부 변수로 password를 사용하므로, @RequestParam 으로 다른 변수 pwd로 받음.
        System.out.println("id : "+id);
        System.out.println("password : "+pwd);

        return id+pwd;

    }

    // http://localhost:8080/api/getMultiParameter?account=abcd&email=test@naver.com&page=10
    // 파라미터가 길어지면 코딩 어려워 지므로, 객체로 정의해서 받도록 수정 : SearchParameter
    @GetMapping("/getMultiParameter")
    public SearchParam getMulitParamter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "accout" : "", "email" : "", "page" :0 }  받은 객체를 return 하면, JSON 으로 변환됨
        return searchParam;
    }
}
