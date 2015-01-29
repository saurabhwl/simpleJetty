package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jsonServices")
public class API {

        @GET
        @Path("/print/{name}")
        @Produces(MediaType.APPLICATION_JSON)
        public Student produceJSON( @PathParam("name") String name ) {
            Student st = new Student(name, "Joe",22,1);
            return st;

        }

        @POST
        @Path("/send")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response consumeJSON( Student student ) {

            String output = student.toString();

            return Response.status(200).entity(output).build();
        }

        @GET
        @Path("/test")
        @Produces(MediaType.TEXT_PLAIN)
        public String test() {
            return "Test";
        }
}
