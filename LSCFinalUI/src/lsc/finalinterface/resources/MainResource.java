package lsc.finalinterface.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.businesslogic.client.JaxWsHandlerResolver;
import lsc.businesslogic.ws.LSCLogic;
import lsc.finalinterface.logic.BaseLogic;
import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.rest.model.Statistic;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;


@Stateless
@LocalBean
@Path("/")
public class MainResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	
	@Path("/web")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public InputStream web() throws IOException
	{
		System.out.println("GET /web");
		File f = new File("WebContent/finalinterfaceWebApp.html");
	    //System.out.println("Il doc si trova nel percorso" + f.toURL());
		return new FileInputStream(f);
	}
	
	@Path("/css/kendo.common-material.min.css")
	@GET
	@Produces()
	public InputStream css1() throws IOException
	{
		File f = new File("WebContent/css/kendo.common-material.min.css");
		return new FileInputStream(f);
	}
	
	@Path("/css/kendo.material.min.css")
	@GET
	@Produces()
	public InputStream css2() throws IOException
	{
		File f = new File("WebContent/css/kendo.material.min.css");
		return new FileInputStream(f);
	}
	
	@Path("/js/jquery.min.js")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public InputStream js1() throws IOException
	{
		File f = new File("WebContent/js/jquery.min.js");
		return new FileInputStream(f);
	}
	
	@Path("js/kendo.all.min.js")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public InputStream js2() throws IOException
	{
		File f = new File("WebContent/js/kendo.all.min.js");
		return new FileInputStream(f);
	}
	
	
	
	// ----
	// user
	// ----
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserCollection getAllUsers() {
		return FinalInterfaceLogic.user.getAll(uriInfo);
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(User user) {
		return FinalInterfaceLogic.user.post(uriInfo, user);
	}
	
	@Path("/user/{user_id}")
	public UserResource getUser(@PathParam("user_id") int user_id) {
		return new UserResource(uriInfo, request, user_id);
	}
	
	
	
	// ---------
	// statistic
	// ---------
	
	@Path("/statistic")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Statistic getStatistic() {
		System.out.println("http get "+uriInfo.getPath());
		
		int authenticated_id = BaseLogic.authenticate(uriInfo);
		if( authenticated_id == 0 )
			//invalid credentials
			return null;
		
		int user_id = Integer.parseInt( uriInfo.getQueryParameters().getFirst("user_id") );
		String record_type = uriInfo.getQueryParameters().getFirst("record_type");
		String field_name = uriInfo.getQueryParameters().getFirst("field_name");
		String from = uriInfo.getQueryParameters().getFirst("from");
		String to = uriInfo.getQueryParameters().getFirst("to");
		Goal.Interval on_interval = Goal.Interval.valueOf( uriInfo.getQueryParameters().getFirst("on_interval") );
		Goal.Function function = Goal.Function.valueOf(  uriInfo.getQueryParameters().getFirst("function") );
		
		// contact BusinessLogic
		LSCLogic lscLogic = BusinessLogicClient.init();
		Statistic s = lscLogic.computeStatisticFor(user_id, record_type, field_name, from, to, on_interval, function);
		
		return s;
	}
	
	
	
	// ------
	// record
	// ------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordComplexCollection getAllRecords() {
		return FinalInterfaceLogic.record.getAll(uriInfo);
	}
	
	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(RecordComplex recordComplex) {
		return FinalInterfaceLogic.record.post(uriInfo, recordComplex);
	}
	
	@Path("record/{record_id}")
	public RecordResource getRecord(@PathParam("record_id") int record_id) {
		return new RecordResource(uriInfo, request, record_id);
	}
	
	
	
	// ----
	// goal
	// ----
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		return FinalInterfaceLogic.goal.getAll(uriInfo);
	}
	
	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(Goal goal) {
		return FinalInterfaceLogic.goal.post(uriInfo, goal);
	}
	
	@Path("goal/{goal_id}")
	public GoalResource getGoal(@PathParam("goal_id") int goal_id) {
		return new GoalResource(uriInfo, request, goal_id);
	}
	
	
	
	// --------
	// deadline
	// --------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollection getAllDeadlines() {
		return FinalInterfaceLogic.deadline.getAll(uriInfo);
	}
	
	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(Deadline deadline) {
		return FinalInterfaceLogic.deadline.post(uriInfo, deadline);
	}
	
	@Path("deadline/{deadline_id}")
	public DeadlineResource getDeadline(@PathParam("deadline_id") int deadline_id) {
		return new DeadlineResource(uriInfo, request, deadline_id);
	}
	
	
	
	// ------------
	// notification
	// ------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollection getAllNotifications() {
		return FinalInterfaceLogic.notification.getAll(uriInfo);
	}
	/*
	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(Notification notification) {
		return FinalInterfaceLogic.notification.post(uriInfo, notification);
		// TODO LogicService.re-calculate deadline about this record
	}
	*/
	@Path("notification/{notification_id}")
	public NotificationResource getNotification(@PathParam("notification_id") int notification_id) {
		return new NotificationResource(uriInfo, request, notification_id);
	}
	
	
	
	// ----
	// todo
	// ----
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollection getAllToDos() {
		return FinalInterfaceLogic.todo.getAll(uriInfo);
	}
	
	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDo todo) {
		return FinalInterfaceLogic.todo.post(uriInfo, todo);
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
