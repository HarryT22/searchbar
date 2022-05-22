package com.searchbar.sweng.searchbar.outbound;


import com.searchbar.sweng.searchbar.model.Repositories.UserRepository;
import com.searchbar.sweng.searchbar.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends CrudRepository<User, Integer>, UserRepository {

    List<User> findByName(String name);


    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
