package com.springinaction.tacocloud.repositories;

import com.springinaction.tacocloud.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    // Not needed - extends CrudRepository
//    Iterable<Ingredient> findAll();
//    Optional<Ingredient> findById(String id);
//    Ingredient save(Ingredient ingredient);

}
