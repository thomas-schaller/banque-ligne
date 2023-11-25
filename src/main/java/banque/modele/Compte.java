package banque.modele;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Compte {
    private double solde;
    private double minimumAutorise;


    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;
    @ManyToOne
    private Devise devise;

    @OneToMany(mappedBy = "compte") @OrderColumn(name = "identifiant")
    private List<Operation> operations;

    @ManyToOne()
    private Client proprietaire;

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public double getMinimumAutorise() {
        return minimumAutorise;
    }

    public void setMinimumAutorise(double minimumAutorise) {
        this.minimumAutorise = minimumAutorise;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "solde=" + solde +
                ", minimumAutorise=" + minimumAutorise +
                ", identifiant=" + identifiant +
                ", devise=" + devise +
                ", proprietaire=" + proprietaire +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Double.compare(getSolde(), compte.getSolde()) == 0 && Double.compare(getMinimumAutorise(), compte.getMinimumAutorise()) == 0 && Objects.equals(getDevise(), compte.getDevise()) && Objects.equals(getProprietaire(), compte.getProprietaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSolde(), getMinimumAutorise(), getDevise(), getProprietaire());
    }
}
