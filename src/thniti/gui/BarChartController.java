/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thniti.gui;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import thniti.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author FARAH
 */
public class BarChartController implements Initializable {

    @FXML
    private PieChart piechart;

    private BarChart<?, ?> chart;
    
     ObservableList< PieChart.Data> piechartdata;
  ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
       
        piechart.setData(piechartdata);
    }    
    public void loadData() {

        String query = "select COUNT(*) as count ,etat from reclamation GROUP BY etat "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        Connection cnx = DataSource.getInstance().getCnx();

        try {

            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("etat"), rs.getInt("count")));
                p.add(rs.getString("etat"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  
}
