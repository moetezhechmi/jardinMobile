/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.jardin;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Club;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;



/**
 *
 * @author ASUS
 */
public class ServiceJardin {
    
    
    public ArrayList<jardin> clubs;
    
    public static ServiceJardin instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
                private boolean responseResult;

    

    public ServiceJardin() {
         req = new ConnectionRequest();
    }

    public static ServiceJardin getInstance() {
        if (instance == null) {
            instance = new ServiceJardin();
        }
        return instance;
    }
    public boolean addClub(jardin t) {
        String url = Statics.BASE_URL + "/api/newAction?nom="+ t.getNom() + "&numTel="+ t.getNumTel() + "&mail="+t.getMail()+"&status="+t.getStatus()+"&description="+t.getDescription()+"&ImageUrl="+t.getImageUrl()+"&addresse="+t.getAdresse();
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
    
    
   public ArrayList<jardin> parseClubs(String jsonText) throws ParseException{
        try {
            clubs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                int id = (int)Float.parseFloat(obj.get("id").toString());
                int nbEnfant = (int)Float.parseFloat(obj.get("nbEnfants").toString());
                String Nom = obj.get("nom").toString();
                String description = obj.get("description").toString();
                String mail = obj.get("mail").toString();
                String numTel = obj.get("numTel").toString();
                
                //String status = obj.get("status").toString();
                
               // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                       


                clubs.add(new jardin(id,Nom,description,mail,numTel,nbEnfant));
            }

        } catch (IOException ex) {
        }

        return clubs;
    }
 public ArrayList<jardin> getAllClubs() throws ParseException {
        String url = Statics.BASE_URL+"/api/allAction";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                try {
                    clubs = parseClubs(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erreur de l'affichage !!!!!!!!!!!!!!!!!!!!!"); 
               }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return clubs;
    }
 
        public boolean Participerjardin(int id) {
        String url = Statics.BASE_URL +"/api/ParticiperJsonAction/"+id;

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
   
     public boolean SupprimerClub(int id) {
        String url = Statics.BASE_URL +"/api/deletejardinAction/" + id;
       
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
 
     public boolean updateclub(int id,jardin t) {
        String url = Statics.BASE_URL +"/api/updatejardinJsonAction/" +id+"?nom="+ t.getNom() + "&numTel="+ t.getNumTel() + "&mail="+t.getMail()+"&description="+t.getDescription()+"&ImageUrl="+t.getImageUrl();

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
     
      public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }
 
}
