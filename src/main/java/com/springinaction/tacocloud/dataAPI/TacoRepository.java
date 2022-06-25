package com.springinaction.tacocloud.dataAPI;

import com.springinaction.tacocloud.domains.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
