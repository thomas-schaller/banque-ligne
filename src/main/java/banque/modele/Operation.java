package banque.modele;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToMany @OrderColumn(name = "identifiant")
    private List<Compte> comptes;

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

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
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

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}