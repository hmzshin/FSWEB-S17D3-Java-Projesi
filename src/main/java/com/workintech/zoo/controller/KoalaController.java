package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.validations.KoalaValidations;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalaMap;

    public KoalaController() {
        System.out.println("Koala Controller created");
    }

    @PostConstruct
    public void init() {
        koalaMap = new HashMap<>();
        System.out.println("Koala Controller initialized");
    }

    @GetMapping
    public List<Koala> findAll() {
        return koalaMap.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala findById(@PathVariable Integer id) {
        KoalaValidations.isIdValid(id);
        return koalaMap.get(id);
    }

    @PostMapping
    public Koala saveKoala(@RequestBody Koala koala) {
        KoalaValidations.isKoalaCredentialValid(koala);
        koalaMap.put(koala.getId(), koala);
        return koalaMap.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@RequestBody Koala koala, @PathVariable Integer id) {
        KoalaValidations.isIdValid(id);
        KoalaValidations.isKoalaCredentialValid(koala);
        koalaMap.put(koala.getId(), koala);
        return koalaMap.get(koala.getId());
    }

    @DeleteMapping("/{id}")
    public Koala updateKoala(@PathVariable Integer id) {
        KoalaValidations.isIdValid(id);
        return koalaMap.remove(id);
    }
}
