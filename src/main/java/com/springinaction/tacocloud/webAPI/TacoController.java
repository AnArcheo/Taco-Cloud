package com.springinaction.tacocloud.webAPI;

import com.springinaction.tacocloud.dataAPI.TacoRepository;
import com.springinaction.tacocloud.domains.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json") // handle methods only if client sends a request with an 'Accept' header
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    private TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }
}
