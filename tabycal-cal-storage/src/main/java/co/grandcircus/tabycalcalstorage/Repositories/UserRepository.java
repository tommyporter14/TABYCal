package co.grandcircus.tabycalcalstorage.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.tabycalcalstorage.Models.User;



public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAll();

    List<User> findByAdminStatus(Boolean adminStatus);

    Optional<User> findByUserName(String userName);


}
