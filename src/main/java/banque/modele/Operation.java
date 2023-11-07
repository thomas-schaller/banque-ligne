package banque.modele;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Operation {

    private LocalDateTime dateOperation;
    private double montant;
    @Id @GeneratedValue
    private long identifiant;
    @ManyToOne
    private Devise devise;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Compte compte;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    public Operation(double montant, Devise devise) {
        this.montant = montant;
        this.devise = devise;
    }

    public Operation(){
        this.devise = new Devise();
    }
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

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte comptes) {
        this.compte = comptes;
    }

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

    protected void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

}
