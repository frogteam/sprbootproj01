package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // QueryMethod
    //  '쿼리문'을 '메소드'로 작성하는 기능.

    // findByXXX() 형태의 메소드 --> SELECT 수행
    //  findByXXX명 <=> xxx컬럼명 매칭
    // findByAccount(String account);  findByAccount(String acc); <-- 매개변수명은 중요하지 않다.

    // SELECT * FROM user WHERE account = ?
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    // SELECT * FROM user WHERE account = ? AND email = ?
    Optional<User> findByAccountAndEmail(String account, String email);



}























