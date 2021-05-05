package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Map;
import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

public class GameService {

    private GameInterface gameInterface;
    public GameService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Add a game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Error")
    })
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGame(String userId, Game g) {
        Game res=gameInterface.addGame(userId,g);
        if(res == null)
            return Response.status(400).build();

        return Response.status(200).entity(g).build();
    }

    @GET
    @ApiOperation(value = "Gets a game given it's id", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(int gameId) {
        Game g= gameInterface.getGame(gameId);
        if(g == null)
            return Response.status(404).build();
        return Response.status(200).entity(g).build();
    }

    @GET
    @ApiOperation(value = "Gets all games", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames() {
        List<Game> games = gameInterface.getAllGames();
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        if(games.size() == 0)
            return Response.status(404).build();
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Gets the map of a given game", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/getMap")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMapFromGame(Game gameId) {
        List<Map> maps = gameId.getMapsList();
        GenericEntity<List<Map>> entity = new GenericEntity<List<Map>>(maps) {};
        if(maps.size() == 0)
            return Response.status(404).build();
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Gets all games from a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found/no games")
    })
    @Path("/all/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGamesFromUser(String userId) {
        List<Game> games = gameInterface.getGamesByUser(userId);
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        if(games.size() == 0)
            return Response.status(404).build();

        return Response.status(200).entity(entity).build();
    }
}
