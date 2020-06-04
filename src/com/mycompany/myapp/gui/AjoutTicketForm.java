/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.entities.Ticket;
import com.mycompany.myapp.entities.jardin;
import com.mycompany.myapp.services.IncidentService;
import com.mycompany.myapp.services.ServiceClub;
import com.mycompany.myapp.services.TicketService;
import java.util.ArrayList;

/**
 *
 * @author AmiR
 */
public class AjoutTicketForm extends Form {
 Form current;

    String path ;
    
    public AjoutTicketForm(Form previous,Resources theme) {
         super("Ajouter un ticket", BoxLayout.y());
        current=this;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
        ArrayList<Incident> lis = IncidentService.getInstance().getAllIncidents();

        ComboBox cbIncident=new ComboBox();
        for(int i = 0; i<lis.size(); i++) {
         int var1=lis.get(i).getId();
         String var2=lis.get(i).getName();
            cbIncident.addItem(var1+"  "+var2);
                        

        }
Label lName= new Label ("nom");
        TextField tfName = new TextField();
        Label lType= new Label ("type");

        ComboBox cbType = new ComboBox();
        cbType.addItem("materiel");
        cbType.addItem("Personnel");

        TextField tfEtat = new TextField();
        Label lcre= new Label ("created by");

        TextField tfcreatedby = new TextField();
        Label lurgence= new Label ("urgence");

        ComboBox cbUrgence = new ComboBox();
          cbUrgence.addItem("haute");
          cbUrgence.addItem("moyenne");
          cbUrgence.addItem("basse");
Label lDes= new Label ("description");
Label lInc= new Label ("incident");


        TextField tfDesc = new TextField();

        Button btn = new Button("Ajouter");

        btn.addActionListener((evt) -> {
        
            if ((tfName.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Ticket t = new Ticket(tfName.getText(), cbType.getSelectedItem().toString(), tfEtat.getText(),
                             tfcreatedby.getText(),  cbUrgence.getSelectedItem().toString(), tfDesc.getText(),cbIncident.getSelectedItem().toString());

                    if (new TicketService().addTicket(t)) {
                            Dialog.show("Success","ticket Ajouté",new Command("OK"));
                    } else {
                            Dialog.show("Erreur","no",new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                            Dialog.show("Erreur","no",new Command("OK"));
                }

            }
        });

        this.addAll( lName,tfName,lType, cbType, lcre, tfcreatedby,lurgence, cbUrgence,lInc,cbIncident,lDes, tfDesc, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }

}
