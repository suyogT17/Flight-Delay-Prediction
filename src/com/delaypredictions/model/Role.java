package com.delaypredictions.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role {
private Integer Role_ID;
private String Role_name;
private String Description;
private Set<User> User = new HashSet<User>(0);

@OneToMany(mappedBy="role")
public Set<User> getUserTable() {
	return User;
}
public void setUserTable(Set<User> userTable) {
	User = userTable;
}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column

public Integer getRole_ID() {
	return Role_ID;
}
public void setRole_ID(Integer role_ID) {
	Role_ID = role_ID;
}

@Column
public String getRole_name() {
	return Role_name;
}
public void setRole_name(String role_name) {
	Role_name = role_name;
}

@Column
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}


}
