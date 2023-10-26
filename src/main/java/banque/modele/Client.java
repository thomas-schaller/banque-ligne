package banque.modele;

import javax.persistence.*;

@Entity
public class Client {
    String nom;
    String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public Compte[] getPossede() {
        return possede;
    }

    public void setPossede(Compte[] possede) {
        this.possede = possede;
    }

    public Compte[] getaDestinataireAutorise() {
        return aDestinataireAutorise;
    }

    public void setaDestinataireAutorise(Compte[] aDestinataireAutorise) {
        this.aDestinataireAutorise = aDestinataireAutorise;
    }

    @Id @GeneratedValue
    long identifiant;
    @OneToMany @OrderColumn(name = "identifiant")
    Compte[] possede;
    @ManyToMany @OrderColumn(name = "identifiant")
    Compte[] aDestinataireAutorise;

}
