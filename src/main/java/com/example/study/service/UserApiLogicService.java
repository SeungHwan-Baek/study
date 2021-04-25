package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
/*
* CrudInterface의 함수는 반드시 Override 하여 정의 해야함 --> implements 했으니까
* */
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse,User> {


    @Override
    // UserApiRequest를 body로 갖는 Header를 매개변수로 받고, UserApiRespose를 가진 Header를 반환
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();      // .getData()를 통해 T가 반환되는데, T는 UserApiRequest
        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(userApiRequest.getStatus())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 --> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 1.Repository 에서 id로 찾기
        Optional<User> optional = baseRepository.findById(id);      // Null 일수 있어서 Optional로 받음

        // 찾은 User 를 반환
        return optional
                .map(user-> response(user))                         // Optional user 존재하면 userApiResponse 작성
                .orElseGet(                                         // null 일때만 실행 되는 orElseGet
                        ()->Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data를 가져옴 ( Header data )
        UserApiRequest userApiRequest = request.getData();
        // 2. id로 User 찾음
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3. update 실행
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            return user;
        })
                .map(user -> baseRepository.save(user))     // update 발생 후 새로운 user 객체 반환
                .map(updateUser -> response(updateUser))    // userApiResponse
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id 로 repository 에서 user 찾기
        Optional<User> optional = baseRepository.findById(id);
        // 2. repository 에서 delete
        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<UserApiResponse> response(User user){
        // user --> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }
}
