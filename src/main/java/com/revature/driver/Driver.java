package com.revature.driver;

import com.revature.dao.artistDAO;
import com.revature.dao.paintingDAO;
import com.revature.model.Artist;
import com.revature.model.Painting;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

public class Driver {


    public static Connection conn = ConnectionUtil.getConnection();
    public static void main(String[] args) {
        artistDAO artistDAO = new artistDAO();

        List<Artist> allArtists = artistDAO.getAllArtists();
        for (Artist A : allArtists) {
            System.out.println(A);
        }


        Painting painting = new Painting(2,"Dream Caused by the Flight of a Bee",
                null,
                "Surrealism", 1944);

        paintingDAO paintingDAO = new paintingDAO();

        List<Painting> allPaintings = paintingDAO.getAllPaintings();

        System.out.println(allPaintings.toString());


    }
}
