package com.springinaction.tacocloud.domains;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
//@Table // Optional -by default object is mapped to a table based on the domain class name -> here: "Taco_Order",
        // this can be left out or left without parameters; if prefer to map to a different table name, then specify
        // new table name, like: @Table("Taco_Cloud_Order")
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // designates the id property as being the identity for a TacoOrder
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;
    //delivery info
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip is required")
    private String deliveryZip;
    //payment info
    @CreditCardNumber(message = "Not valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL) // if order is deleted then all related tacos are also deleted
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
