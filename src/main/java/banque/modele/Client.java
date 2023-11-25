package banque.modele;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
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

    @Override
    public String toString() {
        return "Client{" +
                "identifiant=" + identifiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getNom(), client.getNom()) && Objects.equals(getPrenom(), client.getPrenom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom());
    }
}
