/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Blog;
import Entity.categorieA;
import Entity.comment;
import Util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saada
 */
public class CategorieService {

    Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInsatnce().getConnection();
    }

    /*public void AjouterCategorie(categorieA c) {
        try {

          String req = "INSERT INTO categorie_a (id,type_a) "
                    + "VALUES ('" + c.getId() + "', '" + c.getType() + "')";


            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajouter avec succ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    public void AjouterCategorie(categorieA c) {
        try {
            String req = "SELECT id FROM categorie_a WHERE type_a = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getType());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("La catégorie " + c.getType() + " existe déjà.");
            } else {
                req = "INSERT INTO categorie_a (id, type_a) VALUES (?, ?)";
                ps = cnx.prepareStatement(req);
                ps.setInt(1, c.getId());
                ps.setString(2, c.getType());
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("La catégorie " + c.getType() + " a été ajoutée avec succès.");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public List<categorieA> Recuperer() {
        List<categorieA> categories = new ArrayList<>();
        try {
            String req = "select * from categorie_a ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                categorieA cat = new categorieA();
                cat.setId(rs.getInt(1));
                cat.setType(rs.getString("type_a"));

                categories.add(cat);
            }
            System.out.print(categories);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    /*public void ModifierCo(categorieA cat) {
        try {

            String req = "UPDATE categorie_a SET type_a=? WHERE id=?;";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, cat.getType());

            ps.setInt(2, cat.getId());
            ps.executeUpdate();
            System.out.println("categorie  modifié avec succ");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
   public void ModifierCo(categorieA cat) {
    try {
        String req = "UPDATE categorie_a SET type_a=? WHERE id=?;";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        // Vérifier si le type_a est différent de l'ancien
        if (!cat.getType().equals(getCategorieById(cat.getId()).getType())) {
            // Vérifier si le nouveau type_a existe déjà dans la base de données
            if (getCategorieByType(cat.getType()) == null) {
                ps.setString(1, cat.getType());
                ps.setInt(2, cat.getId());
                ps.executeUpdate();
                System.out.println("La catégorie a été modifiée avec succès");
            } else {
                System.out.println("Impossible de modifier la catégorie : ce type existe déjà");
            }
        } else {
            System.out.println("Il n'y a pas eu de modifications à apporter");
        }

    } catch (SQLException ex) {
        Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void SupprimerCat(int ID) {
        try {
            Statement st = cnx.createStatement();
            String checkReq = "SELECT COUNT(*) FROM categorie_a WHERE id = " + ID;
            ResultSet result = st.executeQuery(checkReq);
            result.next();
            int count = result.getInt(1);
            if (count == 0) {
                System.out.println("La categorie avec l'id = " + ID + " n'existe pas...");
            } else {
                String req = "DELETE FROM categorie_a WHERE id = " + ID;
                st.executeUpdate(req);
                System.out.println("La categorie avec l'id = " + ID + " a été supprimée avec succès...");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Boolean supprimer(categorieA t ) {
    try {
        String qry;
    Statement st = cnx.createStatement();

       
            qry = "DELETE FROM `categorie_a` WHERE `id` = " + t.getId();
        
        st = cnx.createStatement();
        st.executeUpdate(qry);
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
    }

   public categorieA getCategorieById(int id) {
    categorieA cat = null;
    try {
        String req = "SELECT * FROM categorie_a WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cat = new categorieA();
            cat.setId(rs.getInt("id"));
            cat.setType(rs.getString("type_a"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return cat;
}
private categorieA getCategorieByType(String type) {
    categorieA cat = null;
    try {
        String req = "SELECT * FROM categorie_a WHERE type_a = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cat = new categorieA(rs.getInt("id"), rs.getString("type_a"));
        }

    } catch (SQLException ex) {
        Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cat;
}



}
