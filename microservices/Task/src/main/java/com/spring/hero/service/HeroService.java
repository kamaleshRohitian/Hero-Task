package com.spring.hero.service;

import com.spring.hero.entity.Hero;
import com.spring.hero.exception.IdException;
import com.spring.hero.repository.HeroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;

@Service
@Slf4j
public class HeroService {

    @Autowired
    private HeroRepository repository;

    @Autowired
    private EntityManager em;

    @Transactional
    public ResponseEntity<List<Hero>> findAll()
    {
        List<Hero> heros = repository.findAll();
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(heros).toUri();
        return  ResponseEntity.ok().body(heros);
        //return heros;
    }
    @Transactional
    public ResponseEntity<Hero> findById(int id)throws IdException
    {
        if (id == 0) {
            throw new IdException("Id must not be null");
        }
        List<Hero> heros=repository.findAll();
        for(Hero h:heros)
        {
            if(h.getId()==id) {
                Hero get= repository.findById(id).get();
                URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();
                return ResponseEntity.created(location).body(get);
            }
        }
        throw new IdException(id+" is not present in db");
    }

    @Transactional
    public ResponseEntity<Object> save(Hero hero) throws IdException
    {
        List<Hero> heros=repository.findAll();
        Iterator<Hero> it=heros.listIterator();
        while(it.hasNext())
        {
            Hero h=it.next();
            if(h.getId()==hero.getId())
            {
                //throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"A Hero must have different ID!...");
                 throw new IdException("A Hero must have different ID!...");
            }
            if( h.getHero().equals(hero.getHero()))
            {
                throw new IdException("A Hero must have different Name!...");
                //throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"A Hero must have different Name!...");
            }
        }
         em.createNativeQuery("insert into hero(id,hero) values(?,?)")
                .setParameter(1,hero.getId())
                .setParameter(2,hero.getHero())
                .executeUpdate();
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(hero.getHero()).toUri();
        return ResponseEntity.created(location).body(hero);

    }
    @Transactional
    public ResponseEntity<Object> update(Hero hero) {
        Hero obj=repository.save(hero);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(hero.getHero()).toUri();
        return ResponseEntity.created(location).body(hero);
    }

    @Transactional
    public ResponseEntity<Hero> deleteHero(int id)throws  IdException {
        if (id == 0) {
            throw new IdException("Id must not be null");
        }
        List<Hero> heros=repository.findAll();
        for(Hero h:heros)
        {
            if(h.getId()==id) {
                repository.deleteById(id);
                URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri();
                return ResponseEntity.created(location).build();
            }
        }
        throw new IdException(id+" is not present in db");

    }
}