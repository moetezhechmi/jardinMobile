/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author root
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
     
        
        
       getToolbar().addMaterialCommandToSideMenu("clubs", FontImage.MATERIAL_DASHBOARD,  e -> showClubForm(res));
       getToolbar().addMaterialCommandToSideMenu("Enfants", FontImage.MATERIAL_CHILD_CARE,  e -> showEnfantForm(res));
       getToolbar().addMaterialCommandToSideMenu("Activites", FontImage.MATERIAL_WORK,  e -> showActiviteForm(res));
        getToolbar().addMaterialCommandToSideMenu("Incidents", FontImage.MATERIAL_DANGEROUS,  e -> showIncidentForm(res));
        getToolbar().addMaterialCommandToSideMenu("Jardins", FontImage.MATERIAL_CHILD_CARE,  e -> showJardinForm(res));
        getToolbar().addMaterialCommandToSideMenu("Réclamations", FontImage.MATERIAL_MESSAGE,  e -> showReclamationForm(res));






     
    }
    
    
    protected abstract void showClubForm(Resources res);
    protected abstract void showEnfantForm(Resources res);
    protected abstract void showActiviteForm(Resources res);
    protected abstract void showIncidentForm(Resources res);
    protected abstract void showJardinForm(Resources res);
    protected abstract void showReclamationForm(Resources res);

    





 
   

    
}

