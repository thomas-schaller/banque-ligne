package banque.modele;

import java.time.LocalDateTime;

public class Depot extends Operation{
    public Depot()
    {
        setTypeOperation(TypeOperation.depot);
    }

    @Override
    public void apply() {
        setDateOperation(LocalDateTime.now());
        Compte compte = getCompte();
        compte.getOperations().add(this);
        compte.setSolde(compte.getSolde()+this.getMontant());
    }

}
