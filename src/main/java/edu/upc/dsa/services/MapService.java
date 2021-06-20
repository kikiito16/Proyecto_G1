package edu.upc.dsa.services;

import edu.upc.dsa.GameImpl;
import edu.upc.dsa.GameInterface;
import edu.upc.dsa.models.Map;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/map")
@Path("/map")
public class MapService {

    private final GameInterface gameInterface;

    public MapService()
    {
        this.gameInterface = GameImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get map", notes = "Get map by map ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Map.class),
            @ApiResponse(code = 404, message = "Map ID does not exist or unknown error")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMap(@PathParam("id") int ID) {
        Map map = this.gameInterface.getMap(ID);
        if (map == null) return Response.status(404).build();
        else {
            GenericEntity<Map> entity = new GenericEntity<Map>(map) {};
            return Response.status(200).entity(entity).build();
        }

    }
}
