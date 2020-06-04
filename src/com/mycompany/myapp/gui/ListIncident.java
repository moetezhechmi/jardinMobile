/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.services.IncidentService;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListIncident  extends Form{
    
    
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListIncident(Form previous,Resources theme) {
        super("Liste des incidents", BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        IncidentService se = new IncidentService();
        
        ArrayList<Incident> lis = IncidentService.getInstance().getAllIncidents();
        
        { for(int i = 0; i<lis.size(); i++) {
            Container c2 = new Container(BoxLayout.x());
            Container c1 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.y());
            Button btnsupp = new Button("Supprimer");
            int m =i+1;
            Label ll = new Label("Incident n°"+m+":");
            c2.add(ll);
            add(c2);
            ImageViewer img1 = new ImageViewer(theme.getImage("signal.jpg").fill(300,300)); 
           
            Button btnmodif = new Button("Modifier");
            FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
            FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
            
            Label lnom = new Label("Nom: " + lis.get(i).getName());
            Label lDesc = new Label("Description: " + lis.get(i).getDescription());
            Label lCreated = new Label("Createdby: " + lis.get(i).getCreatedby());
            
            
            String nom = lis.get(i).getName();
            String desc = lis.get(i).getDescription();
            String created = lis.get(i).getCreatedby();
        
            c1.add(img1);
            c1.add(c3);
            c3.add(lnom);
            c3.add(lDesc);
            c3.add(lCreated);
            
            c3.add(btnmodif);
            c3.add(btnsupp);
            TextField tf = new TextField();
           int id = lis.get(i).getId();
            System.out.println(id);
            
            
            btnmodif.addActionListener((evt) -> {
                new UpdateIncident(this,nom,desc,created,id).show();
            });
             

            
            btnsupp.addActionListener((evt) -> {
              if (new IncidentService().Supprimerinc(id)) {
               
                  ToastBar.showInfoMessage("Votre incident est supprimée avec succés");
                
                    c2.remove();
                    c1.remove();
                    c3.remove();
                    btnsupp.remove();
                    btnmodif.remove();
                    this.refreshTheme();
                     } else {
                   ToastBar.showErrorMessage("Erreur de suppression");
                         System.out.println("id"+id);
                     
                  
              }
            });
            
            
            add(c1);
            
        }
        
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    
    }
    
}
