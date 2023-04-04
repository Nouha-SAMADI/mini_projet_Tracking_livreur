package ma.fstt.model;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class Commande {
    private Long id_commande ;

    private LocalDate date ;

    private String adresse_livraison ;
    private List<Produit> produits = new ArrayList<>() ;



    public Commande() {
    }

    public Commande(Long id_commande,LocalDate date, String adrs_liv,List<Produit> produits) {
        this.id_commande = id_commande;
        this.date = date;
        this.adresse_livraison = adrs_liv;
        this.produits = produits;




    }


    public Long getId_commande() {
        return id_commande;
    }

    public void setId_commande(Long id_commande) {
        this.id_commande = id_commande;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdresseLivraison()              {
        return adresse_livraison;
    }

    public void setAdresseLivraison(String adr_liv) {
        this.adresse_livraison = adr_liv;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", date'" + date + '\'' +
                ", adresse_livraison='" + adresse_livraison + '\'' +


                '}';
    }
}


