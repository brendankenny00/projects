package com.ait.cars;

import java.net.URI;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.UriInfo;

import java.util.ArrayList;



@Path("/cars")
public class CarsResource {

	CarsDAO dao = new CarsDAO();
	
	@Context
	private UriInfo context;
	
	private String id, make;
	
	
	
	
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Cars>getXml(){
		List<Cars> cars=dao.findAll();
		
		for(Cars item: cars){
			List<Link>links = new ArrayList<>();
			
			Link linkSelf = new Link();
			linkSelf.setRel("self");
			linkSelf.setUri("/KennysCars/rest/cars/"+item.getId());
			
			Link linkDeletelink = new Link();
			linkDeletelink.setRel("delete");
			linkDeletelink.setUri("/KennysCars/rest/cars/"+item.getId()+"/");
			
			Link linkUpdatelink = new Link();
			linkUpdatelink.setRel("update");
			linkUpdatelink.setUri("/KennysCars/rest/cars/"+item.getId());
			
			
			
			links.add(linkSelf);
			links.add(linkDeletelink);
			links.add(linkUpdatelink);
			
			item.setLink(links);
			
	}
	return cars;
	
}
	

	
		
	@GET
	@Produces("text/html")
	public String getHtml(){
		List<Cars> cars=dao.findAll();
		String result = "<html lang=\"en\"><head><h1>KennysCars</head></h1>"
				+"<body>"
				+"<table style=\"width:55%\">\n"+
				"<table style=\"padding:15px\">\n"+
				"<table style=\"border:15px\">\n"+
				"<tr>\n"+
				 "<th>ID</th>\n"+
				 "<th>MAKE</th>\n"+
				 "<th>MODEL</th>\n"+
				 "<th>YEAR</th>\n"+
				 "<th>MILLEAGE</th>\n"+
				 "<th>NCT</th>\n"+
				 "<th>COLOUR</th>\n"+
				 "<th>ENGINE SIZE</th>\n"+
				 "</tr>\n";
		for(Cars item: cars){
			result = result +				 
					 "<tr>\n"+
					 "<td>"+item.getId()+"</td>\n"+
					 "<td>"+item.getMake()+"</td>\n"+
					 "<td>"+item.getModel()+"</td>\n"+
					 "<td>"+item.getYear()+"</td>\n"+
					 "<td>"+item.getMilleage()+"</td>\n"+
					 "<td>"+item.getNct()+"</td>\n"+
					 "<td>"+item.getColour()+"</td>\n"+
					 "<td>"+item.getEngineSize()+"</td>\n"+
					 "</tr>\n";
		}
		result = result + 					 
				"</table>"
				 +"</body></html>";
		return result;
		
	}
	
	
	
	
	

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doGetAsXml(@HeaderParam("If-modified-since") java.util.Date Ifmodified, @PathParam("id") String id){
		
		java.util.Date lastmodified = null;
		 Cars cs = null;
		try {
			cs = findById(id);
			String ts = cs.getTs();
			System.out.println(ts);
			String dateofTS = ts.substring(0, 10);
			//java.util.Date lastmodified = null;
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    lastmodified = (java.util.Date) df.parse(dateofTS);
		    System.out.println(lastmodified.toGMTString());
		    System.out.println(Ifmodified.toGMTString());
			
			if(!Ifmodified.equals(null)){
				if(Ifmodified.before(lastmodified)){
					
					cs.setLink(new ArrayList<Link>());
					
					Link linkSelf = new Link();
					linkSelf.setRel("self");
					linkSelf.setUri("/KennysCars/rest/cars/"+id);
					
					Link deleteLink = new Link();
					deleteLink.setRel("delete");
					deleteLink.setUri("/KennysCars/rest/cars/"+id);
					
					Link updateLink = new Link();
					updateLink.setRel("update");
					updateLink.setUri("/KennysCars/rest/cars/"+id);
					
					cs.getLink().add(linkSelf);
					cs.getLink().add(deleteLink);
					cs.getLink().add(updateLink);
					
					
					System.out.println(context.getBaseUri());
					
					
					return Response.ok().entity(cs).lastModified(lastmodified)
				
					.build();
				}
			}
		} catch (Exception e) {
			 cs = findById(id);
			cs.setLink(new ArrayList<Link>());
			
			Link linkSelf = new Link();
			linkSelf.setRel("self");
			linkSelf.setUri("/KennysCars/rest/cars/"+id);
			
			Link deleteLink = new Link();
			deleteLink.setRel("delete");
			deleteLink.setUri("/KennysCars/rest/cars/"+id);
			
			Link updateLink = new Link();
			updateLink.setRel("update");
			updateLink.setUri("/KennysCars/rest/cars/"+id);
			
			cs.getLink().add(linkSelf);
			cs.getLink().add(deleteLink);
			cs.getLink().add(updateLink);
			
			return Response.ok().entity(cs).build();
		}  

		
	
		
		return Response.notModified().entity(cs).lastModified(lastmodified)
				.build();
	}


	
	public Cars findById(@PathParam("id") String id) {
		System.out.println("findById"+id);
		return dao.findById(Integer.parseInt(id));
	}
	

	
	@DELETE @Path("{id}")
	public Response deleteAll(@PathParam("id") int id){
		dao.remove(id);
		
		return Response
				.noContent()
				.status(Response.Status.NO_CONTENT)
				.build();
		
	}
	
	@DELETE
	public Response deleteAllItems()
	{
		dao.deleteAllItems();
		
		return Response
				.noContent()
		.status(Response.Status.NO_CONTENT)
		.build();
	}
	
	@HEAD
	public Response doTheHeadVerb()
	{
		return Response
				.noContent()
				.status(Response.Status.OK)
				.build();
	}
	

	@OPTIONS
	public Response doOptions()
	{
		Set<String>allowedVerbs = new TreeSet<>();
		allowedVerbs.add("GET");
		allowedVerbs.add("POST");
		allowedVerbs.add("DELETE");
		allowedVerbs.add("HEAD");
		
		return Response
				.noContent()
				.header("Allow", "GET,POST,DELETE,PUT,HEAD")
				.language("XML,JSON")
				.build();
		
	}
	
	
	
	
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response create(Cars cars){
		System.out.println("creating car record");
		
		Cars carss = dao.create(cars);
		return Response.ok().entity(cars)
				
				.location(URI.create(context.getBaseUri() .toString()+ carss.getId()))
						.entity(carss)
				.build();
	}
	
	
	
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Cars update(Cars details) {
		dao.update(details);
		return details;
	}


}