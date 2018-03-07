package swagger.src.gen.java.api;

import model.User;

import javax.ws.rs.*;



@Path("/users")
public interface UsersService  {
    @DELETE
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public String deleteUsers();

    @GET
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public User getAllUsers();

}
