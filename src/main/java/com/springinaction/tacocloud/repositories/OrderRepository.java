package com.springinaction.tacocloud.repositories;

import com.springinaction.tacocloud.domains.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
// Not needed - extends CrudRepository
//    TacoOrder save(TacoOrder order);
    //Additional method to basic CRUD methods
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
