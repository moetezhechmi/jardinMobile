/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.enfant;
import com.mycompany.myapp.services.ServiceEnfant;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;


/**
 *
 * @author root
 */
public class ListEnfant extends Form{
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListEnfant(Form previous,Resources theme) {
        super(BoxLayout.y());
        
         try {
             Toolbar tb = getToolbar();
             tb.setTitleCentered(false);
             
             Container titleCmp = BoxLayout.encloseY(
                     
                     new Label("Liste des enfants", "CenterTitle")
                     
                     
             );
             
             
             
             tb.setTitleComponent(titleCmp);
             
             
             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
             EncodedImage enc ;
             ServiceEnfant se = new ServiceEnfant();
             ArrayList<enfant> lis = ServiceEnfant.getInstance().getAllEnfants();
             { for(int i = 0; i<lis.size(); i++) {
                 Container c2 = new Container(BoxLayout.x());
                 Container c1 = new Container(BoxLayout.x());
                 Container c3 = new Container(BoxLayout.y());
                 Button btnsupp = new Button("Supprimer");
                 Label ll = new Label("Enfant n°"+i+":");
                 c2.add(ll);
                 add(c2);
                 ImageViewer iv = new ImageViewer();
                 Image placeholder = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + lis.get(i).getImage(),
                         "http://127.0.0.1/lastupdataApresAmir/lastupdataApresAmir/projet/last2/web/image_directory/"+ lis.get(i).getImage()));
                 Button btnmodif = new Button("Modifier");
                 FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
                 FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
                 Label l2 = new Label("Nom: " + lis.get(i).getNomenf());
                                  Label l3 = new Label("prenom: " + lis.get(i).getPrenomenf());

 Label lsexe = new Label("sexe: " + lis.get(i).getSexe());
                 Label l4 = new Label("Datenaissance: " + lis.get(i).getDate_de_naissance());
                                  Label letat = new Label("etat: " + lis.get(i).getEtat());
                                         

              String nom = lis.get(i).getNomenf();
              String prenom = lis.get(i).getPrenomenf();
              String sexe = lis.get(i).getSexe();
              String etat = lis.get(i).getEtat();

               
              String image = lis.get(i).getImage();



                 c1.add(img1);
                                  c1.add(c3);

                 c3.add(l2);
                 c3.add(l3);
                  c3.add(lsexe);
                  c3.add(l4);
                 c3.add(letat);
                                 







                 
                 
                 c3.add(btnmodif);
                 c3.add(btnsupp);
                 TextField tf = new TextField();
                 
                 int id = lis.get(i).getId();
                 System.out.println(id);
               
                 btnmodif.addActionListener((evt) -> {
                new updateEnfant(this,nom,prenom,sexe,image,etat,id).show();
            });
                 
                 
                 btnsupp.addActionListener((evt) -> {
                     if(new ServiceEnfant().SupprimerEnfant(id))
                     {
                         ToastBar.showInfoMessage("Enfant supprimée avec succés");
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

