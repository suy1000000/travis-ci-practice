package idv.villebez.jaxrs;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import idv.villebez.exception.ValidateLogicException;
import idv.villebez.model.OffsetPagingModel;

@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService {
	
	private UserDAO userDao = new UserDAO();
	
	@GET
	public OffsetPagingModel<User> list(@QueryParam("offset") int offset,
			@QueryParam("limit") @DefaultValue("10") int limit) {
		return userDao.queryForPage(offset, limit);
	}
	
	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") String id) throws ValidateLogicException {
		return userDao.findById(id);
	}
	
	@POST
	public Response create(User user) {
		return Response.status(Status.CREATED).entity(userDao.save(user)).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		userDao.delete(id);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") String id, User user) throws ValidateLogicException {
		userDao.update(id, user);
		return Response.ok().build();
	}
}
