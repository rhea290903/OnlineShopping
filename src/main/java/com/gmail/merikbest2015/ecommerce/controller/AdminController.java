package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.constants.Pages;
import com.gmail.merikbest2015.ecommerce.constants.PathConstants;
import com.gmail.merikbest2015.ecommerce.dto.request.ClothRequest;
import com.gmail.merikbest2015.ecommerce.dto.request.SearchRequest;
import com.gmail.merikbest2015.ecommerce.dto.response.UserInfoResponse;
import com.gmail.merikbest2015.ecommerce.service.AdminService;
import com.gmail.merikbest2015.ecommerce.utils.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ControllerUtils controllerUtils;

    @GetMapping("/clothes")
    public String getClothes(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getClothes(pageable));
        return Pages.ADMIN_CLOTHES;
    }

    @GetMapping("/clothes/search")
    public String searchClothes(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchClothes(request, pageable));
        return Pages.ADMIN_CLOTHES;
    }

    @GetMapping("/users")
    public String getUsers(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getUsers(pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/users/search")
    public String searchUsers(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchUsers(request, pageable));
        return Pages.ADMIN_ALL_USERS;
    }

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", adminService.getOrder(orderId));
        return Pages.ORDER;
    }

    @GetMapping("/orders")
    public String getOrders(Pageable pageable, Model model) {
        controllerUtils.addPagination(model, adminService.getOrders(pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/orders/search")
    public String searchOrders(SearchRequest request, Pageable pageable, Model model) {
        controllerUtils.addPagination(request, model, adminService.searchOrders(request, pageable));
        return Pages.ORDERS;
    }

    @GetMapping("/cloth/{clothId}")
    public String getCloth(@PathVariable Long clothId, Model model) {
        model.addAttribute("cloth", adminService.getClothById(clothId));
        return Pages.ADMIN_EDIT_CLOTH;
    }

    @PostMapping("/edit/cloth")
    public String editCloth(@Valid ClothRequest cloth, BindingResult bindingResult, Model model,
                              @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "cloth", cloth)) {
            return Pages.ADMIN_EDIT_CLOTH;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/clothes", adminService.editCloth(cloth, file));
    }

    @GetMapping("/add/cloth")
    public String addCloth() {
        return Pages.ADMIN_ADD_CLOTH;
    }

    @PostMapping("/add/cloth")
    public String addCloth(@Valid ClothRequest cloth, BindingResult bindingResult, Model model,
                             @RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (controllerUtils.validateInputFields(bindingResult, model, "cloth", cloth)) {
            return Pages.ADMIN_ADD_CLOTH;
        }
        return controllerUtils.setAlertFlashMessage(attributes, "/admin/clothes", adminService.addCloth(cloth, file));
    }

    @GetMapping("/user/{userId}")
    public String getUserById(@PathVariable Long userId, Model model, Pageable pageable) {
        UserInfoResponse userResponse = adminService.getUserById(userId, pageable);
        model.addAttribute("user", userResponse.getUser());
        controllerUtils.addPagination(model, userResponse.getOrders());
        return Pages.ADMIN_USER_DETAIL;
    }
}
