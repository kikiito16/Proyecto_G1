package edu.upc.dsa.services;

import com.google.gson.Gson;
import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.FullObject;
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

    private GameInterface gameInterface;

    public ObjectService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get objects", notes = "Get objects by userID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = FullObject.class, responseContainer="List"),
    })
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects(@PathParam("id") int ID) {
        List<FullObject> objects = this.gameInterface.getUserObjects(ID);
        GenericEntity<List<FullObject>> entity = new GenericEntity<List<FullObject>>(objects) {};
        if (objects.size() == 0) return Response.status(404).build();
        else return Response.status(201).entity(entity).build();

    }
}
