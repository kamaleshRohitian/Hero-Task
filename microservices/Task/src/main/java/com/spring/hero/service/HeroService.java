package com.spring.hero.service;

import com.spring.hero.entity.AuthResponse;
import com.spring.hero.entity.Hero;
import com.spring.hero.entity.User;
import com.spring.hero.entity.UserDetails;
import com.spring.hero.exception.IdException;
import com.spring.hero.repository.HeroRepository;
import com.spring.hero.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.*;

@Service
@Slf4j
public class HeroService {

    @Autowired
    private HeroRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    
    @Autowired
    HttpSession session;

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
    
    @Transactional
    public String addUser(UserDetails userDetails)
    {
    	try
    	{
    		 em.createNativeQuery("insert into users(username,password,authority) values(?,?,?)")
             .setParameter(1,userDetails.getUsername())
             .setParameter(2,userDetails.getPassword())
             .setParameter(3,userDetails.getAuthority())
             .executeUpdate();
    	}catch(Exception e)
    	{
    		throw new IdException("Something went wrong!..");
    	}
    	return "Your details has been added to db successfully!..";
    }
    
    /*
    //login authorization
    public String validate(User user,HttpSession session) {
        User userobj=null;
        RestTemplate restTemplate=new RestTemplate();
        try {
            userobj=restTemplate.postForObject("http://localhost:8090/authapp/login", user, User.class);
           // log.info("inside validate success");
        }
        catch(Exception e)
        {
           // log.info("inside validate error");
            String error="";
            error="Unable to login. please check your credentials.";
           // log.info("inside validate error2");
            return "failure";
        }

        session.setAttribute("token", userobj.getAuthToken());
       session.setAttribute("memberId", userobj.getUserid());
        return getWelcome(session.getAttribute("token").toString(),user);
    }

    public String getWelcome(String token,User user) {

        RestTemplate restTemplate=new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            @SuppressWarnings({ "rawtypes", "unchecked" })
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<AuthResponse> response = restTemplate.exchange("http://localhost:8090/authapp/validate", HttpMethod.GET, request, AuthResponse.class);
            AuthResponse account = response.getBody();
        } catch (Exception e) {
            String error="";
            error="Unable to login. please check your credentials.";
            throw new IdException(error);
        }
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getUserid()).toUri();
        return "success";

    }

    public String AddUser(User user,HttpSession session) {
        try
        {
            RestTemplate restTemplate1=new RestTemplate();
            User userobj=restTemplate1.postForObject("http://localhost:8090/authapp/adduser",user,User.class);
            String details="Hi "+user.getUname()+" Your details has been added to db successfully!..";
            return "signup";
        }catch(Exception e)
        {
            String details="UserId is Already in use!.. Try with another userID!..";
            return "sfailure";
        }
    }
    */
}