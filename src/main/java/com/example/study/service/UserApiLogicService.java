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
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse 를 만들어서 리턴
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build()
                ;

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse 를 리턴
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        // user -> userApiResponse return
        return optional
                .map(user ->
                    response(user)
                )
                .orElseGet(() ->
                    Header.ERROR("데이터 없슴")
                )
                ;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            // 3. update
            // id 가 담겨 있다.
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                    ;
            // 4. userApiReponse 리턴턴
            return user;

        })
                .map(user -> userRepository.save(user))  // update
                .map(updateUser -> response(updateUser))  // userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없슴"));
    }

   @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
       Optional<User> optional = userRepository.findById(id);

       // 2. repository -> delete
       return optional.map(user -> {
           userRepository.delete(user);
           return Header.OK();
       })
               .orElseGet(()->Header.ERROR("데이터 없슴"));


       // 3. response return

    }

    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO 암호화 하거나 길이등 다른 방법으로 response
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build()
                ;

        // Header + data => 리턴
        return Header.OK(userApiResponse);


    }
}




























