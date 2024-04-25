package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Cloth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

    List<Cloth> findByIdIn(List<Long> clothesIds);

    Page<Cloth> findAllByOrderByPriceAsc(Pageable pageable);

    @Query("SELECT cloth FROM Cloth cloth WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'clothTitle' THEN UPPER(cloth.clothTitle) " +
            "   WHEN :searchType = 'country' THEN UPPER(cloth.country) " +
            "   ELSE UPPER(cloth.perfumer) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY cloth.price ASC")
    Page<Cloth> searchClothes(String searchType, String text, Pageable pageable);

    @Query("SELECT cloth FROM Cloth cloth " +
            "WHERE (coalesce(:perfumers, null) IS NULL OR cloth.perfumer IN :perfumers) " +
            "AND (coalesce(:genders, null) IS NULL OR cloth.clothGender IN :genders) " +
            "AND (coalesce(:priceStart, null) IS NULL OR cloth.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY cloth.price ASC")
    Page<Cloth> getClothesByFilterParams(
            List<String> perfumers,
            List<String> genders,
            Integer priceStart,
            Integer priceEnd,
            Pageable pageable);
}
