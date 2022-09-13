package co.grandcircus.restapis.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.grandcircus.restapis.Models.Event;


public interface EventRepository extends MongoRepository<Event, String>{
	
	List<Event> findAll();
	Optional<Event> findById(String id);
	
	@Query ("{'users': ?0}")
	List<Event> findByUsers(String user); 
	
	@Query("{'startTime':{$gte: ?0, $lte:?1 }}")
	List<Event> findEventsUsingDateRange(LocalDateTime startDate, LocalDateTime endDate);
	
	
	@Query("{'users': {$all: ?0}}")
	List<Event>findEventByListOfUsers(List<String>users);
	

	
	@Query("{'users': {$all: ?0},'startTime':{$gte: ?1, $lte:?2}}")
	List<Event> findEventsByUsersAndDateRange(List<String>users, LocalDateTime startDate, LocalDateTime endDate);
}

