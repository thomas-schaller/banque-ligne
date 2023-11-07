package banque.dto;

import banque.modele.Compte;
import banque.modele.TypeOperation;

import java.time.LocalDateTime;

public class DepotDTO extends OperationDTO{
    public DepotDTO() {
        this.setTypeOperation(TypeOperation.depot);
    }

    @Override
    public void apply() {
        setDateOperation(LocalDateTime.now());
        Compte compte = getCompte();
        compte.getOperations().addAll(getOperations());
        compte.setSolde(compte.getSolde()+this.getMontant());
    }

}
