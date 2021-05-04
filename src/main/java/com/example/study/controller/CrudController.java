package com.example.study.controller;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/*
* ItemApiController, OrderGroupApiController, .... Api Controller 반복적인 매핑을 줄이는 추상화 클래스
* CRUD 서비스 연동을 위한 반복적인 연결을 위함
* 추상클래스 없이, 각각의 Controller 에서 직접 Service method 매핑 해도 됨.
*/
@Component
public abstract class CrudController<Req,Res,Entity> implements CrudInterface <Req,Res>{

    // Service 연결을 위한 기본서비스
    @Autowired(required = false)
    protected BaseService<Req,Res,Entity> baseService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) { return baseService.create(request); }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
