package com.apps.dev.cfl.tourOfHeroes.repositories;

import com.apps.dev.cfl.tourOfHeroes.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    public List<Hero> findByNameContainingIgnoreCase(String name);

}
