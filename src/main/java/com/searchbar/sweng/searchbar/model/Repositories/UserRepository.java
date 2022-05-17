package com.searchbar.sweng.searchbar.model.Repositories;



import com.searchbar.sweng.searchbar.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);
    List<User> findByName(String name);

}

