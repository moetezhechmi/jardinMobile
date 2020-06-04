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
 * @author Khaled
 */
public class enfant {
     private int id;
      private int parent;
    private String nomenf;
    private String prenomenf;  
    private String sexe ;
    java.util.Date dt = new java.util.Date();
   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date_de_naissance = sdf.format(dt);
    private String image ;
    private String etat;
    private String jardin;

    public enfant() {
    }

    public enfant(String nomenf, String prenomenf, String sexe, String image, String etat) {
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.image = image;
        this.etat = etat;
    }
    
    
    

    public enfant(int id, String nomenf, String prenomenf, String sexe, String date_de_naissance ,String image, String etat) {
        this.id = id;
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.image = image;
        this.etat = etat;
    }

    public enfant(String nomenf, String prenomenf, String sexe, String date_de_naissance ,String image, String etat) {
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.image = image;
        this.etat = etat;
    }

    public enfant(int id, int parent ,String nomenf, String prenomenf, String sexe, String date_de_naissance, String image, String etat, String jardin) {
        this.id = id;
        this.parent = parent;
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.image = image;
        this.etat = etat;
        this.jardin = jardin;
    }

    public enfant(int id, String nomenf, String prenomenf, String sexe, String image, String etat) {
               this.id = id;
                 this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
           this.image = image;
        this.etat = etat;
 
    }
    
    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomenf() {
        return nomenf;
    }

    public void setNomenf(String nomenf) {
        this.nomenf = nomenf;
    }

    public String getPrenomenf() {
        return prenomenf;
    }

    public void setPrenomenf(String prenomenf) {
        this.prenomenf = prenomenf;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getJardin() {
        return jardin;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

    @Override
    public String toString() {
        return "enfant{" + "id=" + id + ", nomenf=" + nomenf + ", prenomenf=" + prenomenf + ", parent=" + parent + ", sexe=" + sexe + ", dt=" + dt + ", sdf=" + sdf + ", date_de_naissance=" + date_de_naissance + ", image=" + image + ", etat=" + etat + ", jardin=" + jardin + '}';
    }

    public enfant(String nomenf, String prenomenf, String image,String etat) {
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.image = image;
                this.etat = etat;

    }

   
    

   

    
   
}
