package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.FullObject;
import edu.upc.dsa.models.Object;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

    /*@POST
    @ApiOperation(value = "Add object", notes = "Add object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User or object not found")
    })
    @Path("/add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addObject(List<Object> o, @PathParam("id") int ID) {
        int res = gameInterface.addObject(o, ID);

        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Buy object", notes = "Buy object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/buy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyObject(List<Object> o, @PathParam("id") int ID) {
        int res = gameInterface.buyObject(o, ID);

        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Use object", notes = "Use object for playerId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/use/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response useObject(Object o, @PathParam("id") int ID) {
        int res = gameInterface.useObject(o, ID);

        if(res == -1)
            return Response.status(404).build();
        else
            return Response.status(201).build();
    }*/
}
