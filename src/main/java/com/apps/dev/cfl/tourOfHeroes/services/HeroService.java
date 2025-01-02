package com.apps.dev.cfl.tourOfHeroes.services;

import com.apps.dev.cfl.tourOfHeroes.entities.Hero;
import com.apps.dev.cfl.tourOfHeroes.repositories.HeroRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class HeroService {

    @Autowired
    private final HeroRepository repository;

    public ResponseEntity<List<Hero>> getHeroes() {
        try {
            List<Hero> heroes = repository.findAll();
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            String msg = String.format("Error fetching heroes: %s", e.getMessage());
            log.error(msg);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Hero> getHeroById(Long id) {
        Optional<Hero> heroOptional = repository.findById(id);
        return heroOptional.map(hero -> new ResponseEntity<>(hero, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Hero>> getHeroByName(String name){
        try {
            List<Hero> heroes = repository.findByNameContainingIgnoreCase(name);
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            String msg = String.format("Error fetching heroes by name: %s", e.getMessage());
            log.error(msg);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Hero> save(Hero newHero) {
        try {
            Hero heroSaved = repository.save(newHero);
            return new ResponseEntity<>(heroSaved, HttpStatus.OK);
        } catch (Exception e) {
            String msg = String.format("Error saving hero -%s-: %s", newHero, e.getMessage());
            log.error(msg);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Hero> updateHero(Hero newHerosData) {
        Optional<Hero> heroOptional = repository.findById(newHerosData.getId());
        if(heroOptional.isPresent()){
            Hero heroUpdated = repository.saveAndFlush(newHerosData);
            return new ResponseEntity<>(heroUpdated, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteHero(Long id){
        Optional<Hero> heroOptional = repository.findById(id);
        if(heroOptional.isPresent()){
            Hero hero = heroOptional.get();
            repository.delete(hero);
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
