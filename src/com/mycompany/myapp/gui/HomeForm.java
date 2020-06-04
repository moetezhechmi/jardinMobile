/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.services.ServiceClub;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author bhk
 */
public class HomeForm extends SideMenuBaseForm{
Form current;
 private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    public HomeForm(Resources theme) {
        current=this;
         setLayout(new FlowLayout(CENTER,CENTER));
       Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        
       
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                new Label("Acceuil", "CenterTitle")
                        
                       
                );
        
        
       
        tb.setTitleComponent(titleCmp);
          ImageViewer iv=new ImageViewer();
DefaultListModel<Image> listeImages=new DefaultListModel<Image>(); 
try{
listeImages.addItem(Image.createImage("/img1.jpg"));
}catch(IOException ex){
Dialog.show("Erreur", ex.getMessage(),"OK",null); }
iv.setImage(listeImages.getItemAt(0)); 
iv.setImageList(listeImages);
add(iv);
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        
        setupSideMenu(theme);
    }

    @Override
    protected void showClubForm(Resources res) {
        new ClubForm(res).show();
       
    }

    @Override
    protected void showEnfantForm(Resources res) {
        new EnfantForm(res).show();
    }

    @Override
    protected void showActiviteForm(Resources res) {
        new ActiviteForm(res).show();
    }

    @Override
    protected void showIncidentForm(Resources res) {
        new IncidentForm(res).show();
    }


     @Override
    protected void showJardinForm(Resources res) {
                new JardinForm(res).show();

    }
 @Override
    protected void showReclamationForm(Resources res) {
                        new ReclamationForm(res).show();

    }
    

}
    

 