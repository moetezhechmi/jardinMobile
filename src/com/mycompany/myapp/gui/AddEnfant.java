/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.mycompany.myapp.entities.enfant;
import com.mycompany.myapp.services.ServiceEnfant;

import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.enfant;
import com.mycompany.myapp.entities.jardin;
import com.mycompany.myapp.services.ServiceClub;
import java.util.ArrayList;


/**
 *
 * @author root
 */
public class AddEnfant extends Form{
    String path ;
    public AddEnfant(Form previous,Resources theme) throws ParseException
    {
        super(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
        Container titleCmp = BoxLayout.encloseY(
                        
                new Label("Ajouter un Enfant", "CenterTitle")
                        
                       
                );
        
        
                            ArrayList<jardin> lis = ServiceClub.getInstance().getAllJardins();

        tb.setTitleComponent(titleCmp);
  
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        setLayout(BoxLayout.y());
      
        TextField tfnom = new TextField("", "Nom", 20, TextArea.ANY);
        TextField tfprenom = new TextField("", "Prénom", 20, TextArea.ANY);
        ComboBox com = new ComboBox();
        com.addItem("Garçon");
        com.addItem("Fille");
        Calendar c = new Calendar();
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        System.out.println(datePicker.getText());
        String dateS = datePicker.toString().substring(datePicker.toString().indexOf("text = ") + 7, datePicker.toString().indexOf("text = ") + 15);
        Button imgBtn = new Button("parcourir");
         ComboBox cb=new ComboBox();
         for(int i = 0; i<lis.size(); i++) {
            cb.addItem(lis.get(i).getNom());
        }
        TextField tfimage = new TextField("","Veuillez saisir l'url de votre image");
        TextField tfetat = new TextField("Non Traité", "Etat", 20, TextArea.ANY);
        tfetat.setEnabled(focusScrolling);
        imgBtn.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path.substring(7));
                        Image img = null;
                        tfimage.setText(path.substring(7));//image heya just label nsob feha fel path
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        Label lnom = new Label("Nom :");
        Label lprenom = new Label("Prénom :");
        Label lsexe = new Label("Sexe :");
        Label ldate = new Label("Date de Naissance :");
        Label lphoto = new Label("Photo :");
        Label letat = new Label("Etat :");
        Label ljardin = new Label("Jardin :");

        Button btnValider = new Button("Ajouter");
        Button btnAnnuler = new Button("Annuler");
        
        addAll(lnom,tfnom,lprenom,tfprenom,lsexe,com,ldate,datePicker,imgBtn,tfimage,ljardin,cb,btnValider,btnAnnuler);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        
      btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0) ||(com.getSelectedItem()=="") ||(tfimage.getText().length()==0) ||(tfetat.getText().length()==0)  ||(datePicker.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
                else
                {
                    try {
                                               
                        enfant e = new enfant(tfnom.getText(),tfprenom.getText(),com.getSelectedItem().toString(),datePicker.getText(),tfimage.getText(),tfetat.getText());
                    
                        if( ServiceEnfant.getInstance().addEnfant(e))
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
