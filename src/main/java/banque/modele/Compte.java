package banque.modele;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Compte {
    private double solde;
    private double minimumAutorise;


    @Id @GeneratedValue
    private long identifiant;
    @ManyToOne
    private Devise devise;

    @ManyToMany(mappedBy = "comptes") @OrderColumn(name = "id")
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
    public boolean equals(Object obj) {
        return obj instanceof Compte && getIdentifiant() == ((Compte) obj).getIdentifiant();
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant);
    }


}
