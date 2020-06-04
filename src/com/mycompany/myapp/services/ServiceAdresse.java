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
import com.mycompany.myapp.entities.Adresse;
import com.mycompany.myapp.entities.jardin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author moetez
 */
public class ServiceAdresse {
    public ArrayList<Adresse> adresses;
    
    public static ServiceAdresse instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
                private boolean responseResult;

    

    public ServiceAdresse() {
         req = new ConnectionRequest();
    }

    public static ServiceAdresse getInstance() {
        if (instance == null) {
            instance = new ServiceAdresse();
        }
        return instance;
    }
    
    public ArrayList<Adresse> parseAdresses(String jsonText) throws ParseException{
        try {
            adresses = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> eventsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                 SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                int id = (int)Float.parseFloat(obj.get("id").toString()); 
                String rue = obj.get("rue").toString();
                String ville = obj.get("ville").toString();
                String governerat = obj.get("gouvernorat").toString();
               
                //String status = obj.get("status").toString();
                
               // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                       


                adresses.add(new Adresse(id,rue,ville,governerat));
            }

        } catch (IOException ex) {
        }

        return adresses;
    }
 public ArrayList<Adresse> getAllAdresses() throws ParseException {
        String url = Statics.BASE_URL+"/api/listAdresseAction";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          
                try {
                    adresses = parseAdresses(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erreur de l'affichage !!!!!!!!!!!!!!!!!!!!!"); 
               }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return adresses;
    }
}
