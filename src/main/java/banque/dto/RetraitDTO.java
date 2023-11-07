package banque.dto;

import banque.modele.Compte;
import banque.modele.TypeOperation;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

public class RetraitDTO extends OperationDTO {

    public RetraitDTO() {
        setTypeOperation(TypeOperation.retrait);
    }

    @Override
    public void apply() {
        Compte compte = this.getCompte();
        setDateOperation(LocalDateTime.now());
        if (compte.getSolde()-getMontant() < compte.getMinimumAutorise())
        {
            compte.setSolde(compte.getSolde() - getMontant());
            compte.getOperations().addAll(getOperations());
        }
        else {
            throw new InvalidParameterException("Depassement du seuil minimum Autorisé :"+compte.getMinimumAutorise());
        }
    }
}
