package com.revature.dao;

import com.revature.model.Painting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.revature.driver.Driver.conn;


public class paintingDAO {

    public List<Painting> getAllPaintings() {
        List<Painting> allPaintings = new ArrayList<>();
        Statement statement = null;

        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM painting");
            while (rs.next()) {
                Painting nextPainting = new Painting(rs.getInt("artist_id"),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("genre"),
                        rs.getInt("year_made"));
                allPaintings.add(nextPainting);

            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPaintings;
    }

    public Painting getPainting(String title) {
        Painting myPainting = null;

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM painting WHERE title = ?");
            int parameterIndex = 0;
            statement.setString(++parameterIndex, title); //iteration happens before vs after the line executes
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                myPainting = new Painting(rs.getInt("artist_id"),
                        rs.getString("title"),
                        rs.getString("url"),
                        rs.getString("genre"),
                        rs.getInt("year_made"));
            }
            rs.close();
            return myPainting;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addPainting(Painting painting) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO painting (artist_id, title, url, genre, year_made)" +
                            "VALUES" +
                            "(?,?,?,?,?)");
            int parameterIndex = 0;
            statement.setInt(++parameterIndex, painting.getArtist_id());
            statement.setString(++parameterIndex, painting.getTitle());
            statement.setString(++parameterIndex, painting.getUrl());
            statement.setString(++parameterIndex, painting.getGenre());
            statement.setInt(++parameterIndex, painting.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePainting(int year) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM painting WHERE year_made = " + year);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaintingTitle(int artistId) {

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE painting SET title='Mondays are the worst' WHERE artist_id=" + artistId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
