package swagger.src.gen.java.api;

import model.User;

import javax.ws.rs.*;



@Path("/user")
public interface UserService  {
    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public User createUser(User body);

    @GET
    @Path("/{name}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public User getUserByName( @PathParam("name") String name);

}
