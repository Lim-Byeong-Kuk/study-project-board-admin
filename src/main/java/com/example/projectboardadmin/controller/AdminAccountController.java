package com.example.projectboardadmin.controller;

import com.example.projectboardadmin.dto.response.AdminAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/members")
@Controller
public class AdminAccountController {

    @GetMapping
    public String members(Model model) {
        return "admin/members";
    }

    @ResponseBody
    @GetMapping("/api/admin/members")
    public List<AdminAccountResponse> getMembers() {
        return List.of();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // @ResponseStatus는 응답 status 를 특정한 것으로 지정할 때 쓰인다. Delete 할 때는 204(NO_CONTENT) 를 많이 내려준다.
    @ResponseBody
    @DeleteMapping("/api/admin/members/{userId}")
    public void delete(@PathVariable String userId) {
    }


}
