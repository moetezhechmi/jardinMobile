/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ASUS
 */
public class activite {
    private int id;
    private String nomact;
    private String clubs ;
    private String jardins;
    private String nomImg;

    public activite() {
    }

    public activite(int id, String nomact, String clubs, String jardins, String nomImg) {
        this.id = id;
        this.nomact = nomact;
        this.clubs = clubs;
        this.jardins = jardins;
        this.nomImg = nomImg;
    }

    public activite(int id, String nomact) {
        this.id = id;
        this.nomact = nomact;
    }

    public activite(String nomact) {
        this.nomact = nomact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomact() {
        return nomact;
    }

    public void setNomact(String nomact) {
        this.nomact = nomact;
    }

    public String getClubs() {
        return clubs;
    }

    public void setClubs(String clubs) {
        this.clubs = clubs;
    }

    public String getJardins() {
        return jardins;
    }

    public void setJardins(String jardins) {
        this.jardins = jardins;
    }

    public String getNomImg() {
        return nomImg;
    }

    public void setNomImg(String nomImg) {
        this.nomImg = nomImg;
    }

    public activite(int id, String nomact, String nomImg) {
        this.id = id;
        this.nomact = nomact;
        this.nomImg = nomImg;
    }

    
    
    
    @Override
    public String toString() {
        return "activite{" + "id=" + id + ", nomact=" + nomact + ", clubs=" + clubs + ", jardins=" + jardins + ", nomImg=" + nomImg + '}';
    }
    
    
    
    
}
