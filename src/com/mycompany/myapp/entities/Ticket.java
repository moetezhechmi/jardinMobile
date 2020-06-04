/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author AmiR
 */
public class Ticket {
    private int id;
    private String inc_id;
    private int user_id;
    private String ok;
    private String name;
    private String type;
    private String etat;
    private String createdby;
    private String createdat;
    private String urgence;
    private String description;

   
    
    
    
    
 
    
    
    
  

    


    
    
    

   
     


   

    public Ticket() {
    }

   public Ticket( String name, String type, String etat, String createdby,  String urgence, String description ,String inc_id) {
                this.name=name;
                        this.type=type;
                                this.etat=etat;
                                        this.createdby=createdby;
                                                                            this.urgence=urgence;
                                                        this.description=description;
                                                        this.inc_id=inc_id;






    }

    public Ticket(int id, String name, String type, String etat, String createdby, String createdat, String urgence, String description) {
        this.id=id;
                this.name=name;
                        this.type=type;
                                this.etat=etat;
                                        this.createdby=createdby;
                                                        this.createdat=createdat;
                                                                            this.urgence=urgence;
                                                        this.description=description;






    }

  




    
    
     


    

    

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInc_id() {
        return inc_id;
    }

    public void setInc_id(String inc_id) {
        this.inc_id = inc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }

   
   
    
}
