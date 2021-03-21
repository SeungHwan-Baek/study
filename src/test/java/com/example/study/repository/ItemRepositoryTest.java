package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;      //  Spring IoC에게 객체 LC 제어권을 넘겼기 때문에 가능

    @Test
    public void create(){

        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("노트북");
        item.setTitle("삼성 노트북 A100");
        item.setPrice(1500000);
        item.setContent("2021년형 삼성 노트북 입니다");
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
//        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);

    }

    @Test
    public void read(){

        Optional<Item> item = itemRepository.findById(1L);  // Optional<Item>은 item 이 있을수도 없을수도 있음
        Assert.assertTrue((item.isPresent()));              // assertTrue 를 활용하여, 존재하면 True 반환


    }
}
