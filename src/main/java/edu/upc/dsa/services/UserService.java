package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/user")
@Path("/user")
public class UserService {

    private GameInterface gameInterface;

    public UserService()
    {
        gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {

        Player p = gameInterface.getUser(username);

        if(p != null)
            return Response.status(200).entity(p).build();

        return Response.status(404).build();
    }


    @DELETE
    @ApiOperation(value = "Delete a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 409, message = "Incorrect password")
    })
    @Path("/delete")
    public Response delete(String username, String password) {

        int deleted = gameInterface.deletePlayer(username, password);

        if(deleted == 0)
            return Response.status(200).build();
        else if(deleted == -1)
            return Response.status(409).build();
        else
            return Response.status(404).build();
    }


}
