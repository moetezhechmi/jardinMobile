/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Club;
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
import com.mycompany.myapp.entities.jardin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;



/**
 *
 * @author ASUS
 */
public class ServiceClub {
    
    
    public ArrayList<Club> clubs;
        public ArrayList<jardin> jardins;

    
    public static ServiceClub instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
                private boolean responseResult;

    

    public ServiceClub() {
         req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }
    public boolean addClub(Club c) {
        String url = Statics.BASE_URL + "/api/clubs/new?nom="+c.getNom()+"&description="+c.getDescription()+"&dateC="+c.getDateC()+"&nomImage="+c.getNomImage()+"&jardins="+c.getJardins();
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
    
    
   public ArrayList<Club> parseClubs(String jsonText) throws ParseException{
        try {
            clubs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                int id = (int)Float.parseFloat(obj.get("id").toString()); 
                int par = (int)Float.parseFloat(obj.get("nbParticipe").toString()); 

                String Nom = obj.get("nom").toString();
                String Description = obj.get("description").toString();

                String img = obj.get("nomImage").toString();
                String jard = obj.get("jardins").toString();



                  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
               //  System.out.println(id);      


                clubs.add(new Club(id,Nom, Description,img,par,jard ));
            }

        } catch (IOException ex) {
        }

        return clubs;
    }
 public ArrayList<Club> getAllClubs() throws ParseException {
        String url = Statics.BASE_URL +"/api/clubs/all";

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
 public boolean SupprimerClub(int id) {
        String url = Statics.BASE_URL +"/api/club/deleteJson/"+ id;
       
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
 
 
   public boolean updateclub(int id,Club c) {
        String url = Statics.BASE_URL +"/api/club/updateclubJson/"+id+"?nom=" + c.getNom()+ "&description=" + c.getDescription()+"&nomImage="+c.getNomImage()+"&dateC="+c.getDateC();

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
   
   
   public boolean Participerclub(int id) {
        String url = Statics.BASE_URL +"/api/club/participer/"+id;

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
   
   
   /// liste de jardins
   
   
      public ArrayList<jardin> parseJardins(String jsonText) throws ParseException{
        try {
            jardins = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                 int id = (int)Float.parseFloat(obj.get("id").toString()); 

                String Nom = obj.get("nom").toString();
               


                jardins.add(new jardin(id,Nom ));
            }

        } catch (IOException ex) {
        }

        return jardins;
    }
      
      public ArrayList<jardin> getAllJardins() throws ParseException {
        String url = Statics.BASE_URL +"/api/listJardin";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                try {
                    jardins = parseJardins(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erreur de l'affichage !!!!!!!!!!!!!!!!!!!!!"); 
               }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return jardins;
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
