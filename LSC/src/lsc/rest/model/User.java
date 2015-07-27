package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The persistent class for the "user" database table.
 * 
 */
@XmlType(name = "user", propOrder = { "id", "name", "mail", "password", "birthdate" })
@XmlRootElement(name="user")
public class User extends Base {

	
	private int id;
	
	private String name;

	private String mail;
	
    private String birthdate;
	
	private String password;
	
	
	
	
	public User() { }
	
	
	
	
	
	@XmlElement(name="id")
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement(name="name")
	public String getName() { return this.name;	}
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="birthdate")
	public String getBirthdate() { return this.birthdate; }
	
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	
	
	@XmlElement(name="mail")
	public String getMail() { return this.mail; }
	
	public void setMail(String email) {	this.mail = email; }
	
	
	@XmlElement(name="password")
	public String getPassword() { return this.password;	}
	
	public void setPassword(String password) { this.password = password; }
	
	
	
	
	
	
	
	
	
	
}
