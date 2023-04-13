/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author saada
 */
public class PostLike {

    private int id, articles_id, user_id;

    public PostLike() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + this.articles_id;
        hash = 43 * hash + this.user_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PostLike other = (PostLike) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.articles_id != other.articles_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PostLike{" + "id=" + id + ", articles_id=" + articles_id + ", user_id=" + user_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticles_id() {
        return articles_id;
    }

    public void setArticles_id(int articles_id) {
        this.articles_id = articles_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public PostLike(int id, int articles_id, int user_id) {
        this.id = id;
        this.articles_id = articles_id;
        this.user_id = user_id;
    }

}
