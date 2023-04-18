/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Util.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class StatComController implements Initializable {

    @FXML
    private PieChart StatCom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Connection cnx;
        cnx = MyDB.getInsatnce().getConnection();
         ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    try {
        String query = "SELECT articles.id, articles.titre_article, COUNT(commentaires.id) AS num_comments "
                    + "FROM articles LEFT JOIN commentaires ON articles.id = commentaires.id_article_id "
                    + "GROUP BY articles.id";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("STATISTIQUES DES LIKES\n");
        while (rs.next()) {
             int id = rs.getInt("id");
          String title = rs.getString("titre_article");
                int numComments = rs.getInt("num_comments");
                System.out.println("Article #" + id + " - " + title + " - " + numComments + " commentaires");
            // Créer un nouvel objet PieChart.Data avec les données récupérées
            PieChart.Data data = new PieChart.Data(title + " - " + numComments + " likes",numComments);
            // Ajouter l'objet PieChart.Data à la liste observable
            pieChartData.add(data);
        }
        System.out.println();
        rs.close();
        stmt.close();
    } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des statistiques des commentaires : " + ex.getMessage());
    }

    pieChartData.forEach(data ->
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), " - ", data.getPieValue(), " Comments"
                    )
            )
    );

  StatCom.getData().addAll(pieChartData);

    // Définir le rayon du graphique à une valeur plus grande pour l'agrandir
   StatCom.setStartAngle(90);
    // Mettre les sections du graphique dans le sens anti-horaire
    StatCom.setClockwise(false);
}
    }    
    

