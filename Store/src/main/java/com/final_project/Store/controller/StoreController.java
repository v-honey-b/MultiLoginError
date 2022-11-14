package com.final_project.Store.controller;

import com.final_project.Store.StoreApplication;
import com.final_project.Store.dto.StoreRegisterFormDto;
import com.final_project.Store.entity.Store;
import com.final_project.Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/stores")
@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String storeRegisterForm(Model model) {
        model.addAttribute("storeRegisterFormDto", new StoreRegisterFormDto());

        return "store/storeRegisterForm";
    }

    @PostMapping(value = "/new")
    public String newStore(@Valid StoreRegisterFormDto storeRegisterFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "store/storeRegisterForm";
        }

        try {
            Store store = Store.createStore(storeRegisterFormDto, passwordEncoder);
            storeService.saveStore(store);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "store/storeRegisterForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginStore() {

        return "/store/storeLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요");
        return "/store/storeLoginForm";
    }


}
