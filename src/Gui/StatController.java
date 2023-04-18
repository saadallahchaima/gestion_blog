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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author saada
 */
public class StatController implements Initializable {

    @FXML
    private PieChart piestat;

    /**
     * Initializes the controller class.
     */
    @Override

public void initialize(URL url, ResourceBundle rb) {
       Connection cnx;
        cnx = MyDB.getInsatnce().getConnection();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    try {
        String query = "SELECT articles.id, articles.titre_article, COUNT(`like`.id) AS num_likes "
                + "FROM articles LEFT JOIN `like` ON articles.id = `like`.articles_id "
                + "GROUP BY articles.id";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("STATISTIQUES DES LIKES\n");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("titre_article");
            int numLikes = rs.getInt("num_likes");
            System.out.println("Article #" + id + " - " + title + " - " + numLikes + " likes");

            // Créer un nouvel objet PieChart.Data avec les données récupérées
            PieChart.Data data = new PieChart.Data(title + " - " + numLikes + " likes", numLikes);
            // Ajouter l'objet PieChart.Data à la liste observable
            pieChartData.add(data);
        }
        System.out.println();
        rs.close();
        stmt.close();
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des statistiques des likes : " + ex.getMessage());
    }

    pieChartData.forEach(data ->
            data.nameProperty().bind(
                    Bindings.concat(
                            data.getName(), " - ", data.getPieValue(), " likes"
                    )
            )
    );

    piestat.getData().addAll(pieChartData);

    // Définir le rayon du graphique à une valeur plus grande pour l'agrandir
     piestat.setStartAngle(90);
    // Mettre les sections du graphique dans le sens anti-horaire
    piestat.setClockwise(false);
}
}