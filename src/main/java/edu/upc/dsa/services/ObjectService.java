package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
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

public class ObjectService {

    private GameInterface gameInterface;
    public ObjectService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Buy an object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 404, message = "Not enough money")
    })
    @Path("/buy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyObject(String objectId) {

        Object b= gameInterface.buyObject(objectId);

        if(b == null)   //Asumiendo que en la funcion de buyObject, si no tienes suficiente $$, te devuelve un null
            return Response.status(404).build();

        return Response.status(200).entity(b).build();
    }


    @POST
    @ApiOperation(value = "Picks an object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pickObject(String objectId) {
        Object o= gameInterface.addObject(objectId);
        if(o == null)   //Igual que el caso anterior
            return Response.status(404).build();

        return Response.status(200).entity(o).build();
    }


    @PUT        //En el UML se le dio el metodo UPDATE pero al parecer no existe
    @ApiOperation(value = "Uses an object", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/use")
    @Produces(MediaType.APPLICATION_JSON)
    public Response useObject(String objectId) {
        Object u= gameInterface.useObject(objectId);
        if(u == null)
            return Response.status(404).build();

        return Response.status(200).entity(u).build();
    }

    @GET
    @ApiOperation(value = "Returns all items", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listObjects() {
        List<Object> objects = gameInterface.getAllObjects();
        GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(objects) {};
        if(objects.size() == 0)
            return Response.status(404).build();

        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Returns items from a given user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found/no items")
    })
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUserObjects(String userId) {
        List<Object> objects = gameInterface.getUserObjects(userId);
        GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(objects) {};
        if(objects.size() == 0)
            return Response.status(404).build();

        return Response.status(200).entity(entity).build();
    }
}
