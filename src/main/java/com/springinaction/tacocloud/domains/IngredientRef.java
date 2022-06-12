package com.springinaction.tacocloud.domains;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table // this will be mapped automatically so there is no need for @Table
public class IngredientRef {
    // there is no identity column//

    private final String ingredient;
}
