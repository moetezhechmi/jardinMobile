/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;



/**
 *
 * @author ASUS
 */
public class ClubForm extends SideMenuBaseForm {
    
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};

    public ClubForm(Resources res) {
                super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        
       
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                new Label("Clubs", "CenterTitle")
                        
                       
                );
        tb.setTitleComponent(titleCmp);

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        setupSideMenu(res);
        ImageViewer img =new ImageViewer(res.getImage("club.png"));
                                  ImageViewer img2 =new ImageViewer(res.getImage("listE.png").fill(250, 250));
Button stat =new Button ("stat");
         Button Ajoutevent = new Button("Ajouter club(s)");
         Button Affevent = new Button("Afficher club(s)");
          FontImage.setMaterialIcon(Ajoutevent, FontImage.MATERIAL_ADD_COMMENT);
        FontImage.setMaterialIcon(Affevent, FontImage.MATERIAL_VIEW_LIST);
        Ajoutevent.getStyle().setBorder(Border.createDashedBorder(CENTER,CENTER ));
        Affevent.getStyle().setBorder(Border.createDashedBorder(CENTER, CENTER));
         Ajoutevent.setUIID("SkipButton");
        Affevent.setUIID("SkipButton");
          Container southLayout = BoxLayout.encloseXCenter(
                       
                        img,img2,stat
                );
        add(BorderLayout.center(
                southLayout
        ));
        
         img.addPointerReleasedListener((l)->{
                    try {
                        new AddClub(this,res).show();
                    } catch (ParseException ex) {
                    }

        });
             img2.addPointerReleasedListener((l)->{
                        new ListClub(this,res).show();

        });
        stat.addActionListener((evt) -> {
         try {
                new statForm(res).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Ajoutevent.addActionListener((evt) -> {
                    try {
                        new AddClub(this,res).show();
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
