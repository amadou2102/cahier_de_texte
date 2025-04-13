package models;

public class Classe {
    private int idClasse;
    private String nomClasse;

    public Classe(int idClasse, String nomClasse) {
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    @Override
    public String toString() {
        return nomClasse; // Ce qui sera affiché dans le JComboBox
    }
}

