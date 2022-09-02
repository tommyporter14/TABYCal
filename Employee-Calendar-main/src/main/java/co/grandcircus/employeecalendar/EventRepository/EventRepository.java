package co.grandcircus.employeecalendar.EventRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.employeecalendar.EventModel.Event;

public interface EventRepository extends MongoRepository<Event, String>{
	
	List<Event> findAll();
	Optional<Event> findById(String id);
}
