package com.spring.hero.controller;

import com.spring.hero.entity.Hero;
import com.spring.hero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@ResponseBody
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    @Autowired
    private HeroService service;

    @GetMapping(value = "/heros")
    public ResponseEntity<List<Hero>> findAll()
    {

        return service.findAll();
    }

    @GetMapping(value = "/heros/{id}")
    public ResponseEntity<Hero> findById(@PathVariable int id)
    {

        return service.findById(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> save(@Valid  @RequestBody Hero hero)
    {
        return service.save(hero);
    }


    @PutMapping(value ="/update")
    public ResponseEntity<Object> update(@Valid @RequestBody Hero hero)
    {
        return service.update(hero);
    }


    @DeleteMapping(value = "/heros/remove/{id}")
    public ResponseEntity<Hero> deleteHero(@PathVariable int id)
    {
        return service.deleteHero(id);
    }

}
