/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Blog;
import Entity.comment;
import Util.MyDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saada
 */
public class CommentService {
    
    Connection cnx;
    
    public CommentService() {
        cnx = MyDB.getInsatnce().getConnection();
    }

    /*public void AjouterCo(comment c) {
        try {
            String query = "INSERT INTO commentaires (id, nom_c, email_c, id_article_id, contenu_c, date_com, approved) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt;
            pstmt = cnx.prepareStatement(query);
            {

                pstmt.setInt(1, c.getID());
                pstmt.setString(2, c.getNom_c());
                pstmt.setString(3, c.getEmail());
                pstmt.setInt(4, c.getId_article());
                pstmt.setString(5, c.getContenu_c());
                pstmt.setDate(6, new java.sql.Date(c.getDate_com().getTime()));
                pstmt.setInt(7, c.getApproved());

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new comment was inserted successfully!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     */
 public void AjouterCo(comment c) {
    try {
        // Vérifier si l'article existe
        String queryArticle = "SELECT * FROM articles WHERE id = ?";
        PreparedStatement pstmtArticle = cnx.prepareStatement(queryArticle);
        pstmtArticle.setInt(1, c.getId_article());
        ResultSet rsArticle = pstmtArticle.executeQuery();
        if (!rsArticle.next()) {
            System.out.println("L'article n'existe pas !");
            return;
        }

        // Insérer le commentaire
        String query = "INSERT INTO commentaires (id, nom_c, email_c, id_article_id, contenu_c, date_com, approved) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(query);
        pstmt.setInt(1, c.getID());
        pstmt.setString(2, c.getNom_c());
        pstmt.setString(3, c.getEmail());
        pstmt.setInt(4, c.getId_article());
        pstmt.setString(5, c.getContenu_c());
        pstmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
        pstmt.setInt(7, c.getApproved());

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Un nouveau commentaire a été inséré avec succès !");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    public void ModifierCo(comment c) {
        try {
            
            String req = "UPDATE commentaires SET nom_c=?, email_c=?, id_article_id=?, contenu_c=?, date_com=?, approved=? WHERE id=?;";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom_c());
            ps.setString(2, c.getEmail());
            ps.setInt(3, c.getId_article());
            ps.setString(4, c.getContenu_c());
            if (c.getDate_com() == null) {
                System.out.println("La date du commentaire est nulle, impossible de modifier le commentaire.");
                return;
            }
            ps.setTimestamp(5, new Timestamp(c.getDate_com().getTime()));
            ps.setInt(6, c.getApproved());
            ps.setInt(7, c.getID());
            ps.executeUpdate();
            System.out.println("Commentaire modifié avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ModifierCommentaire(comment c) {
        try {
            
            String req = "UPDATE commentaires SET  approved=? WHERE id=?;";
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setInt(1, c.getApproved());
            ps.setInt(2, c.getID());
            ps.executeUpdate();
            System.out.println("Commentaire modifié avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SupprimerCo(int ID) {
        try {
            Statement st = cnx.createStatement();
            String req = "DELETE FROM commentaires WHERE id = " + ID + "";
            st.executeUpdate(req);
            System.out.println("Le commentaire avec l'id = " + ID + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<comment> Recuperer(int idp) {
        List<comment> comments = new ArrayList<>();
        try {
            String req = "select * from commentaires where id_article_id= " + idp;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                comment p = new comment();
                p.setID(rs.getInt(1));
                
                p.setNom_c(rs.getString("nom_c"));
                p.setEmail(rs.getString("email_c"));
                p.setId_article(idp);
                p.setContenu_c(rs.getString("contenu_c"));
                
                p.setDate_com(rs.getTimestamp("date_com"));
                p.setApproved(rs.getInt("Approved"));
                comments.add(p);
            }
            for (comment comment : comments) {
                System.out.println("Commentaire #" + comment.getID());
                System.out.println("Nom : " + comment.getNom_c());
                System.out.println("Email : " + comment.getEmail());
                System.out.println("Contenu : " + comment.getContenu_c());
                System.out.println("Date : " + comment.getDate_com());
                System.out.println("Approuvé : " + comment.getApproved());
                System.out.println("----------------------");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comments;
    }
    
    public List<comment> Recuperer() {
        List<comment> posts = new ArrayList<>();
        try {
            String req = "select * from commentaires ORDER BY date_com DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                comment p = new comment();
                p.setID(rs.getInt(1));
                
                p.setNom_c(rs.getString("nom_c"));
                p.setEmail(rs.getString("email_c"));
                p.setContenu_c(rs.getString("contenu_c"));
                p.setId_article(rs.getInt("id_article_id"));
                p.setDate_com(rs.getTimestamp("date_com"));
                p.setApproved(rs.getInt("Approved"));
                posts.add(p);
            }
            System.out.print(posts);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
    
    public comment detail(int id) {
        comment p = new comment();
        try {
            String req = "SELECT * FROM commentaires WHERE id = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                p.setID(id);
                p.setContenu_c(rs.getString("contenu_c"));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    public List<comment> getCommentsByArticle(int articleId) {
        try {
            List<comment> comments = new ArrayList<>();
            String req = "SELECT * FROM commentaires WHERE id_article_id = ?";
            try {
                PreparedStatement st = cnx.prepareStatement(req);
                st.setInt(1, articleId);
                ResultSet rs = st.executeQuery();
                
                while (rs.next()) {
                    java.sql.Date date_com = rs.getDate("date_com");
                    java.sql.Timestamp timestamp = null;
                    if (date_com != null) {
                        timestamp = new java.sql.Timestamp(date_com.getTime());
                    }
                    comment comment = new comment(rs.getInt("ID"), rs.getInt("id_article_id"), rs.getString("contenu_c"), timestamp, rs.getString("nom_c"), rs.getString("email_c"), rs.getInt("approved"));
                    comments.add(comment);
                }
                
            } catch (SQLException e) {
                System.err.println("Error getting comments by article: " + e.getMessage());
            }
            
            return comments;
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return null;
        }
    }
    
    public List<comment> getCommentHistory(int articleId) {
        List<comment> comments = getCommentsByArticle(articleId);
        Collections.sort(comments, new Comparator<comment>() {
            public int compare(comment c1, comment c2) {
                if (c1.getDate_com() == null || c2.getDate_com() == null) {
                    return 0;
                }
                return c1.getDate_com().compareTo(c2.getDate_com());
            }
        });
        return comments;
    }
    
}
