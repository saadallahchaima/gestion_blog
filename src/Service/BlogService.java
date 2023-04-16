/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Blog;
import Entity.PostLike;
import Entity.categorieA;
import Entity.comment;
import Util.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author saada
 */
public class BlogService implements IServiceA<Blog> {

    Connection cnx;

    public BlogService() {
        cnx = MyDB.getInsatnce().getConnection();
    }

    @Override
    /* public void AjouterBlog(Blog article) {
        try {
            // Vérifier si l'article existe déjà dans la base de données
            String selectQuery = "SELECT COUNT(*) AS count FROM articles WHERE titre_article = ?";
            PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
            selectStatement.setString(1, article.getTitre_article());
            ResultSet result = selectStatement.executeQuery();
            result.next();
            int count = result.getInt("count");

            // Si l'article n'existe pas déjà, l'ajouter à la base de données
            if (count == 0) {
                int isBestValue = article.isIs_best() ? 1 : 0;
                String insertQuery = "INSERT INTO articles (id, id_categ_a_id, titre_article, contenu_article, auteur_article, image_article, date_a, is_best) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = cnx.prepareStatement(insertQuery);
                insertStatement.setInt(1, article.getID());
                insertStatement.setInt(2, article.getId_categ_a_id());
                insertStatement.setString(3, article.getTitre_article());
                insertStatement.setString(4, article.getContenu_article());
                insertStatement.setString(5, article.getAuteur_article());
                insertStatement.setString(6, article.getImage());
                insertStatement.setDate(7, article.getDate());
                insertStatement.setInt(8, isBestValue);
                insertStatement.executeUpdate();
                System.out.println("Article ajouté avec succès.");
            } else {
                System.out.println("Un article avec ce titre existe déjà dans la base de données.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     */

    public void AjouterBlog(Blog article) {
        try {
            // Vérifier si l'article existe déjà dans la base de données
            String selectQuery = "SELECT COUNT(*) AS count FROM articles WHERE titre_article = ?";
            PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
            selectStatement.setString(1, article.getTitre_article());
            ResultSet result = selectStatement.executeQuery();
            result.next();
            int count = result.getInt("count");

            // Si l'article n'existe pas déjà, l'ajouter à la base de données
            if (count == 0) {
                int isBestValue = article.isIs_best() ? 1 : 0;
                String insertQuery = "INSERT INTO articles (id, id_categ_a_id, titre_article, contenu_article, auteur_article, image_article, date_a, is_best) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = cnx.prepareStatement(insertQuery);
                insertStatement.setInt(1, article.getID());
                insertStatement.setInt(2, article.getId_categ_a_id());
                insertStatement.setString(3, article.getTitre_article());
                insertStatement.setString(4, article.getContenu_article());
                insertStatement.setString(5, article.getAuteur_article());
                insertStatement.setString(6, article.getImage());
                insertStatement.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
                insertStatement.setInt(8, isBestValue);
                insertStatement.executeUpdate();
                System.out.println("Article ajouté avec succès.");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Un article avec ce titre existe déjà dans la base de données.");
            alert.showAndWait();
                System.out.println("Un article avec ce titre existe déjà dans la base de données.");
            }
        } catch (SQLException ex) {
            System.out.println(" mouch jawek behi ");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Blog> Recuperer() {
        List<Blog> posts = new ArrayList<>();
        try {
            String req = "select * from articles ORDER BY date_a DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Blog p = new Blog();
                p.setID(rs.getInt(1));
                p.setId_categ_a_id(rs.getInt(1));;
                p.setTitre_article(rs.getString("titre_article"));
                p.setContenu_article(rs.getString("contenu_article"));
                p.setAuteur_article(rs.getString("auteur_article"));
                p.setDate(rs.getDate("date_a"));
                p.setImage(rs.getString("image_article"));
                p.setIs_best(rs.getInt("is_best"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }

    @Override
    public void SupprimerBlog(int ID) {
        try {
            Statement st = cnx.createStatement();
            String checkReq = "SELECT id FROM articles WHERE id = " + ID + "";
            ResultSet rs = st.executeQuery(checkReq);
            if (rs.next()) { // si l'article existe
                String req = "DELETE FROM articles WHERE id = " + ID + "";
                st.executeUpdate(req);
                System.out.println("L'Article avec l'id = " + ID + " a été supprimer avec succès...");
            } else { // sinon
                System.out.println("L'Article avec l'id = " + ID + " n'existe pas...");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ModifierBlog(int id, Blog v) {

        try {

            String req = "UPDATE articles SET id_categ_a_id=?, titre_article=?, contenu_article=?, auteur_article=?, image_article=?, date_a=?, is_best=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, v.getId_categ_a_id());
            ps.setString(2, v.getTitre_article());
            ps.setString(3, v.getContenu_article());
            ps.setString(4, v.getAuteur_article());
            ps.setString(5, v.getImage());
            ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setInt(7, v.getIs_best());
            ps.setInt(8, id);

            ps.executeUpdate();
            System.out.println("Article modifié avec succès");

        } catch (SQLException ex) {
            Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void ModifierBlog2(Blog b) {
    try {
        String req = "UPDATE articles SET id_categ_a_id=?, titre_article=?, contenu_article=?, auteur_article=?, image_article=?, date_a=?, is_best=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);

        // Vérifier si le type_a est différent de l'ancien
        ps.setInt(1, b.getId_categ_a_id());
        ps.setString(2, b.getTitre_article());
        ps.setString(3, b.getContenu_article());
        ps.setString(4, b.getAuteur_article());
        ps.setString(5, b.getImage());
        ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
        ps.setInt(7, b.getIs_best());
        ps.setInt(8, b.getID());

        ps.executeUpdate();
        System.out.println("Article modifié avec succès");

    } catch (SQLException ex) {
        Logger.getLogger(BlogService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    /*public List<comment> getComments(int id) {
    List<comment> comments = new ArrayList<>();
    try {
        String req = "SELECT * FROM commentaires WHERE id_article_id = " + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            comment c = new comment();
            c.setID(rs.getInt("id"));
            c.setNom_c(rs.getString("nom_c"));
            c.setEmail(rs.getString("email"));
            c.setContenu_c(rs.getString("contenu_c"));
            c.setDate_com(rs.getDate("date_com"));
            c.setApproved(rs.getInt("approved"));
            comments.add(c);
        }
        System.out.print(comments);

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return comments; 
}*/
 public List<comment> getComments(int id) {
    List<comment> comments = new ArrayList<>();
    try {
        String req = "SELECT * FROM commentaires WHERE id_article_id = " + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
              comment c = new comment();
            c.setID(rs.getInt("id"));
            c.setNom_c(rs.getString("nom_c"));
            c.setEmail(rs.getString("email_c"));
              c.setID(rs.getInt("id_article"));
            c.setContenu_c(rs.getString("contenu_c"));
Timestamp timestamp = rs.getTimestamp("date_com");
c.setDate_com(timestamp);
c.setApproved(rs.getInt("approved"));
            comments.add(c);
        }
        System.out.println("Comments retrieved: " + comments.size());

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return comments; 
}
     
    @Override
    public List<comment> getCommentsWithArticleTitles() {
        List<comment> comments = new ArrayList<>();
        try {
            String req = "SELECT c.*, a.titre_article FROM commentaires c JOIN articles a ON c.id_article_id = a.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                comment c = new comment();
                c.setID(rs.getInt("id"));
                c.setNom_c(rs.getString("nom_c"));
                c.setEmail(rs.getString("email_c"));
                c.setId_article(rs.getInt("id_article_id"));
                c.setContenu_c(rs.getString("contenu_c"));
                c.setDate_com(rs.getTimestamp("date_com"));
                c.setApproved(rs.getInt("approved"));

                Blog a = new Blog();
                a.setID(rs.getInt("id_article_id"));
                a.setTitre_article(rs.getString("titre_article"));

                c.setArticle(a);
                comments.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return comments;
    }

    @Override
    public List<PostLike> likes(int id) {
        List<PostLike> posts = new ArrayList<>();
        try {
            String req = "select * from `like` where articles_id =" + id + ";";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                PostLike p = new PostLike();
                p.setId(rs.getInt("id"));
                p.setArticles_id(rs.getInt("articles_id"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }

    @Override
    public boolean ajouterlike(int idp, String email) {
        boolean a = false;
        try {
            String req = "INSERT INTO `like` (articles_id, user_id) "
                    + "SELECT ?, user.id "
                    + "FROM user "
                    + "WHERE user.email = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idp);
            ps.setString(2, email);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("like ajoutée");
                a = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

    @Override
    public List<PostLike> islikedbyuser(int idp, int idc) {

        List<PostLike> posts = new ArrayList<>();
        try {
            String req = "Select * from `like` where user_id= '" + idc + "'and articles_id ='" + idp + "';";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                PostLike p = new PostLike();
                p.setId(rs.getInt("id"));
                p.setArticles_id(rs.getInt("articles_id"));
                p.setUser_id(rs.getInt("user_id"));
                posts.add(p);
            }
            System.out.println(posts.size());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }

    @Override
    public void Supprimerlike(int id, int idc) {
        try {
            String req = "DELETE FROM `like` WHERE articles_id = ? and user_id = ?;";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            pstmt.setInt(2, idc);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("like supprimé avec succès...");
            } else {
                System.out.println("Aucun like n'a été supprimé.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void afficherAuteursPlusActifs() {
        try {
            String req = "SELECT user_id, COUNT(*) as num_articles FROM `like` GROUP BY user_id ORDER BY num_articles DESC";
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(req);

            System.out.println("Les auteurs les plus actifs en fonction du nombre d'articles écrits :");
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int numArticles = rs.getInt("num_articles");

                // Récupérer le nom d'utilisateur et l'avatar de l'auteur à partir de la table des utilisateurs
                String req2 = "SELECT nom, prenom FROM user WHERE id=?";
                PreparedStatement ps = cnx.prepareStatement(req2);
                ps.setInt(1, userId);
                ResultSet rs2 = ps.executeQuery();
                if (rs2.next()) {
                    String username = rs2.getString("nom");
                    String prenom = rs2.getString("prenom");
                    System.out.println(username + " (" + prenom + ") : " + numArticles + " articles");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Récupère les statistiques des likes pour chaque article
    public void getLikeStats(Connection conn) {
        try {
            String query = "SELECT articles.id, articles.titre_article, COUNT(`like`.id) AS num_likes "
                    + "FROM articles LEFT JOIN `like` ON articles.id = `like`.articles_id "
                    + "GROUP BY articles.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("STATISTIQUES DES LIKES\n");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("titre_article");
                int numLikes = rs.getInt("num_likes");
                System.out.println("Article #" + id + " - " + title + " - " + numLikes + " likes");
            }
            System.out.println();
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des statistiques des likes : " + ex.getMessage());
        }
    }

    // Récupère les statistiques des commentaires pour chaque article
    public void getCommentStats(Connection conn) {
        try {
            String query = "SELECT articles.id, articles.titre_article, COUNT(commentaires.id) AS num_comments "
                    + "FROM articles LEFT JOIN commentaires ON articles.id = commentaires.id_article_id "
                    + "GROUP BY articles.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("STATISTIQUES DES COMMENTAIRES\n");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("titre_article");
                int numComments = rs.getInt("num_comments");
                System.out.println("Article #" + id + " - " + title + " - " + numComments + " commentaires");
            }
            System.out.println();
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des statistiques des commentaires : " + ex.getMessage());
        }
    }

    public List<Blog> rechercheAvancee(String titre, String auteur) {
        List<Blog> resultats = new ArrayList<>();
        try {
            String sql = "SELECT * FROM articles WHERE titre_article LIKE ? AND auteur_article LIKE ?";

            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, "%" + titre + "%");
            pstmt.setString(2, "%" + auteur + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(rs.getInt("ID"), rs.getInt("id_categ_a_id"), rs.getString("titre_article"), rs.getString("contenu_article"),
                        rs.getString("auteur_article"), rs.getString("image_article"), rs.getDate("date_a"), rs.getBoolean("is_best"));
                resultats.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultats;
    }

    public List<Blog> recherche(String motCle) {
        List<Blog> resultats = new ArrayList<>();
        try {
            String sql = "SELECT * FROM articles WHERE titre_article LIKE ? OR contenu_article LIKE ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, "%" + motCle + "%");
            pstmt.setString(2, "%" + motCle + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(rs.getInt("ID"), rs.getInt("id_categ_a_id"), rs.getString("titre_article"), rs.getString("contenu_article"),
                        rs.getString("auteur_article"), rs.getString("image_article"), rs.getDate("date_a"), rs.getBoolean("is_best"));
                resultats.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultats;
    }

    public List<Blog> AfficherArticlesByCategoryId(int categoryId) {
        List<Blog> articles = new ArrayList<>();
        try {
            String req = "SELECT * FROM articles WHERE id_categ_a_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog article = new Blog();
                // Mappez les colonnes de la table articles aux propriétés de l'objet Blog
                article.setID(rs.getInt("id"));
                article.setId_categ_a_id(rs.getInt("id_categ_a_id"));
                article.setTitre_article(rs.getString("titre_article"));
                article.setContenu_article(rs.getString("contenu_article"));
                article.setAuteur_article(rs.getString("auteur_article"));
                article.setImage(rs.getString("image_article"));
                article.setDate(rs.getDate("date_a"));
                article.setIs_best(rs.getInt("is_best"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return articles;
    }

    public List<Blog> filterArticles(int categoryId, String title) {
        List<Blog> articles = new ArrayList<>();
        try {
            String req = "SELECT * FROM articles WHERE id_categ_a_id = ? AND titre_article LIKE ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, categoryId);
            ps.setString(2, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog article = new Blog();
                // Mappez les colonnes de la table articles aux propriétés de l'objet Blog
                article.setID(rs.getInt("id"));
                article.setId_categ_a_id(rs.getInt("id_categ_a_id"));
                article.setTitre_article(rs.getString("titre_article"));
                article.setContenu_article(rs.getString("contenu_article"));
                article.setAuteur_article(rs.getString("auteur_article"));
                article.setImage(rs.getString("image_article"));
                article.setDate(rs.getDate("date_a"));
                article.setIs_best(rs.getInt("is_best"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return articles;
    }

    public List<Blog> getAllArticlesByCategory(int categoryId) {
        List<Blog> articles = new ArrayList<>();
        try {
            String req = "SELECT * FROM articles "
                    + "JOIN categorie_a ON articles.id_categ_a_id = categorie_a.id "
                    + "WHERE categorie_a.id = ?";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Blog article = new Blog(rs.getInt("ID"),
                        rs.getInt("id_categ_a_id"),
                        rs.getString("titre_article"),
                        rs.getString("contenu_article"),
                        rs.getString("auteur_article"),
                        rs.getString("image_article"),
                        rs.getDate("date_a"),
                        rs.getBoolean("is_best"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching articles by category: " + ex.getMessage());
        }

        return articles;
    }

    public List<Blog> getAllArticlesSortedByDate(String sortColumn, String sortOrder) {
        List<Blog> articles = new ArrayList<>();
        try {
            String req = "SELECT * FROM articles ORDER BY " + sortColumn + " " + sortOrder;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Blog article = new Blog();
                article.setID(rs.getInt("id"));
                article.setId_categ_a_id(rs.getInt("id_categ_a_id"));
                article.setTitre_article(rs.getString("titre_article"));
                article.setContenu_article(rs.getString("contenu_article"));
                article.setAuteur_article(rs.getString("auteur_article"));
                article.setImage(rs.getString("image_article"));
                article.setDate(rs.getDate("date_a"));
                article.setIs_best(rs.getInt("is_best"));
                articles.add(article);
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching articles: " + ex.getMessage());
        }

        return articles;
    }

    public List<Blog> getFavoriteArticles() {
        List<Blog> favoriteArticles = new ArrayList<>();
        try {
            String req = "SELECT * FROM articles WHERE is_best = 1";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Blog article = new Blog();
                article.setID(rs.getInt("id"));
                article.setId_categ_a_id(rs.getInt("id_categ_a_id"));
                article.setTitre_article(rs.getString("titre_article"));
                article.setContenu_article(rs.getString("contenu_article"));
                article.setAuteur_article(rs.getString("auteur_article"));
                article.setImage(rs.getString("image_article"));
                article.setDate(rs.getDate("date_a"));
                article.setIs_best(rs.getInt("is_best"));
                favoriteArticles.add(article);
            }

            if (favoriteArticles.isEmpty()) {
                System.out.println("Aucun article favori trouvé.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return favoriteArticles;
    }

    public Map<Blog, Integer> compterNbCommentaires() {
        Map<Blog, Integer> nbCommentaires = new HashMap<>();
        try {
            String query = "SELECT articles.*, COUNT(commentaires.id) AS nb_commentaires \n"
                    + "FROM articles \n"
                    + "LEFT JOIN commentaires ON articles.id = commentaires.id_article_id \n"
                    + "GROUP BY articles.id;";
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                int idCategorie = rs.getInt("id_categ_a_id");
                String titre = rs.getString("titre_article");
                String contenu = rs.getString("contenu_article");
                String auteur = rs.getString("auteur_article");
                String image = rs.getString("image_article");
                LocalDate date = rs.getDate("date_a").toLocalDate();
                int is_best = rs.getInt("is_best");

                Blog article = new Blog(id, idCategorie, titre, contenu, auteur, image, date, is_best);
                int nbComm = rs.getInt("nb_commentaires");
                nbCommentaires.put(article, nbComm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nbCommentaires;
    }

    public List<Blog> getArticlesPopulairesParCommentaires(int limit) {
        List<Blog> articles = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT articles.* FROM articles "
                    + "INNER JOIN commentaires ON articles.id = commentaires.id_article_id "
                    + "ORDER BY (SELECT COUNT(*) FROM commentaires WHERE id_article_id = articles.id) DESC "
                    + "LIMIT ?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Blog article = new Blog();
                article.setID(rs.getInt("id"));
                article.setId_categ_a_id(rs.getInt("id_categ_a_id"));
                article.setTitre_article(rs.getString("titre_article"));
                article.setContenu_article(rs.getString("contenu_article"));
                article.setAuteur_article(rs.getString("auteur_article"));
                article.setImage(rs.getString("image_article"));
                Timestamp timestamp = rs.getTimestamp("date_a");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                LocalDate localDate = localDateTime.toLocalDate();
                article.setDate(localDate);
                article.setIs_best(rs.getInt("is_best"));

                articles.add(article);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return articles;
    }

    public int chercherCategorieA(String nom) throws SQLException {
        int id = 0;
        String requete = "SELECT id FROM categorie_a WHERE type_a = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, nom);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public int chercherBlog(String titre) throws SQLException {
        int id = 0;
        String requete = "SELECT id FROM articles WHERE titre_article = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, titre);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    public int chercherbest(String best) throws SQLException {
        int id = 0;
        String requete = "SELECT id FROMBlog WHERE best = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, best);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }


}
