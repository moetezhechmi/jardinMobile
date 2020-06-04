/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author AmiR
 */
public class Incident {
    private int id;
    private String name;
    private String description;
    private String createdby;
    private String dateinc;

    public Incident(int id, String name, String description, String createdby, String dateinc) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdby = createdby;
        this.dateinc = dateinc;
    }

    public Incident(String name, String description, String createdby, String dateinc) {
        this.name = name;
        this.description = description;
        this.createdby = createdby;
        this.dateinc = dateinc;
    }

    public Incident(int id, String name, String description, String createdby) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdby = createdby;
    }

    public Incident(String name, String description, String createdby) {
        this.name = name;
        this.description = description;
        this.createdby = createdby;
    }

    public Incident() {
    }

  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getDateinc() {
        return dateinc;
    }

    public void setDateinc(String dateinc) {
        this.dateinc = dateinc;
    }

    @Override
    public String toString() {
        return "Incident{" + "id=" + id + ", name=" + name + ", description=" + description + ", createdby=" + createdby + ", dateinc=" + dateinc + '}';
    }
    
    
    
}
