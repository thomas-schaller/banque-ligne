package banque.modele;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Devise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long identifiant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devise devise = (Devise) o;
        return Objects.equals(getNomDevise(), devise.getNomDevise());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomDevise());
    }

    @Column(nullable=false)
    private String nomDevise;
    public String getNomDevise() {
        return nomDevise;
    }

    public void setNomDevise(String nomDevise) {
        this.nomDevise = nomDevise;
    }

    @Override
    public String toString() {
        return "Devise{" +
                "identifiant=" + identifiant +
                ", nomDevise='" + nomDevise + '\'' +
                '}';
    }
}
