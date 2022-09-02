package co.grandcircus.TABYCal.EventRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.TABYCal.EventModel.Event;

public interface EventRepository extends MongoRepository<Event, String>{
	
	List<Event> findAll();
	Optional<Event> findById(String id);
}

