package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit>{
    public ProduitDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Produit object) throws SQLException {

        String request = "insert into produit (nom , description, quantite, prix) values (? , ?, ?, ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getDescription());
        this.preparedStatement.setInt(3 , object.getQuantite());
        this.preparedStatement.setDouble(4 , object.getPrix());
        this.preparedStatement.execute();




    }

    @Override
    public void update(Produit object) throws SQLException {


        String request = "update produit set nom=?, description=?, quantite=?, prix=? where id_produit=?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.setString(2 , object.getDescription());
        this.preparedStatement.setInt(3 , object.getQuantite());
        this.preparedStatement.setDouble(4 , object.getPrix());

        this.preparedStatement.setLong(5, object.getId_produit());
        this.preparedStatement.executeUpdate();



    }

    @Override
    public void delete(Produit object) throws SQLException {
        String request = "delete from produit where id_produit=?";
        this.preparedStatement = this.connection.prepareStatement(request);
        this.preparedStatement.setLong(1, object.getId_produit());
        this.preparedStatement.executeUpdate();


    }

    @Override
    public List<Produit> getAll()  throws SQLException {

        List<Produit> mylist = new ArrayList<Produit>();

        String request = "select * from produit ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Produit(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) , this.resultSet.getString(3),this.resultSet.getInt(4) ,this.resultSet.getDouble(5) ));


        }


        return mylist;
    }

    @Override
    public Produit getOne(Long id) throws SQLException {

        return null;
    }
}
