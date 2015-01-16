package lsc.localdatabase.parser;

import lsc.localdatabase.dao.GoalDao;
import lsc.localdatabase.dao.RecordDao;
import lsc.localdatabase.dao.UserDao;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineCollection;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalCollection;
import lsc.localdatabase.model.Link;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.model.NotificationCollection;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordCollection;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;
import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserCollection;


public class Parser {
	
	
	
	// User
	
	public static UserCollection parse(UserCollection entities){
		for(User x : entities.getList()) parse(x);
		return entities;
	}
	
	public static User parse(User entity){
		return entity;
	}
	
	public static UserCollection generate(UserCollection entities){
		for(User x : entities.getList()) generate(x);
		return entities;
	}
	
	public static User generate(User entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("records", entity._getUrl()+"/record") );
		entity.putLink( Link.create("goals", entity._getUrl()+"/goal") );
		entity.putLink( Link.create("notifications", entity._getUrl()+"/notification") );
		entity.putLink( Link.create("todos", entity._getUrl()+"/todo") );
		return entity;
	}
	
	
	
	// Record
	
	public static RecordCollection parse(RecordCollection entities){
		for(Record x : entities.getList()) parse(x);
		return entities;
	}
	
	public static Record parse(Record entity){
		entity.setUser( UserDao.getByUrl( entity.getLink("user").getHref() ) );
		return entity;
	}
	
	public static RecordCollection generate(RecordCollection entities){
		for(Record x : entities.getList()) generate(x);
		return entities;
	}
	
	public static Record generate(Record entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("user", entity.getUser()._getUrl() ) );
		entity.putLink( Link.create("datas", entity._getUrl()+"/data" ) );
		return entity;
	}
	
	
	
	// Data
	
	public static DataCollection parse(DataCollection entities){
		for(Data x : entities.getList()) parse(x);
		return entities;
	}
	
	public static Data parse(Data entity){
		entity.setRecord( RecordDao.getByUrl( entity.getLink("record").getHref() ) );
		return entity;
	}
	
	public static DataCollection generate(DataCollection entities){
		for(Data x : entities.getList()) generate(x);
		return entities;
	}
	
	public static Data generate(Data entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("record", entity.getRecord()._getUrl() ) );
		return entity;
	}
	
	
	
	// Goal
	
	public static GoalCollection parse(GoalCollection entities){
		for(Goal x : entities.getList()) parse(x);
		return entities;
	}
	
	public static Goal parse(Goal entity){
		entity.setUser( UserDao.getByUrl( entity.getLink("user").getHref() ) );
		return entity;
	}
	
	public static GoalCollection generate(GoalCollection entities){
		for(Goal x : entities.getList()) generate(x);
		return entities;
	}
	
	public static Goal generate(Goal entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("user", entity.getUser()._getUrl() ) );
		entity.putLink( Link.create("deadlines", entity._getUrl()+"/deadline" ) );
		return entity;
	}
	
	
	
	// Deadline
	
	public static DeadlineCollection parse(DeadlineCollection entities){
		for(Deadline x : entities.getList()) parse(x);
		return entities;
	}
	
	public static Deadline parse(Deadline entity){
		entity.setRecord( RecordDao.getByUrl( entity.getLink("record").getHref() ) );
		entity.setGoal( GoalDao.getByUrl( entity.getLink("goal").getHref() ) );
		return entity;
	}
	
	public static DeadlineCollection generate(DeadlineCollection entities){
		for(Deadline x : entities.getList()) generate(x);
		return entities;
	}
	
	public static Deadline generate(Deadline entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("record", entity.getRecord()._getUrl() ) );
		entity.putLink( Link.create("goal", entity.getGoal()._getUrl() ) );
		return entity;
	}
	
	
	
	// Notification
	
	public static NotificationCollection parse(NotificationCollection entities){
		for(Notification x : entities.getList()) parse(x);
		return entities;
	}
	
	public static Notification parse(Notification entity){
		entity.setUser( UserDao.getByUrl( entity.getLink("user").getHref() ) );
		return entity;
	}
	
	public static NotificationCollection generate(NotificationCollection entities){
		for(Notification x : entities.getList()) generate(x);
		return entities;
	}
	
	public static Notification generate(Notification entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("user", entity.getUser()._getUrl() ) );
		entity.putLink( Link.create("deadlines", entity._getUrl()+"/deadline" ) );
		return entity;
	}
	
	
	// ToDo
	
	public static ToDoCollection parse(ToDoCollection entities){
		for(ToDo x : entities.getList()) parse(x);
		return entities;
	}
	
	public static ToDo parse(ToDo entity){
		entity.setUser( UserDao.getByUrl( entity.getLink("user").getHref() ) );
		return entity;
	}
	
	public static ToDoCollection generate(ToDoCollection entities){
		for(ToDo x : entities.getList()) generate(x);
		return entities;
	}
	
	public static ToDo generate(ToDo entity){
		entity.putLink( Link.create("self", entity._getUrl() ) );
		entity.putLink( Link.create("user", entity.getUser()._getUrl() ) );
		return entity;
	}
	
	
	
	
	
	
	
	
	
}
