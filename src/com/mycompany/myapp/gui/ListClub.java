/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.services.ServiceClub;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.jardin;
import java.util.ArrayList;



/**
 *
 * @author root
 */
public class ListClub extends Form{
    

    
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListClub(Form previous,Resources theme) {
        super(BoxLayout.y());
         try {
             Toolbar tb = getToolbar();
             tb.setTitleCentered(false);
             
             Container titleCmp = BoxLayout.encloseY(
                     
                     new Label("Liste des clubs", "CenterTitle")
                     
                     
             );
             
             
             
             tb.setTitleComponent(titleCmp);
             
                                  ArrayList<jardin> listJ = ServiceClub.getInstance().getAllJardins();

             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
             EncodedImage enc ;
             ServiceClub se = new ServiceClub();

             ArrayList<Club> lis = ServiceClub.getInstance().getAllClubs();
             { for(int i = 0; i<lis.size(); i++) {
                 Container c2 = new Container(BoxLayout.x());
                 Container c1 = new Container(BoxLayout.x());
                 Container c3 = new Container(BoxLayout.y());
                 Button btnsupp = new Button("Supprimer");
                 Label ll = new Label("Event n°"+i+":");
                 c2.add(ll);
                 add(c2);
                 Club c = new Club();
                 ImageViewer iv = new ImageViewer();
                 Image placeholder = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + lis.get(i).getNomImage(),
                         "http://127.0.0.1/lastupdataApresAmir/lastupdataApresAmir/projet/last2/web/image_directory/"+ lis.get(i).getNomImage()));
                 Button btnmodif = new Button("Modifier");
                               Button participer = new Button("partciper");

                 FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
                 FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
                 Label l2 = new Label("Nom: " + lis.get(i).getNom());
                 Label l3 = new Label("Dateclub: " + lis.get(i).getDateC());
                 Label lparticipe = new Label("nbParticipe: " + lis.get(i).getNbParticipe());
                 Label l5 = new Label("jardin: " + lis.get(i).getJardins());


            String nomC = lis.get(i).getNom();
              String des = lis.get(i).getDescription();
               String date = lis.get(i).getDateC();
                String image = lis.get(i).getNomImage();

              
                

                 c1.add(img1);
                 c1.add(c3);
                 c3.add(l2);


                 c3.add(new SpanLabel("Description:" + lis.get(i).getDescription()));
                 c3.add(l3);
                 c3.add(lparticipe);
                 c3.add(l5);
                 
                 c3.add(btnmodif);
                 c3.add(btnsupp);
                                  c3.add(participer);

                 TextField tf = new TextField();
                                  int id = lis.get(i).getId();
                                  
                                  
                 participer.addActionListener((evt) -> {
                                  if(new ServiceClub().Participerclub(id)){
                           ToastBar.showInfoMessage("Vous etes maintenent membre dans le club "+nomC);
                                       new ListClub(this,theme).show();

                            this.refreshTheme();
                                  }

                                         
                                     

            });

                   System.out.println(id);
                 btnsupp.addActionListener((evt) -> {
                     if(new ServiceClub().SupprimerClub(id))
                     {
                         ToastBar.showInfoMessage("Votre evenement est supprimée avec succés");
                         c2.remove();
                         c1.remove();
                         c3.remove();
                         img1.remove();
                         btnsupp.remove();
                         btnmodif.remove();
                         this.refreshTheme();
                     }else{
                         ToastBar.showErrorMessage("Erreur de suppression");
                         System.out.println("id"+id);
                     }
                 });
 btnmodif.addActionListener((evt) -> {
                     try {
                         new updateClub(this,nomC,des,image,id).show();
                     } catch (ParseException ex) {
                     }
            });
            
            
                 
                 
                 
                 
                 add(c1);
                 
             }
             
             
             }
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         } catch (ParseException ex) {
         }
    
    }
    
   
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
    
}

