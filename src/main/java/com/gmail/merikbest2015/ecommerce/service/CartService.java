package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Cloth;

import java.util.List;

public interface CartService {

    List<Cloth> getClothesInCart();

    void addClothToCart(Long clothId);

    void removeClothFromCart(Long clothId);
}
