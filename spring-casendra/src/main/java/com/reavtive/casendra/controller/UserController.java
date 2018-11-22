package com.reavtive.casendra.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reavtive.casendra.model.User;
import com.reavtive.casendra.repo.UserRepository;

@RestController
public class UserController {

	 
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private CacheManager cacheManager;      // autowire cache manager



    @Cacheable(value="users")  // it will cache result and key name will be "employees"
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		return repository.findAll();
	}

	@GetMapping("/getUserFilterByAge/{age}")
	public List<User> getUserFilterByAge(@PathVariable int age) {
		return repository.findByAgeGreaterThan(age);
	}
	
	@CacheEvict(value = "users", allEntries=true)       // It will clear cache when new user save to database
	@RequestMapping(value = "clearCache", method=RequestMethod.GET)
    public User save( ) {
		User user1 = new User(8,"name","address",35);
        return repository.save(user1);
    }
	
	 // clear all cache using cache manager
    @RequestMapping(value = "clearCache")
    public void clearCache(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();
        }
    }
    
    @CachePut(value = "users")      //@CachePut updates the cache which is stored and execute the method.
	@RequestMapping(value = "clearPut", method=RequestMethod.GET)
    public User save1( ) {
		User user1 = new User(11,"name11","address11",35);
        return repository.save(user1);
    }
}