package co.grandcircus.restapis.Repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongodb.client.MongoDatabase;
import co.grandcircus.restapis.Models.CurrentUser;

public interface CurrentUserRepository extends MongoRepository<CurrentUser, String> {
    List<CurrentUser> findAll();
}
