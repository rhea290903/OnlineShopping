package com.gmail.merikbest2015.ecommerce.service.impl;

import com.gmail.merikbest2015.ecommerce.domain.Cloth;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repository.ClothRepository;
import com.gmail.merikbest2015.ecommerce.service.CartService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final ClothRepository clothRepository;

    @Override
    public List<Cloth> getClothesInCart() {
        User user = userService.getAuthenticatedUser();
        return user.getClothList();
    }

    @Override
    @Transactional
    public void addClothToCart(Long clothId) {
        User user = userService.getAuthenticatedUser();
        Cloth cloth = clothRepository.getOne(clothId);
        user.getClothList().add(cloth);
    }

    @Override
    @Transactional
    public void removeClothFromCart(Long clothId) {
        User user = userService.getAuthenticatedUser();
        Cloth cloth = clothRepository.getOne(clothId);
        user.getClothList().remove(cloth);
    }
}
