/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Adresse;
import java.util.ArrayList;

/**
 *
 * @author moetez
 */
public class jardin {
        private int id;
        private String nom;
        private String addresse;
        private String numTel;
        private String mail;
        private String status="WAITING";
        private String imageUrl;
        private String description;
        private String Image;
        private int nbEnfants;
        
        java.util.Date dt = new  java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Image_updated_at = sdf.format(dt);

public jardin() {
    }

    public jardin(int id, String nom, String adresse, String numTel, String mail, String imageUrl, String description, String Image_updated_at,String Image,int nbEnfants) {
        this.id = id;
        this.nom = nom;
        this.addresse = adresse;
        this.numTel = numTel;
        this.mail = mail;
        this.imageUrl = imageUrl;
        this.description = description;
        this.Image_updated_at = Image_updated_at;
        this.Image = Image;
        this.nbEnfants = nbEnfants;
        
    }

    public jardin(String nom, String numTel, String mail, String imageUrl, String description, String adresse) {
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
        this.imageUrl = imageUrl;
        this.description = description;
        this.addresse = adresse;
    }

    public jardin(int id, String nom, String numTel, String mail,  String imageUrl, String description, String adresse) {
        this.id = id;
        this.nom = nom;
        this.addresse = adresse;
        this.numTel = numTel;
        this.mail = mail;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public jardin(int id, String nom, String numTel, String mail, String description , String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
        this.description = description;
        this.imageUrl = imageUrl;
        
    }
    
    
    public jardin(int id,String nom) {
        this.id = id;
        this.nom = nom;
        
    }

    public jardin(String nom,String description , String numTel, String mail ) {
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
        this.description = description;
    }

    public jardin(int id, String nom, String description, String mail, String numTel) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.mail = mail;
        this.numTel = numTel;
    }
    public jardin(int id, String nom, String description, String mail, String numTel, int nbEnfants){
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.mail = mail;
        this.numTel = numTel;
         this.nbEnfants = nbEnfants;
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

    public String getAdresse() {
        return addresse;
    }

    public void setAdresse(String adresse) {
        this.addresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.imageUrl = ImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_updated_at() {
        return Image_updated_at;
    }

    public void setImage_updated_at(String Image_updated_at) {
        this.Image_updated_at = Image_updated_at;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getNbEnfants() {
        return nbEnfants;
    }

    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }
    
    
    @Override
    public String toString() {
        return "jardin{" + "id=" + id + ", nom=" + nom + ", adresse=" + addresse + ", numTel=" + numTel + ", mail=" + mail + ", status=" + status + ", ImageUrl=" + imageUrl + ", description=" + description + ", Image_updated_at=" + Image_updated_at + '}';
    }



} 