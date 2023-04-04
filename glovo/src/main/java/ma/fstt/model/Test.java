package ma.fstt.model;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

// trait bloc try catch
        /*try {


            ProduitDAO ProduitDAO = new ProduitDAO();
              Produit prod1 = new Produit(0l , "burger" ,"chicken",1, 25.5);

            ProduitDAO.save(prod1);

            Produit prod2 = new Produit(0l , "tacos" ,"beef",2, 30.0);


             ProduitDAO.save(prod2);


            List<Produit> prodlist =  ProduitDAO.getAll();

            for (Produit prod :prodlist) {

                System.out.println(prod.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        /*try {


            LivreurDAO livreurDAO = new LivreurDAO();
            //  Livreur liv = new Livreur(0l , "liv3" , "200000000");

            //livreurDAO.save(liv);

            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


            // livreurDAO.save(liv2);


            List<Livreur> livlist =  livreurDAO.getAll();

            for (Livreur liv :livlist) {

                System.out.println(liv.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        try{
            CommandeDAO cmdDao =new CommandeDAO();
            ProduitDAO ProduitDAO = new ProduitDAO();
            Produit prod1 = new Produit(7l , "tacos" ,"beef",1, 25);

            ProduitDAO.save(prod1);

            Produit prod2 = new Produit(4l , "burger" ,"chicken",1, 60.5);


            ProduitDAO.save(prod2);
            List<Produit> pr = new ArrayList<>();
            pr.add(prod1);
            pr.add(prod2);

            Commande cmd = new Commande(0l, LocalDate.of(2023,2,20),"branes",pr);
            cmdDao.save(cmd);
            System.out.println(cmd.getProduits());

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
