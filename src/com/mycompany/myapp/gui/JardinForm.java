/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author ASUS
 */
public class JardinForm  extends SideMenuBaseForm {

 
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    public JardinForm(Resources res) {
               super("Jardins", BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        
       
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                new Label("Jardins", "CenterTitle")
                        
                       
                );
        tb.setTitleComponent(titleCmp);

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        setupSideMenu(res);
        ImageViewer img =new ImageViewer(res.getImage("addJardin.png").fill(250,250));
                                  ImageViewer img2 =new ImageViewer(res.getImage("listJardin.png").fill(250, 250));

       Button btn = new Button("statistiques");
          Container southLayout = BoxLayout.encloseXCenter(
                       
                        img,img2,btn
                );
        add(BorderLayout.center(
                southLayout
        ));
        
         img.addPointerReleasedListener((l)->{
                   try {
                       new AddJardin(this,res).show();
                   } catch (ParseException ex) {
                   }
    

        });
             img2.addPointerReleasedListener((l)->{
                                                       new ListJardin(this,res).show();


        });
             
             
         
             btn.addPointerReleasedListener((l)->{
                   try {
                       new statJardin(this,res).show();
                   } catch (ParseException ex) {
                   }


        });
       
        
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
