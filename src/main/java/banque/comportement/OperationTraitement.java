package banque.comportement;

import banque.modele.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OperationTraitement {

    Operation wrapped = new Operation();

    public LocalDateTime getDateOperation() {
        return wrapped.getDateOperation();
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        wrapped.setDateOperation(dateOperation);
    }

    public double getMontant() {
        return wrapped.getMontant();
    }

    public void setMontant(double montant) {
        wrapped.setMontant(montant);
    }

    public Devise getDevise() {
        return wrapped.getDevise();
    }

    public void setDevise(Devise devise) {
        wrapped.setDevise(devise);
    }

    public Compte getCompte() {
        return wrapped.getCompte();
    }

    public void setCompte(Compte comptes) {
        wrapped.setCompte(comptes);
    }


    public Client getClient() {
        return wrapped.getClient();
    }

    public void setClient(Client client) {
        wrapped.setClient(client);
    }

    public TypeOperation getTypeOperation() {
        return wrapped.getTypeOperation();
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        wrapped.setTypeOperation(typeOperation);
    }

    public abstract void apply();

    public List<Operation> getOperations()
    {   
        List<Operation> operations = new ArrayList<>();
       operations.add(wrapped);
        return operations;
    }

    protected void dateOperationSurDateCourante()
    {
         this.setDateOperation(LocalDateTime.now());
    }
}
