package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.service.ClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ClothService clothService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("clothes", clothService.getPopularClothes());
        return Pages.HOME;
    }
}
