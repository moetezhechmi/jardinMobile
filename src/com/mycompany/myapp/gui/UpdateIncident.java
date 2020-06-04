/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
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
import com.mycompany.myapp.services.IncidentService;

/**
 *
 * @author ASUS
 */
public class UpdateIncident  extends Form{
    IncidentService se=new IncidentService();
       public UpdateIncident(Form previous,String nom,String desc,String created,int id)
    {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
        Container titleCmp = BoxLayout.encloseY(
                        
                new Label("Modifier Incident ", "CenterTitle")  );
    
        tb.setTitleComponent(titleCmp);
  
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField(nom);
        TextField tfDescription = new TextField(desc);
        TextField tfCreatedby = new TextField(created);
         
        Label lnom = new Label("Name :");
        Label ldescription = new Label("Descriptio  :");
        Label lCreatdby = new Label("Created by   :");
         
        Button btnValider = new Button("Update");
        Button btnAnnuler = new Button("Annuler");
        
        addAll(lnom,tfName ,ldescription,tfDescription,lCreatdby,tfCreatedby,btnValider,btnAnnuler);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        
      btnValider.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0) ||(tfDescription.getText().length()==0) ||(tfCreatedby.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                          Incident e=new Incident();
                          
                            e.setName(tfName.getText());
                            e.setDescription(tfDescription.getText());
                            e.setCreatedby(tfCreatedby.getText());
                           
                        if( se.updateinc(id, e))
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
