package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.KangarooException;
import com.workintech.zoo.exceptions.KangarooValidations;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangarooMap;

    public KangarooController() {
        System.out.println("Kangaroo Controller created");
    }

    @PostConstruct
    public void init() {
        kangarooMap = new HashMap<>();
        System.out.println("Kangaroo Controller initialized");
    }

    @GetMapping
    public List<Kangaroo> findAll() {
        return kangarooMap.values().stream().toList();
    }


    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable Integer id) {
        KangarooValidations.isIdValid(id);
        return kangarooMap.get(id);
    }

    @PostMapping
    public Kangaroo saveKangaroo(@RequestBody Kangaroo kangaroo) {
        KangarooValidations.isKangarooCredentialValid(kangaroo);
        kangarooMap.put(kangaroo.getId(), kangaroo);
        return kangarooMap.get(kangaroo.getId());
    }


    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@RequestBody Kangaroo kangaroo, @PathVariable Integer id) {
        KangarooValidations.isKangarooNotExist(kangarooMap, id);
        KangarooValidations.isKangarooCredentialValid(kangaroo);
        kangarooMap.put(kangaroo.getId(), kangaroo);
        return kangarooMap.get(kangaroo.getId());
    }

    @DeleteMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable Integer id) {
        KangarooValidations.isKangarooNotExist(kangarooMap, id);
        return kangarooMap.remove(id);
    }
}
