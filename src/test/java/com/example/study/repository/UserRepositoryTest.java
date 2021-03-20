package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;      // Optional 객체 사용 read()

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired  // Spring DI(Dependency Injection) , 객체를 만들지 않고 Spring 이 의존성을 주입 시킴
    private UserRepository userRepository;

    @Test
    public void create(){

        String account = "Test01";
        String password = "test01password";
        String status = "REGISTERED";
        String email = "Test01@naver.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        Assert.assertNotNull(user);

    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("updated method()");

            userRepository.save(selectUser);
            // create 와 동일하게 사용했지만, id가 존재하면 JPA 가 update 하고 없으면 insert 하기 때문에 가능
        });

    }

    @Test
    @Transactional      // Test 코드는 실행되지만, Database 수행결과는 Rollback
    public void delete(){
        Optional<User> user = userRepository.findById(4L); // id로 user를 찾음

        Assert.assertTrue(user.isPresent());       // junit Assert 사용하여, 삭제 대상 존재여뷰 체크

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(4L);

        Assert.assertFalse(deleteUser.isPresent());     // junit Assert 사용하여, 삭제된 대상 존재여뷰 체크

        //  다른 형태로 조회된 데이터가 있는지 체크 하는 로직
//        if(deleteUser.isPresent()){     // 데이터가 존재하는지 체크
//            System.out.println("데이터가 존재 : "+deleteUser.get());
//        }else {
//            System.out.println("데이터 존재 하지 않음");
//        }
    }
}

