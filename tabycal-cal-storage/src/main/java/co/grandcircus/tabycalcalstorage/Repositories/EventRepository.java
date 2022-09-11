package co.grandcircus.tabycalcalstorage.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.grandcircus.tabycalcalstorage.Models.Event;


public interface EventRepository extends MongoRepository<Event, String>{
	
	List<Event> findAll();
	Optional<Event> findById(String id);
	
	@Query ("{'users': ?0}")
	List<Event> findByUsers(String user); 
}

