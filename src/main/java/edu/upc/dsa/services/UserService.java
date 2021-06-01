package edu.upc.dsa.services;

import com.google.gson.Gson;
import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.api.CompleteCredentials;
import edu.upc.dsa.models.api.Credentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Api(value = "/user")
@Path("/user")
public class UserService {

    private GameInterface gameInterface;

    public UserService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get user", notes = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("username") int ID) {
        User t = this.gameInterface.getUser(ID);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(new Gson().toJson(t)).build();
    }

    @PUT
    @ApiOperation(value = "Update user", notes = "Update user's data (password not included)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/update")
    public Response updateUser(User user) {

        int res = this.gameInterface.updateUser(user.getId(), user.getUsername(), user.getFullName(), user.getEmail(), user.getMoney());

        if (res != 0) return Response.status(404).build();
        else return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Update password", notes = "Update user's password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/update/password")
    public Response updatePassword(User user) {

        int res = this.gameInterface.updateUserAttribute(user.getId(), "password", user.getPassword());

        if (res != 0) return Response.status(404).build();
        else return Response.status(201).build();
    }
}
