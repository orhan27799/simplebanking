package com.eteration.simplebanking.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eteration.simplebanking.common.GeneralEnumerationDefinitions;

@Entity
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users usersCredentials;

    @Column(name="role")
    private String role;

    public UserRole(){}

    public UserRole(GeneralEnumerationDefinitions.USER_ROLE user_role){
        this.role=user_role.getShortCode();
    }
    
    
}