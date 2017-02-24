package com.ait.cars;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;









@Path("/cars")
public class CarsResource {

	CarsDAO dao = new CarsDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Cars> findAll() {
		System.out.println("findAll");
		return dao.findAll();
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Cars findById(@PathParam("id") String id) {
		System.out.println("findById"+id);
		return dao.findById(Integer.parseInt(id));
	}
	
	@DELETE @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id){
		dao.remove(id);
		
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Cars create(Cars cars){
		System.out.println("creating car record");
		return dao.create(cars);
	}
	
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Cars update(Cars cars){
		System.out.println("updating cars: "+ cars.getMake());
		dao.update(cars);
		return cars;
	}
	
	
	
	
	@GET @Path("/search/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Cars> Search(@PathParam("id") String id){
		System.out.println("Searching for: " + id);
		return dao.Search(id);	
	}


}