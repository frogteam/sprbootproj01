package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        //user.setId();   // AI
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
        //System.out.println("newUser : " + user);
    }

    @Test
    @Transactional
    public void read(){
        //Optional<User> user = userRepository.findById(4L);
        Optional<User> user = userRepository.findByAccount("TestUser03");

        user.ifPresent(selectedUser->{

            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });

//            System.out.println("user : " + selectedUser);
//            System.out.println("email : " + selectedUser.getEmail());
        });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectecUser->{
            selectecUser.setAccount("PPPP");
            selectecUser.setUpdatedAt(LocalDateTime.now());
            selectecUser.setUpdatedBy("update method()");

            userRepository.save(selectecUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectecUser->{
            userRepository.delete(selectecUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);

//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재 : " + deleteUser.get());
//        }else {
//            System.out.println("데이터 삭제됨. 데이터 업슴");
//        }

        Assert.assertFalse(deleteUser.isPresent());

    }


}














