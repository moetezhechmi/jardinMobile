/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.activite;
import com.mycompany.myapp.entities.jardin;
import com.mycompany.myapp.services.ServiceActivite;
import com.mycompany.myapp.services.ServiceClub;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListActivite extends Form {
    
       private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListActivite(Form previous,Resources theme) {
        super(BoxLayout.y());
         try {
             Toolbar tb = getToolbar();
             tb.setTitleCentered(false);
             
             Container titleCmp = BoxLayout.encloseY(
                     
                     new Label("Liste des Activites", "CenterTitle")
                     
                     
             );
             
             
             
             tb.setTitleComponent(titleCmp);
             

             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
             EncodedImage enc ;
             ServiceClub se = new ServiceClub();

             ArrayList<activite> lis = ServiceActivite.getInstance().getAllActivites();
             { for(int i = 0; i<lis.size(); i++) {
                 Container c2 = new Container(BoxLayout.x());
                 Container c1 = new Container(BoxLayout.x());
                 Container c3 = new Container(BoxLayout.y());
                 Button btnsupp = new Button("Supprimer");
                 Label ll = new Label("Event n°"+i+":");
                 c2.add(ll);
                 add(c2);
                 ImageViewer iv = new ImageViewer();
                 Image placeholder = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + lis.get(i).getNomImg(),
                         "http://127.0.0.1/lastupdataApresAmir/lastupdataApresAmir/projet/last2/web/image_directory/"+ lis.get(i).getNomImg()));
                 Button btnmodif = new Button("Modifier");
                         ImageViewer img =new ImageViewer(theme.getImage("pdf.png"));

                               Button participer = new Button("partciper");

                 FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
                 FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
                 Label l2 = new Label("Nom: " + lis.get(i).getNomact());
                                  Label l3 = new Label("Nom: " + lis.get(i).getNomImg());

                

              
                

                 c1.add(img);
                 c1.add(c3);
                 c3.add(l2);
                                  c3.add(l3);



                
                 
              
            
                 
                 
                 
                 
                 add(c1);
                 
             }
             
             
             }
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
         } catch (ParseException ex) {
         }
    
    }
    
}
