package models;

public class Cours {
    private int idCours;
    private String nomCours;

    public Cours(int idCours, String nomCours) {
        this.idCours = idCours;
        this.nomCours = nomCours;
    }

    public int getIdCours() {
        return idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    @Override
    public String toString() { //La méthode toString() est très utile car c’est ce que JComboBox affichera.
        return nomCours;
    }


}
