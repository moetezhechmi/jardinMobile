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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.activite;
import com.mycompany.myapp.entities.enfant;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceActivite {
    public ArrayList<activite> activites;
    
    public static ServiceActivite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
                private boolean responseResult;

    

    public ServiceActivite() {
         req = new ConnectionRequest();
    }

    public static ServiceActivite getInstance() {
        if (instance == null) {
            instance = new ServiceActivite();
        }
        return instance;
    }
    
    
     public ArrayList<activite> parseActivites(String jsonText) throws ParseException{
        try {
            activites = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                int id = (int)Float.parseFloat(obj.get("id").toString()); 
                String Nom = obj.get("nomact").toString();
                                String img = obj.get("nomImg").toString();

               








                  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
               //  System.out.println(id);      


                activites.add(new activite(id,Nom,img));
            }

        } catch (IOException ex) {
        }

        return activites;
    }
 public ArrayList<activite> getAllActivites() throws ParseException {
        String url = Statics.BASE_URL +"/api/listActivites";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                try {
                    activites = parseActivites(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erreur de l'affichage !!!!!!!!!!!!!!!!!!!!!"); 
               }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return activites;
    }
}
