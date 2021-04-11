package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component  // Spring Container에게 Bean 등록 시킴, 개발자가 직접 작성한 클래스 등록 시킬때
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)    // Optional 로 해당 객체가 없어도 예외처리 하지 않음
    protected JpaRepository<Entity,Long> baseRepository;        // 각 서비스마다 필수 repository가 있는데, 이걸 공통화 한 기본 Repository
}
