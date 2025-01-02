package com.apps.dev.cfl.tourOfHeroes.controllers;

import com.apps.dev.cfl.tourOfHeroes.entities.Hero;
import com.apps.dev.cfl.tourOfHeroes.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hero>> getHeroes(){
        return this.heroService.getHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id){
        return this.heroService.getHeroById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Hero>> getHeroesByName(@RequestParam(name = "name") String name){
        return this.heroService.getHeroByName(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hero> getHeroById(@RequestBody Hero newHero){
        return this.heroService.save(newHero);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hero> updateHero(@RequestBody Hero newHerosData){
        return this.heroService.updateHero(newHerosData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable("id") Long id){
        return this.heroService.deleteHero(id);
    }


}
