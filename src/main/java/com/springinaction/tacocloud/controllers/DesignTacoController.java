package com.springinaction.tacocloud.controllers;

import com.springinaction.tacocloud.domains.Ingredient;
import com.springinaction.tacocloud.domains.Ingredient.Type;
import com.springinaction.tacocloud.domains.Taco;
import com.springinaction.tacocloud.domains.TacoOrder;
import com.springinaction.tacocloud.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();

        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){ // creates empty Taco Object
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){ //handles GET request for /design
        return "design";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing Taco: {}", taco);
        return "redirect:/orders/current";
    }
}
