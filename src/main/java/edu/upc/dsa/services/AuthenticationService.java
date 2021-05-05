package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/auth")
@Path("/auth")
public class AuthenticationService {

    private GameInterface gameInterface;

    public AuthenticationService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Log In", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Incorrect password"),
            @ApiResponse(code = 409, message = "User not found")

    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String username, String password) {

        int res = gameInterface.logIn(username, password);

        if(res == 0)
            return Response.status(200).build();
        else if(res == -1)
            return Response.status(404).build();

        return Response.status(409).build();
    }

    @POST
    @ApiOperation(value = "Sign up", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Username already exists")
    })
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signup(String username, String password) {

        if(gameInterface.signUp(username, password) != null)
            return Response.status(200).build();

        return Response.status(404).build();
    }

}
