package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import lombok.experimental.Accessors;
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
        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        //User user = new User(account, password, status, email, phoneNumber, registeredAt, createdAt);

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // @Builder 사용
        // 필요한 것만 set
        User u = User.builder()
                .account(account)
                .password(password)
                //.status(status)
                .email(email)
                .build()
                ;


//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        // @Accessors(chain = true) 사용
        //user.setEmail().setPhoneNumber().setStatus()..
        //User u = new User().setAccount().setEmail().setPassword()..

        user.getOrderGroupList().forEach(orderGroup -> {
            System.out.println("-------------주문묶음-----------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총금액 : " + orderGroup.getTotalPrice());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());

            System.out.println("-------------주문상세-----------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());

                // JPA 연관관계를 이용한 설정. 이와같이 한번에 가져옿수 있다.
                // ★JPA 의 장점★
                // 객체들을 타고(?) 다니는데 복잡하게 쿼리 신경 쓸 필요가 없다.
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());

                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());

            });
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














