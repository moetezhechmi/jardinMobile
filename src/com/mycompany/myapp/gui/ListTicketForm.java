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
import com.mycompany.myapp.entities.Ticket;
import com.mycompany.myapp.services.TicketService;
import java.util.ArrayList;

/**
 *
 * @author AmiR
 */
public class ListTicketForm extends Form {
 
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListTicketForm(Form previous,Resources theme) {
        super(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Container titleCmp = BoxLayout.encloseY(
                
                new Label("Liste des Tickets", "CenterTitle")
                
                
        );
        
        tb.setTitleComponent(titleCmp);
        TicketService se = new TicketService();
        
        ArrayList<Ticket> lis = TicketService.getInstance().getAllTickets();
        
        { for(int i = 0; i<lis.size(); i++) {
            Container c2 = new Container(BoxLayout.x());
            Container c1 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.y());
            
            Button btnsupp = new Button("Supprimer");
            
            Label ll = new Label("Ticket n°"+i+":");
            c2.add(ll);
            add(c2);
            ImageViewer img1 = new ImageViewer(theme.getImage("ticket.png")); 
           
            Button btnmodif = new Button("Modifier");
            FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
            FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
            
            Label lname = new Label("Name: " + lis.get(i).getName());
            Label ltype = new Label("Type: " + lis.get(i).getType());
            Label letat = new Label("Etat: " + lis.get(i).getEtat());
            Label lCreated = new Label("creer par: " + lis.get(i).getCreatedby());
            Label ldate = new Label("date: " + lis.get(i).getCreatedat());
            Label lurgence = new Label("urgence: " + lis.get(i).getUrgence());
            Label ldesc = new Label("Description: " + lis.get(i).getDescription());
            
            
            String nom = lis.get(i).getName();
            String type = lis.get(i).getType();
            String etat = lis.get(i).getEtat();
            String created = lis.get(i).getCreatedby();
            String urgence = lis.get(i).getUrgence();
            String desc = lis.get(i).getDescription();
             String inc = lis.get(i).getInc_id();



                                    
        
            c1.add(img1);
            c1.add(c3);
            c3.add(lname);
            c3.add(ltype);
            c3.add(letat);
            c3.add(lCreated);
            c3.add(ldate);           
             c3.add(lurgence);
             c3.add(ldesc);
                        
                        
            c3.add(btnmodif);
            c3.add(btnsupp);
            TextField tf = new TextField();
           int id = lis.get(i).getId();
            System.out.println(id);
            
            
            btnmodif.addActionListener((evt) -> {
                new ModifTicketForm(this,id,nom,type,etat,created,desc,urgence,inc).show();
            });
             

            
            btnsupp.addActionListener((evt) -> {
              if (new TicketService().Supprimertick(id)) {
               
                  ToastBar.showInfoMessage("Votre réclamation est supprimée avec succés");
                
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
