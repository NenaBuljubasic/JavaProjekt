/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabrijela
 */
public class BazaPodataka {
    public String urlic;
    
    private static final String INSERT_SQL = "INSERT INTO mjerenja(id, dimenzija, standardni, rekFFT, iterFFT, standJNI) VALUES(?, ?, ?, ?, ?, ?)";

    public BazaPodataka(){
       try{
           Class.forName("org.sqlite.JDBC");
       } catch(ClassNotFoundException ex){
           //
       }
    }
    public void stvoriBazu(String fileName){
        String url = "jdbc:sqlite:" + fileName + ".db";
        urlic = url;
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                //DatabaseMetaData meta = conn.getMetaData();
            }
            else{
                System.out.println("Nema baze");
            }
        }
        catch(SQLException e){ System.out.println("greška");}
    }
    public Connection veza(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(urlic);
            return conn;
        }
        catch(ClassNotFoundException | SQLException e)
        {return null;}
    }
    
    public void stvoriStol(){
        try{
            String stvori = "CREATE TABLE IF NOT EXISTS mjerenja (\n" + " id integer PRIMARY KEY,\n" + "dimenzija integer,\n" + "standardni integer,\n" + "rekFFT integer,\n" + "iterFFT integer,\n"+"standJNI integer);";
            Statement st = veza().createStatement();
            st.execute(stvori);
        }
        catch(SQLException e){
        //
        }
    }
    
    public List<UBazi> dohvatiIzBaze(){
       
        List< UBazi > rezultat = new ArrayList<>();
        Statement st = null;
        
        try{
            st = veza().createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM mjerenja");
            while(r.next()){
                UBazi novi = new UBazi(r.getInt("id"),
                        r.getInt("dimenzija"), r.getInt("standardni"),
                        r.getInt("rekFFT"), r.getInt("iterFFT"), r.getInt("standJNI"));
                rezultat.add(novi);
                
            }
        }
        catch(SQLException ex){
            rezultat = new ArrayList<>();
        }
        finally{
            this.close(st);
        }
        return rezultat;
    }
    
     public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int dodaj(UBazi element){
        int numRowsInserted = 0;
        PreparedStatement ps = null;
        try {
            ps = this.veza().prepareStatement(INSERT_SQL);
            ps.setInt(1, element.ID);
            ps.setInt(2, element.dimenzija);
            ps.setInt(3, element.standardni);
            ps.setInt(4, element.rekFFT);
            ps.setInt(5, element.iterFFT);  // You'll have to update this each and every year. BirthDate would be better.
            ps.setInt(6, element.standJNI);
            numRowsInserted = ps.executeUpdate();
           

        } catch (SQLException e) {
            System.out.println("Nije uspješno...");
        } finally {
            close(ps);
        }
        return numRowsInserted;
    }
}
