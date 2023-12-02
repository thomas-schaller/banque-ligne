package banque.comportement;

import banque.modele.TypeOperation;

public class DepotTraitement extends OperationTraitement {
    public DepotTraitement() {
        this.setTypeOperation(TypeOperation.depot);
    }

    @Override
    public void apply() {
        getCompte().setSolde(getCompte().getSolde()+this.getMontant());
    }

}
