/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.jardin;
import com.mycompany.myapp.services.ServiceJardin;

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
public class ListJardin extends Form{
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star); 
   s.setBgTransparency(0);
     }
    public ListJardin(Form previous,Resources theme) {
        super(BoxLayout.y());
        
         try {
             Toolbar tb = getToolbar();
             tb.setTitleCentered(false);
             
             Container titleCmp = BoxLayout.encloseY(
                     
                     new Label("Mes Jardins", "CenterTitle")
                     
                     
             );
             
             
             
             tb.setTitleComponent(titleCmp);
             
             
             FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
             EncodedImage enc ;
             ServiceJardin se = new ServiceJardin();
             ArrayList<jardin> lis = ServiceJardin.getInstance().getAllClubs();
             { for(int i = 0; i<lis.size(); i++) {
                 Container c2 = new Container(BoxLayout.x());
                 Container c1 = new Container(BoxLayout.x());
                 Container c3 = new Container(BoxLayout.y());
                 Button btnsupp = new Button("Supprimer");
                 int m=i+1;
                 Label ll = new Label("Jardin n°"+m+":");
                 c2.add(ll);
                 add(c2);
                 jardin c = new jardin();
                 ImageViewer iv = new ImageViewer();
                 Image placeholder = Image.createImage(this.getWidth() / 3 - 3, this.getWidth() / 3- 3, 0xbfc9d2);
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + lis.get(i).getImageUrl(),
                         "http://127.0.0.1:8000/app_dev.php/uploads/jardin/image/"+ lis.get(i).getImageUrl()));
                   ImageViewer img =new ImageViewer(theme.getImage("weldi.png").fill(300,300));
                 Button btnmodif = new Button("Modifier");
                  Button participer = new Button("partciper");
                 FontImage.setMaterialIcon(btnsupp, FontImage.MATERIAL_DELETE);
                 FontImage.setMaterialIcon(btnmodif, FontImage.MATERIAL_UPLOAD_FILE);
                 Label l2 = new Label("Nom: " + lis.get(i).getNom());
                 String nom = lis.get(i).getNom();
                 String numTel = lis.get(i).getNumTel();
                 String mail = lis.get(i).getMail();
                 String imageUrl = lis.get(i).getImageUrl();
                 String description = lis.get(i).getDescription();
                  Label lparticipe = new Label("nbEnfants: " + lis.get(i).getNbEnfants());
                 
                 c1.add(img);
                 c1.add(c3);
                 c3.add(l2);
                 c3.add(new SpanLabel("description:" + lis.get(i).getDescription()));
                 c3.add(lparticipe);
                  c3.add(new SpanLabel("mail:" + lis.get(i).getMail()));
                   c3.add(new SpanLabel("numTel:" + lis.get(i).getNumTel()));
                   // c3.add(new SpanLabel("Status:" + lis.get(i).getStatus()));
                
                 
                 c3.add(btnmodif);
                 c3.add(btnsupp);
                                  c3.add(participer);

                 int id=lis.get(i).getId();
                 TextField tf = new TextField();
                                  participer.addActionListener((evt) -> {
                                  if(new ServiceJardin().Participerjardin(id)){
                           ToastBar.showInfoMessage("Vous etes maintenent membre dans le jardin "+nom);
                                       new ListJardin(this,theme).show();

                            this.refreshTheme();
                                  }

                                         
                                     

            });
                 
                 System.out.println(id);
                 btnsupp.addActionListener((evt) -> {
                     if(new ServiceJardin().SupprimerClub(id))
                     {
                         ToastBar.showInfoMessage("Votre jardin est supprimée avec succés");
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
                new updateJardin(this,nom,numTel,mail,imageUrl,description,id).show();
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

