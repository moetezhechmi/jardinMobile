/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.io.ConnectionRequest;
import com.mycompany.myapp.entities.jardin;

import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.services.ServiceClub;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
import java.util.ArrayList;


/**
 *
 * @author root
 */
public class AddClub extends Form{
        Form current;

    String path ;
    
    
   
    public AddClub(Form previous,Resources theme) throws ParseException
    {
               super("Ajouter un nouveau club", BoxLayout.y());
        current=this;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
      

        
        ArrayList<jardin> lis = ServiceClub.getInstance().getAllJardins();

       
  
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        setLayout(BoxLayout.y());
        TextField tfNom = new TextField();
        TextArea taDescription = new TextArea("WriteHere", 2, 80, TextArea.BASELINE);
        Calendar c = new Calendar();
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        System.out.println(datePicker.getText());
        String dateS = datePicker.toString().substring(datePicker.toString().indexOf("text = ") + 7, datePicker.toString().indexOf("text = ") + 15);
        Button imgBtn = new Button("parcourir");
        ComboBox cb=new ComboBox();
       
         for(int i = 0; i<lis.size(); i++) {
         int var1=lis.get(i).getId();
         String var2=lis.get(i).getNom();
            cb.addItem(var1);
                        

        }
        Club C=new Club();
        TextField tfimage = new TextField("","Veuillez saisir l'url de votre image");
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
        Label ldate = new Label("Date Event :");
        Label ldescription = new Label("Description :");
                Label ljardin = new Label("Jardin :");

        Label lphoto = new Label("Photo :");
        
        Button btnValider = new Button("Save");
        Button btnAnnuler = new Button("Annuler");
        
        addAll(lnom,tfNom,ldate,datePicker,lphoto,imgBtn,tfimage,ldescription,taDescription,ljardin , cb ,btnValider,btnAnnuler);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btnAnnuler.addActionListener((evt) -> {
            previous.showBack();
        });
        
      btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(taDescription.getText().length()==0) ||(tfimage.getText().length()==0) ||(datePicker.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                                               
                        Club ev = new Club(tfNom.getText(),taDescription.getText(),datePicker.getText(),tfimage.getText(),cb.getSelectedItem().toString());
                    
                        if( ServiceClub.getInstance().addClub(ev))
                        {

                            System.out.println(ev);
                                                        sendMail("chaima.hichri1@esprit.tn");

                            Dialog.show("Success","Club Ajouté",new Command("OK"));

                            




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
  public void sendMail(String Email) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/journal/sendmail.php?email="+ Email);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.err.println("Mail Sent");
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
}
