package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    // api 통신시간
    //@JsonProperty("transaction_time")
    private LocalDateTime transactionTime;   // 어떤 포맷?  ISO, YYYY-MM-DD HH:mm:ss ...

    // api 응답 코드
    private String resultCode;

    // api 부가 설명명
    private String description;

    private T data;

    // OK
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder().transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build()
                ;
    }

    // DATA OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build()
                ;
    }

    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build()
                ;
    }
}









