package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Cloth;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.request.ClothRequest;
import com.gmail.merikbest2015.ecommerce.dto.request.SearchRequest;
import com.gmail.merikbest2015.ecommerce.dto.response.MessageResponse;
import com.gmail.merikbest2015.ecommerce.dto.response.UserInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    Page<Cloth> getClothes(Pageable pageable);

    Page<Cloth> searchClothes(SearchRequest request, Pageable pageable);

    Page<User> getUsers(Pageable pageable);

    Page<User> searchUsers(SearchRequest request, Pageable pageable);

    Order getOrder(Long orderId);

    Page<Order> getOrders(Pageable pageable);

    Page<Order> searchOrders(SearchRequest request, Pageable pageable);

    Cloth getClothById(Long clothId);

    MessageResponse editCloth(ClothRequest clothRequest, MultipartFile file);

    MessageResponse addCloth(ClothRequest clothRequest, MultipartFile file);

    UserInfoResponse getUserById(Long userId, Pageable pageable);
}
