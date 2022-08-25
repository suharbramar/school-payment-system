package io.bramcode.movie.moviecategoryservices.repository;

import io.bramcode.movie.moviecategoryservices.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    boolean existsById(Long aLong);
}