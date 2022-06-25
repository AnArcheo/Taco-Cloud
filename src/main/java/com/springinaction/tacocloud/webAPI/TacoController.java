package com.springinaction.tacocloud.webAPI;

import com.springinaction.tacocloud.dataAPI.TacoRepository;
import com.springinaction.tacocloud.domains.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

//    @GetMapping("/{id}") // request for /api/tacos/{id}
//    public Optional<Taco> tacoById(@PathVariable("id") Long id){
//        return tacoRepository.findById(id);
//    }

    @GetMapping("/{id}") // request for /api/tacos/{id}
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optionalTaco = tacoRepository.findById(id);

        if(optionalTaco.isPresent()){
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
