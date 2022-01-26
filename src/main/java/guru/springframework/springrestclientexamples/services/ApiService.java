package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}