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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Ticket;
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
public class TicketService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Ticket> tickets;
 public static TicketService instance=null;
    public TicketService() {
        request = DataSource.getInstance().getRequest();
    }
 public static TicketService getInstance() {
        if (instance == null) {
            instance = new TicketService();
        }
        return instance;
    }
    public boolean addTicket(Ticket ticket) {
       
        String url = Statics.BASE_URL + "/api/ticket/new?&name="+ticket.getName()
                +"&type=" + ticket.getType()
                +"&etat=" + ticket.getEtat()
                +"&Createdby=" + ticket.getCreatedby()
                +"&description=" + ticket.getDescription()
                +"&urgence=" + ticket.getUrgence()
                +"&incidents="+ticket.getInc_id();

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

    public ArrayList<Ticket> getAllTickets() {
        String url = Statics.BASE_URL + "/api/ticket/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tickets = parseTasks(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return tickets;
    }

    public ArrayList<Ticket> parseTasks(String jsonText) {
        try {
            tickets = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString();
                String type = obj.get("type").toString();
                String etat = obj.get("etat").toString();
                String createdby = obj.get("createdby").toString();
                String createdat = obj.get("createdat").toString();
                String urgence = obj.get("urgence").toString();
                String description = obj.get("description").toString();

                tickets.add(new Ticket(id, name, type, etat, createdby, createdat, urgence, description));
            }

        } catch (IOException ex) {
        }

        return tickets;
    }
    
     public boolean updatetick(int id,Ticket ticket) {
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL +"/api/ticket/updatetick/"+id+"?&name="+ticket.getName()
                +"&type=" +ticket.getType()
                +"&etat=" +ticket.getEtat()
                +"&Createdby=" +ticket.getCreatedby()
                +"&description=" +ticket.getDescription()
                +"&urgence=" +ticket.getUrgence()
                +"&incidents="+ticket.getInc_id();

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
     
     
    public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/last2/web/app_dev.php/api/ticket/delete_tic" + "/" + id);

        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
     public boolean Supprimertick(int id) {
                 ConnectionRequest req = new ConnectionRequest();

        String url =Statics.BASE_URL +"/api/ticket/delete_tic" + "/" + id;
       
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
}
