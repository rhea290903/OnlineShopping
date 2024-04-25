package com.gmail.merikbest2015.ecommerce.service.impl;

import com.gmail.merikbest2015.ecommerce.constants.ErrorMessage;
import com.gmail.merikbest2015.ecommerce.domain.Cloth;
import com.gmail.merikbest2015.ecommerce.dto.request.SearchRequest;
import com.gmail.merikbest2015.ecommerce.repository.ClothRepository;
import com.gmail.merikbest2015.ecommerce.service.ClothService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothServiceImpl implements ClothService {

    private final ClothRepository clothRepository;
    private final ModelMapper modelMapper;

    @Override
    public Cloth getClothById(Long clothId) {
        return clothRepository.findById(clothId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.CLOTH_NOT_FOUND));
    }

    @Override
    public List<Cloth> getPopularClothes() {
        List<Long> clothIds = Arrays.asList(26L, 43L, 46L, 106L, 34L, 76L, 82L, 85L, 27L, 39L, 79L, 86L);
        return clothRepository.findByIdIn(clothIds);
    }

    @Override
    public Page<Cloth> getClothesByFilterParams(SearchRequest request, Pageable pageable) {
        Integer startingPrice = request.getPrice();
        Integer endingPrice = startingPrice + (startingPrice == 0 ? 500 : 50);
        return clothRepository.getClothesByFilterParams(
                request.getPerfumers(),
                request.getGenders(),
                startingPrice,
                endingPrice,
                pageable);
    }

    @Override
    public Page<Cloth> searchClothes(SearchRequest request, Pageable pageable) {
        return clothRepository.searchClothes(request.getSearchType(), request.getText(), pageable);
    }
}
