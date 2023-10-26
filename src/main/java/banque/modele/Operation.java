package banque.modele;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Operation {
    LocalDateTime dateOperation;
    double montant;


    public Operation(double montant, Devise devise) {
        this.montant = montant;
        this.devise = devise;
    }

    @Id @GeneratedValue
    long identifiant;
    @ManyToOne
    Devise devise;

    public long getIdentifiant() {
        return identifiant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @ManyToOne
    Client client;

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    @ManyToMany @OrderColumn(name = "identifiant")
    List<Compte> comptes;

    @Enumerated(EnumType.STRING)
    TypeOperation typeOperation;

}
