package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Cloth;
import com.gmail.merikbest2015.ecommerce.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClothService {

    Cloth getClothById(Long clothId);

    List<Cloth> getPopularClothes();

    Page<Cloth> getClothesByFilterParams(SearchRequest searchRequest, Pageable pageable);

    Page<Cloth> searchClothes(SearchRequest searchRequest, Pageable pageable);
}
