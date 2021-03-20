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
        // String sql = insert into user ( %s, %s .. ) value (account, email.. );
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-3333-2222");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        System.out.println("newUser :"+newUser);
    }

    @Test
    @Transactional
    public void read(){
        // findById로 null 이 나오는 경우를 대비하여 Optional 사용, 없어도 NullPointException 발생하지 않음
        Optional<User> user = userRepository.findByAccount("TestUser02");

        // userRepository에서 Id로 찾은 user가 있으면, selectUser에 넣고 그 값을 출력
        user.ifPresent(selectUser->{
//            System.out.println("user : "+selectUser);
//            System.out.println("email : "+selectUser.getEmail());

            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
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

