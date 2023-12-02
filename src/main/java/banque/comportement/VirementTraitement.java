package banque.comportement;

import banque.modele.Operation;
import banque.modele.TypeOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * Un virement correspond à une operation de retrait d'un compte puis d'une operation de depot sur un autre compte
 */
public class VirementTraitement extends OperationTraitement {
    public VirementTraitement() {
        setTypeOperation(TypeOperation.virement);
    }
    DepotTraitement depot;
    RetraitTraitement retrait;

    public DepotTraitement getDepot() {
        return depot;
    }

    public void setDepot(DepotTraitement depot) {
        this.depot = depot;
    }

    public RetraitTraitement getRetrait() {
        return retrait;
    }

    public void setRetrait(RetraitTraitement retrait) {
        this.retrait = retrait;
    }

    public void apply() {
        retrait.apply();
        depot.apply();
    }

    @Override
    public List<Operation> getOperations() {
        List<Operation> operations =  new ArrayList<>(retrait.getOperations());
        operations.addAll(depot.getOperations());
        return operations;
    }
}
