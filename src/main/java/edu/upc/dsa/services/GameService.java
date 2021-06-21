package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.Game;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/game")
@Path("/game")
public class GameService {

    private final GameInterface gameInterface;

    public GameService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get games", notes = "Get games by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List")
    })
    @Path("/get/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGamesForUser(@PathParam("id") int ID) {
        List<Game> games = this.gameInterface.getAllGamesOf(ID);
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        if (games.size() == 0) return Response.status(404).build();
        else return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get game", notes = "Get games by gameID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class)
    })
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@PathParam("id") int ID) {
        Game game = this.gameInterface.getGame(ID);
        GenericEntity<Game> entity = new GenericEntity<Game>(game) {};
        if (game == null) return Response.status(404).build();
        else return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Add game", notes = "Add game for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @Path("/add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGame(Game game, @PathParam("id") int ID) {
        int res = gameInterface.addGame(ID, game.getDuration(), game.getVictory(), game.getScore());

        if(res == -1)
            return Response.status(404).build();
        else if(res == -2)
            return Response.status(500).build();
        else
            return Response.status(201).entity(res).build();
    }
}
