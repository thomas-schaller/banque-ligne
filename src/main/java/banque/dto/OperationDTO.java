package banque.dto;

import banque.modele.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OperationDTO {
    private LocalDateTime dateOperation;
    private double montant;

    private long identifiant;

    private Devise devise;


    private Client client;

    private Compte compte;

    private TypeOperation typeOperation;

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

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public abstract void apply();

    public List<Operation> getOperations()
    {   
        List<Operation> operations = new ArrayList<>();
        Operation operation = new Operation();
        operation.setMontant(this.getMontant());
        operation.setDevise(this.getCompte().getDevise());
        operation.setClient(this.getClient());
        operation.setCompte(this.getCompte());
        operation.setDateOperation(this.getDateOperation());
        operations.add(operation);
        return operations;
    }

    protected void dateOperationSurDateCourante()
    {
         this.seDateOperation(LocalDateTime.now());
    }
}
