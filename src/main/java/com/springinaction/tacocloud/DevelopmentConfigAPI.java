package com.springinaction.tacocloud;

import com.springinaction.tacocloud.dataAPI.TacoRepository;
import com.springinaction.tacocloud.domains.Ingredient;
import com.springinaction.tacocloud.domains.Taco;
import com.springinaction.tacocloud.domains.User;
import com.springinaction.tacocloud.repositories.IngredientRepository;
import com.springinaction.tacocloud.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DevelopmentConfigAPI {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder passwordEncoder,
                                        TacoRepository tacoRepository){
        return args -> {
            Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tomatoes= new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            Ingredient lettuce = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient salsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(tomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            userRepository.save(new User("Anna",
                    passwordEncoder.encode("password"),
                    "Anna Totamto", "Carmelkowa 123",
                    "Big City", "BG", "12345", "111-222-3334" ));

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);


            Taco taco2 = new Taco();
            taco2.setName("Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veggie");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepository.save(taco3);

        };
    }
}
