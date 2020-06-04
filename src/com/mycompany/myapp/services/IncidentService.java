/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.utils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AmiR
 */
public class IncidentService {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Incident> incidents;
  public static IncidentService instance=null;
    public IncidentService() {
        request = DataSource.getInstance().getRequest();
    }
     public static IncidentService getInstance() {
        if (instance == null) {
            instance = new IncidentService();
        }
        return instance;
    }
    
        public boolean addIncident(Incident incident) {
        String url = Statics.BASE_URL + "/api/incident/new?name="+incident.getName() 
                +"&description=" + incident.getDescription()
                +"&createdby=" + incident.getCreatedby();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
    public ArrayList<Incident> getAllIncidents() {
        String url = Statics.BASE_URL + "/api/incident/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                incidents = parseinc(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return incidents;
    }

    public ArrayList<Incident> parseinc(String jsonText) {
        try {
            incidents = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString();
                String description = obj.get("description").toString();
                String createdby = obj.get("createdby").toString();
                String dateinc= obj.get("dateinc").toString();
            
                incidents.add(new Incident(id,name, description,createdby,dateinc));
            }

        } catch (IOException ex) {
        }

        return incidents;
    }
    
        public void Modifier(int id,Incident incident){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/last2/web/app_dev.php/api/incident/edit"+"/"+id);  
     
        NetworkManager.getInstance().addToQueueAndWait(con);
        //this.dureeAttente(Commande);
        //     pdf(Commande);
    }
        
        public boolean updateinc(int id,Incident e) {
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL +"/api/incident/updateincJson/"+id+"?name=" + e.getName()+ 
                "&description=" + e.getDescription()+"&createdby=" + e.getCreatedby();

        con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; // Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return responseResult;
    }
 
        
     /*   public void Supprimer(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/last2/web/app_dev.php/api/incident/delete_inc" + "/" + id);  
     
        NetworkManager.getInstance().addToQueueAndWait(con);
     
        
    } */
     public boolean Supprimerinc(int id) {
                 ConnectionRequest req = new ConnectionRequest();

        String url =Statics.BASE_URL +"/api/incident/delete_inc" + "/" + id;
       
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
        
    public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    } 
}
