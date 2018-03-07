package io.mart.executors.jersey_way;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.springframework.stereotype.Service;
import swagger.src.gen.java.api.UserService;
import swagger.src.gen.java.api.UsersService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Service
public class UserJerseyApi {
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target("http://localhost:8080");

    public UserService getUserApi() {
        return WebResourceFactory.newResource(UserService.class, webTarget);
    }

    public UsersService getUsersApi() {
        return WebResourceFactory.newResource(UsersService.class, webTarget);
    }
}
