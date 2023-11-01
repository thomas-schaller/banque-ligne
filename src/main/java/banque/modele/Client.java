package banque.modele;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id @GeneratedValue
    private long identifiant;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "proprietaire") @OrderColumn(name = "identifiant")
    private List<Compte> possede;

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

    public List<Compte> getPossede() {
        return possede;
    }

    public void setPossede(List<Compte> possede) {
        this.possede = possede;
    }






}
