package banque.modele;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compte {
    double solde;
    double minimumAutorise;

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    @ManyToOne()
    Client proprietaire;

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Compte && getIdentifiant() == ((Compte) obj).getIdentifiant();
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

    @Id @GeneratedValue
    long identifiant;
    @ManyToOne
    Devise devise;



    @ManyToMany(mappedBy = "comptes") @OrderColumn(name = "id")
    List<Operation> operations;
}
