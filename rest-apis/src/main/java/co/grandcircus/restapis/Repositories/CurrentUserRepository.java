package co.grandcircus.restapis.Repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import co.grandcircus.restapis.Models.*;

public interface CurrentUserRepository extends MongoRepository<CurrentUser, String> {
    List<CurrentUser> findAll();
}
