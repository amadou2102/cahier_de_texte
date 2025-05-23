package models;

public class Responsable {
    private int id;
    private String nom;
    private String prenom;
    private String email;

    public Responsable(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return nom + " " + prenom; // ou juste nom
    }

}
