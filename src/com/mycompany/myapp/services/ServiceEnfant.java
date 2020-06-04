/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.enfant;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;



/**
 *
 * @author ASUS
 */
public class ServiceEnfant {
    
    
    public ArrayList<enfant> enfants;
    
    public static ServiceEnfant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
                private boolean responseResult;

    

    public ServiceEnfant() {
         req = new ConnectionRequest();
    }

    public static ServiceEnfant getInstance() {
        if (instance == null) {
            instance = new ServiceEnfant();
        }
        return instance;
    }
    public boolean addEnfant(enfant e) {
        String url = Statics.BASE_URL + "/api/enfants/newEnfant?nomenf=" + e.getNomenf()+ "&prenomenf=" + e.getPrenomenf()+"&sexe=" + e.getSexe()+"&datedenaissance=" + e.getDate_de_naissance()+"&image=" + e.getImage() +"&etat=" + e.getEtat() ; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
   public ArrayList<enfant> parseEnfants(String jsonText) throws ParseException{
        try {
            enfants = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                int id = (int)Float.parseFloat(obj.get("id").toString()); 
                String Nom = obj.get("nomenf").toString();
                String Prenom = obj.get("prenomenf").toString();
                 String sexe = obj.get("sexe").toString();
                  String etat = obj.get("etat").toString();
                                   
                                                    String image = obj.get("image").toString();









                  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
               //  System.out.println(id);      


                enfants.add(new enfant(id,Nom, Prenom,sexe,image,etat));
            }

        } catch (IOException ex) {
        }

        return enfants;
    }
 public ArrayList<enfant> getAllEnfants() throws ParseException {
        String url = Statics.BASE_URL +"/api/enfants/all";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                try {
                    enfants = parseEnfants(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erreur de l'affichage !!!!!!!!!!!!!!!!!!!!!"); 
               }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return enfants;
    }
 
 
 public boolean SupprimerEnfant(int id) {
        String url = Statics.BASE_URL +"/api/enfant/deleteEnfant/"+ id;
       
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
         System.out.println(responseResult);
           
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         
        return responseResult;
    }
 
 
  public boolean updateEnfant(int id,enfant e) {
        String url = Statics.BASE_URL +"/api/enfant/updateEnfantJson/"+id+"?nomenf=" + e.getNomenf()+ "&prenomenf=" + e.getPrenomenf()+"&sexe=" + e.getSexe()+"&datedenaissance=" + e.getDate_de_naissance()+"&image=" + e.getImage() +"&etat=" + e.getEtat() ;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return responseResult;
    }
 
}
