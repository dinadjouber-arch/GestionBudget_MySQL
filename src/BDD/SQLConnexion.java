package BDD;

import java.sql.*;


public class SQLConnexion {


    //Connexion a la BDD
    private static final String URL="jdbc:mysql://localhost:3306/gestion_depenses";
    private static final String user="root";
    private static final String password="20062006D!";




    public static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }

    public static void connexion() {

        Connection con=null;
        Statement stat=null;

        try {
            con=DriverManager.getConnection(URL,user,password);
            System.out.println("connected to the DB");
            stat= con.createStatement();

            String sql="SELECT * FROM categorie;";
            ResultSet result=stat.executeQuery(sql);

           /* while (result.next())
            {
                int id= result.getInt("id_categorie");
                String nom= result.getString("nom");
                System.out.println("ID : "+id+"  nom : "+nom);

            }*/} catch (Exception ex) {ex.printStackTrace();}
        finally
        {
            try{
                if (stat!=null) stat.close();
                if (con!=null)  con.close();
            } catch (Exception e) {e.printStackTrace();}

        }


    }
}