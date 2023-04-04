package ma.fstt.model;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class CommandeDAO extends BaseDAO<Commande>{


    public CommandeDAO() throws SQLException {
        super();
    }



    @Override
    public void save(Commande object) throws SQLException {
        String request = "INSERT INTO commande (date, adresse_livraison) VALUES (?, ?)";
        this.preparedStatement = this.connection.prepareStatement(request, this.statement.RETURN_GENERATED_KEYS);
        this.preparedStatement.setDate(1, Date.valueOf(object.getDate()));
        this.preparedStatement.setString(2, object.getAdresseLivraison());
        this.preparedStatement.executeUpdate();
        this.resultSet = this.preparedStatement.getGeneratedKeys();

        if (this.resultSet.next()) {
            Long orderId = this.resultSet.getLong(1);

            for (Produit produit : object.getProduits()) {
                String checkProduitRequest = "SELECT COUNT(*) FROM produit WHERE id_produit = ?";
                this.preparedStatement = this.connection.prepareStatement(checkProduitRequest);
                this.preparedStatement.setLong(1, produit.getId_produit());
                this.resultSet = this.preparedStatement.executeQuery();

                if (this.resultSet.next() && this.resultSet.getLong(1) > 0) {
                    String insertOrderProductQuery = "INSERT INTO commande_produits (id_commande, id_produit) VALUES (?, ?)";
                    this.preparedStatement = this.connection.prepareStatement(insertOrderProductQuery);
                    this.preparedStatement.setLong(1, orderId);
                    this.preparedStatement.setLong(2, produit.getId_produit());

                    this.preparedStatement.executeUpdate();
                }
            }
        }

    }

    @Override
    public void update(Commande object) throws SQLException {

    }

    @Override
    public void delete(Commande object) throws SQLException {

    }

    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> commandes = new ArrayList<>();

        String request = "SELECT * FROM commande";

        this.preparedStatement = this.connection.prepareStatement(request);

        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()) {
            Long commandeId = this.resultSet.getLong(1);
            LocalDate orderDate = this.resultSet.getDate(2).toLocalDate();
            String adr = this.resultSet.getString(3);
            List<Produit> products = new ArrayList<>();
            String produitRequest = "SELECT p.id_produit, p.nom " +
                    "FROM produit p " +
                    "JOIN commande_produits cp ON p.id_produit = cp.id_produit " +
                    "WHERE cp.id_commande = ?";
            this.preparedStatement = this.connection.prepareStatement(produitRequest);
            this.preparedStatement.setLong(1, commandeId);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                Long productId = this.resultSet.getLong(1);
                String name = this.resultSet.getString(2);
                products.add(new Produit(productId, name));
            }
            commandes.add(new Commande(commandeId, orderDate, adr, products));
        }



        return commandes;
    }



    @Override
    public Commande getOne(Long id) throws SQLException {
        return null;
    }
}
