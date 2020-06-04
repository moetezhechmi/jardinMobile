/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.io.FileSystemStorage;
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
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Incident;
import com.mycompany.myapp.entities.enfant;
import com.mycompany.myapp.services.IncidentService;
import com.mycompany.myapp.services.ServiceClub;
import com.mycompany.myapp.services.ServiceEnfant;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class updateEnfant extends Form{
    String path="";
    ServiceEnfant se=new ServiceEnfant();
       public updateEnfant(Form previous,String nom,String prenom,String sexe,String image, String etat,int id)
       {
        super(BoxLayout.y());
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      
        Container titleCmp = BoxLayout.encloseY(
                        
                new Label("modifier un profil enfant", "CenterTitle")
                        
                       
                );
        
        
       
        tb.setTitleComponent(titleCmp);
  
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        setLayout(BoxLayout.y());
        TextField txtnom = new TextField(nom);
        TextField txtprenom = new TextField(prenom);
               

        ComboBox com = new ComboBox();
        com.addItem("Garçon");
        com.addItem("Fille");
        Calendar c = new Calendar();
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        System.out.println(datePicker.getText());
        String dateS = datePicker.toString().substring(datePicker.toString().indexOf("text = ") + 7, datePicker.toString().indexOf("text = ") + 15);
        Button imgBtn = new Button("parcourir");
        TextField tfimage = new TextField(image);
        TextField tfetat = new TextField("Non Traité", "Etat", 20, TextArea.ANY);
        
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
        Label lprenom = new Label("Prenom  :");
        Label lsexe = new Label("Sexe :");
         Label lphoto = new Label("Photo :");
      Label ldate = new Label("Date de Naissance :");
        
        Button btnValider = new Button("Update");
        Button btnAnnuler = new Button("Annuler");
        
        addAll(lnom,txtnom ,lprenom,txtprenom,lsexe,com,ldate,datePicker,lphoto,imgBtn,tfimage,btnValider,btnAnnuler,tfetat);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        
      btnValider.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if ((txtnom.getText().length()==0) ||(txtprenom.getText().length()==0) ||(tfimage.getText().length()==0) ||(datePicker.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                          enfant e=new enfant();
                          
            
                   
                            e.setNomenf(txtnom.getText());
                            e.setPrenomenf(txtprenom.getText());
                            e.setSexe(com.getSelectedItem().toString());
                            e.setDate_de_naissance(datePicker.getText());
                            e.setImage(tfimage.getText());
                            e.setEtat(tfetat.getText());
                                 e.setDate_de_naissance(datePicker.getText());


                    
      
                        if( se.updateEnfant(id, e))
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
