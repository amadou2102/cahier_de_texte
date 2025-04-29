package models;

public class Seance {
        private int idSeance;
        private String contenueSeance;
        private String dureeSeance;
        private String dateSeance;
        private int idCours;
        private String etat; // ✅ État de validation
        private String nomCours;
        private String role;
        private String email;
        private int idProfesseur;

                public Seance() {
        }

        // Getters et Setters
        public int getIdSeance() { return idSeance; }
        public void setIdSeance(int id) { this.idSeance = id; }

        public String getContenueSeance() { return contenueSeance; }
        public void setContenueSeance(String c) { this.contenueSeance = c; }

        public String getDureeSeance() { return dureeSeance; }
        public void setDureeSeance(String d) { this.dureeSeance = d; }

        public String getDateSeance() { return dateSeance; }
        public void setDateSeance(String d) { this.dateSeance = d; }

        public int getIdCours() { return idCours; }
        public void setIdCours(int idCours) { this.idCours = idCours; }

        public String getEtat() { return etat; }
        public void setEtat(String etat) { this.etat = etat; }


        public String getNomCours() { return nomCours; }
        public void setNomCours(String nomCours) { this.nomCours = nomCours; }
}
