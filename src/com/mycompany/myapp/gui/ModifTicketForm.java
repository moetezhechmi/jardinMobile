/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.entities.Ticket;
import com.mycompany.myapp.services.IncidentService;
import com.mycompany.myapp.services.TicketService;
import java.util.ArrayList;

/**
 *
 * @author AmiR
 */
public class ModifTicketForm extends Form{
     TicketService se=new TicketService();
     
       public ModifTicketForm(Form previous,int id,String nom,String type,String etat,
               String created, String desc,String urgence,String inc )
    {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
        Container titleCmp = BoxLayout.encloseY(
                        
                new Label("Modifier Ticket ", "CenterTitle")  );
    
        tb.setTitleComponent(titleCmp);
  
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        setLayout(BoxLayout.y());
         ArrayList<Incident> lis = IncidentService.getInstance().getAllIncidents();

        ComboBox cbIncident=new ComboBox();
        for(int i = 0; i<lis.size(); i++) {
         int var1=lis.get(i).getId();
         String var2=lis.get(i).getName();
            cbIncident.addItem(var1+"  "+var2);
                        

        }
        TextField tfName = new TextField(nom);
 ComboBox cbType = new ComboBox();
        cbType.addItem("materiel");
        cbType.addItem("Personnel");
        TextField tfetat = new TextField(etat);
        TextField tfCreated = new TextField(created);
ComboBox cbUrgence = new ComboBox();
          cbUrgence.addItem("haute");
          cbUrgence.addItem("moyenne");
          cbUrgence.addItem("basse");        TextField tfDesc = new TextField(desc);
              
         
        Label lnom = new Label("Name :");
        Label ltype = new Label("type  :");
        Label letat = new Label("etat    :");
        Label lCreatd = new Label("Created    :");
        Label lurgence = new Label("Urgence    :");
        Label lDesc = new Label("Description    :");

        
        
        Button btnValider = new Button("Update");
        Button btnAnnuler = new Button("Annuler");
        
        addAll(lnom,tfName ,ltype,cbType,lCreatd,tfCreated,lurgence,cbUrgence,lDesc,tfDesc,cbIncident,btnValider,btnAnnuler);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        
      btnValider.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                          Ticket e=new Ticket();
                          
                            e.setName(tfName.getText());
                            e.setType(cbType.getSelectedItem().toString());
                            e.setEtat(tfetat.getText());
                            e.setCreatedby(tfCreated.getText());
                            e.setUrgence(cbUrgence.getSelectedItem().toString());
                            e.setDescription(tfDesc.getText());
                            e.setInc_id(cbIncident.getSelectedItem().toString());

                           
                        if( se.updatetick(id, e))
                        {
                            System.out.println(e);
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (Exception e) {
                        Dialog.show("ERROR", "server error", new Command("OK"));
                    }
                    
                }
                
                
            }

        });
      }  

    


}
