package edu.upc.dsa.services;

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
    @ApiOperation(value = "Get user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/get/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("username") String username) {
        User t = this.gameInterface.getUser(username);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    /*@POST
    @ApiOperation(value = "Sign Up", notes = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "Username already exists"),

    })
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUp(CompleteCredentials cred) {
        int res = gameInterface.signUp(cred);
        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(200).entity(res).build();
    }


    @POST
    @ApiOperation(value = "Log In", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Incorrect username or password"),
            @ApiResponse(code = 409, message = "Unknown error")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials cred) {

        int res = gameInterface.logIn(cred.getUsername(), cred.getPassword());

        if(res == 0)
            return Response.status(200).build();
        else if(res == -1)
            return Response.status(404).build();

        return  Response.status(409).build();
    }

    @POST
    @ApiOperation(value = "Log Out", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")

    })
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logOut(Credentials cred) {

        int res = gameInterface.logOut(cred.getUsername());

        if(res == 0)
            return Response.status(200).build();
        else
            return Response.status(404).build();
    }*/

}
