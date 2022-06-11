package com.springinaction.tacocloud.repositories;

import com.springinaction.tacocloud.domains.TacoOrder;
import java.util.Optional;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
