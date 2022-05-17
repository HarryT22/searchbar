package com.searchbar.sweng.searchbar.model.Event;

import com.searchbar.sweng.searchbar.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {

    private Integer userId;
    private String name;
    private String email;

    public UserRegisteredEvent(User user){
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
