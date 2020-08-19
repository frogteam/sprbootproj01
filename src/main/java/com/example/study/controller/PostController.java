package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // POST : http 통신시 body 에 데이터 담아서 옴

    // 어케 POST 로?
    // HTML <form> 을 사용
    // ajax 검색

    // request 할때의 폼 다양
    // json, xml, multipart-form , text-plain

    //@RequestMapping(method = RequestMethod.POST, path="/postMethod")
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("patchMethod")
    public void patch(){

    }



}
