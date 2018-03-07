package io.mart.executors.jersey_way;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserJerseyExecutor {

    @Autowired
    private UserJerseyApi api;

    public model.User createUser(model.User user) {
        log.info("EXECUTOR: creating user {}", user);
        return api.getUserApi().createUser(user);
    }

    public void deleteUsers() {
        log.info("Deleting all users");
        api.getUsersApi().deleteUsers();
    }
}
