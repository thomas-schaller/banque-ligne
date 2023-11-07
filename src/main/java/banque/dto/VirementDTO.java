package banque.dto;

import banque.modele.Operation;
import banque.modele.TypeOperation;

import java.util.ArrayList;
import java.util.List;

public class VirementDTO extends OperationDTO{
    public VirementDTO() {
        setTypeOperation(TypeOperation.virement);
    }
    DepotDTO depot;
    RetraitDTO retrait;

    public DepotDTO getDepot() {
        return depot;
    }

    public void setDepot(DepotDTO depot) {
        this.depot = depot;
    }

    public RetraitDTO getRetrait() {
        return retrait;
    }

    public void setRetrait(RetraitDTO retrait) {
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
