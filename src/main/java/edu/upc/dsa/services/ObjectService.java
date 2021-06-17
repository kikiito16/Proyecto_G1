package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.GameObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/object")
@Path("/object")
public class ObjectService {

    private final GameInterface gameInterface;

    public ObjectService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get objects", notes = "Get objects by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = FullObject.class, responseContainer="List")
    })
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects(@PathParam("id") int ID) {
        List<FullObject> objects = this.gameInterface.getUserObjects(ID);
        GenericEntity<List<FullObject>> entity = new GenericEntity<List<FullObject>>(objects) {};
        if (objects.size() == 0) return Response.status(404).build();
        else return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Add object", notes = "Add object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User or object not found")
    })
    @Path("/add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addObject(List<GameObject> o, @PathParam("id") int ID) {
        int res = gameInterface.addObject(o, ID);

        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Buy object", notes = "Buy object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "Unknown error"),
            @ApiResponse(code = 405, message = "Incorrect user id"),
            @ApiResponse(code = 409, message = "Not enough money")
    })
    @Path("/buy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyObject(List<GameObject> o, @PathParam("id") int ID) {
        int res = gameInterface.buyObject(o, ID);

        if(res == -1)
            return Response.status(404).build();
        else if(res == -2)
            return Response.status(405).build();
        else if(res == -3)
            return Response.status(409).build();
        else
            return Response.status(201).entity(res).build();
    }

    @POST
    @ApiOperation(value = "Sell object", notes = "Sell object of playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "Unknown error"),
            @ApiResponse(code = 405, message = "Incorrect user id or object id"),
            @ApiResponse(code = 409, message = "the quantity of objects that the user wants to sell is higher than the quantity he actually has")
    })
    @Path("/sell/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sellObject(GameObject o, @PathParam("id") int ID) {
        int res = gameInterface.sellObject(ID, o);

        if(res == -1)
            return Response.status(405).build();
        else if(res == -2)
            return Response.status(409).build();
        else if(res == -3)
            return Response.status(404).build();
        else
            return Response.status(201).entity(res).build();
    }

    @PUT
    @ApiOperation(value = "Use object", notes = "Use object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Error")
    })
    @Path("/use/{idObject}/user/{idUser}")
    public Response useObject(@PathParam("idObject") int idObject, @PathParam("idUser") int idUser) {
        int res = gameInterface.useObject(idObject, idUser);

        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get objects from the store", notes = "Get all objects of the store")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = FullObject.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Error")
    })
    @Path("/store")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreObjects() {

        List<FullObject> objects = this.gameInterface.getStoreObjects();
        GenericEntity<List<FullObject>> entity = new GenericEntity<List<FullObject>>(objects) {};
        if (objects == null) return Response.status(404).build();
        else return Response.status(200).entity(entity).build();
    }
}
