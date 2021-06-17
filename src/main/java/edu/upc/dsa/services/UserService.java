package edu.upc.dsa.services;

import com.google.gson.Gson;
import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Api(value = "/user")
@Path("/user")
public class UserService {

    private final GameInterface gameInterface;

    public UserService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get user", notes = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int ID) {
        User t = this.gameInterface.getUser(ID);
        if (t == null) return Response.status(404).build();
        else  return Response.status(200).entity(new Gson().toJson(t)).build();
    }

    @PUT
    @ApiOperation(value = "Update user", notes = "Update user's data (password not included)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/update")
    public Response updateUser(User user) {

        int res = this.gameInterface.updateUser(user.getId(), user.getUsername(), user.getFullName(), user.getEmail(), user.getMoney());

        if (res != 0) return Response.status(404).build();
        else return Response.status(200).build();
    }

    @PUT
    @ApiOperation(value = "Update password", notes = "Update user's password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/update/password")
    public Response updatePassword(User user) {

        int res = this.gameInterface.updateUserAttribute(user.getId(), "password", user.getPassword());

        if (res != 0) return Response.status(404).build();
        else return Response.status(200).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a user", notes = "Delete user by id (needed password)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/delete/{username}")
    public Response deleteUser(@PathParam("username") int ID) {
        int res = gameInterface.deleteUser(ID);
        if (res != 0) return Response.status(404).build();
        else return Response.status(200).build();
    }



    @GET
    @ApiOperation(value = "Get money", notes = "Get user money")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/money/{id}")
    public Response getMoney(@PathParam("id") int ID) {
        User t = this.gameInterface.getUser(ID);
        if (t == null) return Response.status(404).build();
        else return Response.status(200).entity(t.getMoney()).build();
    }
}
