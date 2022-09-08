package co.grandcircus.TABYCal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.TABYCal.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAll();

    List<User> findByAdminStatus(Boolean adminStatus);

    User findByUserName(String userName);


}
