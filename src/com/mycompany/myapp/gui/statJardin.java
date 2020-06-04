/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.jardin;
import com.mycompany.myapp.services.ServiceJardin;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class statJardin extends SideMenuBaseForm {
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);
        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) throws ParseException {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        ArrayList<jardin> lis = ServiceJardin.getInstance().getAllClubs();
              for(int i = 0; i<lis.size(); i++) {
                                    double m=lis.get(i).getNbEnfants();
String nom= lis.get(i).getNom();
            series.add(nom + ++k, m);
        }

        return series;
    }

    public  statJardin(Form previous,Resources res) throws ParseException {
        
        
         super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        
        
       
        

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                new Label("statistiques jardin", "CenterTitle")
                        
                       
                );
        tb.setTitleComponent(titleCmp);
        

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        setupSideMenu(res);
   ArrayList<jardin> lis = ServiceJardin.getInstance().getAllClubs();
              for(int i = 0; i<lis.size(); i++) {
                  double m=lis.get(i).getNbEnfants();
        double[] values = new double[]{m};
        

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);

        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

    add(c);
             }
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

