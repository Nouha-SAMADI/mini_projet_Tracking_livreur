package ma.fstt.model;

public class Produit {
    private Long id_produit ;

    private String nom ;

    private String description ;
    private double prix;
    private int quantite;

    public Produit() {
    }

    public Produit(Long id_produit, String nom, String description, int quantite, double prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Produit(Long productId, String name) {
        this.id_produit = productId;
        this.nom =name;
    }


    public Long getId_produit() {
        return id_produit;
    }

    public void setId_produit(Long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom='" + nom + '\'' +

                '}';
    }
}


                /*", description='" + description + '\'' +
                        ", quantité='" + quantite + '\'' +
                        ", prix='" + prix + '\'' +*/