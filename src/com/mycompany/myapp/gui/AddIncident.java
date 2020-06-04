/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.services.IncidentService;


/**
 *
 * @author ASUS
 */
public class AddIncident extends Form {
     public AddIncident(Form previous,Resources theme) {
        super("Ajouter un nouveau incident", BoxLayout.y());

        TextField tfName = new TextField("", "incident name");
        TextField tfDescription = new TextField("", "description");
        TextField tfCreatedby = new TextField("", "createdby");
         

        Button btn = new Button("Ajouter");

        btn.addActionListener((evt) -> {
            if ((tfName.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Incident t = new Incident(tfName.getText(),tfDescription.getText(),tfCreatedby.getText());
                    if (new IncidentService().addIncident(t)) {
                        Dialog.show("SUCCESS", "Incident sent", "OK", null);
                       sendMail("amir.yazidi1@esprit.tn");

                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            }
        });

        this.addAll(tfName, tfDescription ,tfCreatedby , btn);

               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
     public void sendMail(String Email) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/journalAmir/sendmail.php?email="+ Email);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.err.println("Mail Sent");
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
}


