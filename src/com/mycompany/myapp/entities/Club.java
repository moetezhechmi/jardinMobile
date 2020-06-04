/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author ASUS
 */
public class Club {
     
    private int id;

    private String nom;
    private String description;
    private int nbParticipe ;
    private String jardins;
    private String  nomImage ;
      
    java.util.Date dt = new  java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String dateC = sdf.format(dt);

    public Club(int id, String nom, String description, int nbParticipe, String jardins, String nomImage) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nbParticipe = nbParticipe;
        this.jardins = jardins;
        this.nomImage = nomImage;
    }


    public Club(int id, String nom, String description, int nbParticipe, String jardins, String nomImage,String dateC) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nbParticipe = nbParticipe;
        this.jardins = jardins;
        this.nomImage = nomImage;
        this.dateC=dateC;
    }

    public Club(int id,String nom, String description, String nomImage) {
        this.id=id;
        this.nom = nom;
        this.description = description;
             this.nomImage = nomImage;


        
    }

    public Club( String nom, String description, String nomImage,String dateC) {
        this.nom = nom;
        this.description = description;
        this.nomImage = nomImage;
         this.dateC=dateC;

    }
     
    
    
    

    public Club() {
    }

   

    
    

    public Club( String Nom, String Description, String nomImage) {
         this.nom = nom;
        this.description = description;
        this.nomImage = nomImage;
    }

    public Club(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Club(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Club(int id, String nom, String description, String nomImage,String dateC, int nbParticipe ) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.nomImage=nomImage;
        this.dateC=dateC;
         this.nbParticipe=nbParticipe;


    }

    public Club(int id, String Nom, String Description, String img, int participant) {
          this.id = id;
        this.nom = Nom;
        this.description = Description;
        this.nomImage=img;
                 this.nbParticipe=participant;

        
    }

    public Club(int id, String nom, String description, int nbParticipe, String nomImage) {
          this.id = id;
        this.nom = nom;
        this.description = description;
                         this.nbParticipe=nbParticipe;

        this.nomImage=nomImage;
    }

    public Club(String nom, String description, String dateC, String nomImage, String jardins) {
          this.nom = nom;
        this.description = description;
            this.nomImage=nomImage;
        this.dateC=dateC;
        this.jardins=jardins;
    }

    public Club(int id, String nom, String description, String nomImage,int nbParticipe, String jardins) {
        this.id = id;
        this.nom = nom;
        this.description = description;
          this.nomImage=nomImage;
          this.nbParticipe=nbParticipe;
                  this.jardins=jardins;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbParticipe() {
        return nbParticipe;
    }

    public void setNbParticipe(int nbParticipe) {
        this.nbParticipe = nbParticipe;
    }

    public String getJardins() {
        return jardins;
    }

    public void setJardins(String jardins) {
        this.jardins = jardins;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getDateC() {
        return dateC;
    }

    public void setDateC(String dateC) {
        this.dateC = dateC;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", nbParticipe=" + nbParticipe + ", jardins=" + jardins + ", nomImage=" + nomImage + ", dateC=" + dateC + '}';
    }
    
    
    
    
      
      
      
      

}