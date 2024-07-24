package com.loco.demo.repository.HotelRepo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loco.demo.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, String> {
        @Query("SELECT h FROM Hotel h JOIN h.furniture f WHERE " +
                        "(:type IS NULL OR h.type = :type) AND " +
                        "(:name IS NULL OR h.name LIKE %:name%) AND " +
                        "(:minPrice IS NULL OR h.price >= :minPrice) AND " +
                        "(:maxPrice IS NULL OR h.price <= :maxPrice) AND " +
                        "(:bed IS NULL OR f.bed = :bed) AND " +
                        "(:bath IS NULL OR f.bath = :bath) AND " +
                        "(:car IS NULL OR f.car = :car) AND " +
                        "(:pet IS NULL OR f.pet = :pet) AND " +
                        "(:amenity IS NULL OR h.amenities LIKE %:amenity%) AND " +
                        "(:safety IS NULL OR h.safety LIKE %:safety%)" )
        public Page<Hotel> findHotelsByCriteria(@Param("type") Integer type,
                        @Param("name") String name,
                        @Param("minPrice") Integer minPrice,
                        @Param("maxPrice") Integer maxPrice,
                        @Param("bed") Integer bed,
                        @Param("bath") Integer bath,
                        @Param("car") Integer car,
                        @Param("pet") Integer pet,
                        @Param("amenity") String amenity,
                        @Param("safety") String safety,
                        Pageable pageable);

        @Query("SELECT h FROM Hotel h WHERE ( :slug IS NULL OR h.slug= :slug )")
        public Optional<Hotel> findHotelDetailByHotelSlug(String slug);
}
