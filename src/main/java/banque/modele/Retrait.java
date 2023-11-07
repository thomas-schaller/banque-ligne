package banque.modele;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Retrait extends Operation{

    public Retrait()
    {
        setTypeOperation(TypeOperation.retrait);
    }


    @Override
    public void apply() {
        Compte compte = this.getCompte();
        setDateOperation(LocalDateTime.now());
        if (compte.getSolde()-getMontant() < compte.getMinimumAutorise())
        {
            compte.setSolde(compte.getSolde() - getMontant());
            compte.getOperations().add(this);
        }
        else {
            throw new InvalidParameterException("Depassement du seuil minimum Autorisé :"+compte.getMinimumAutorise());
        }
    }
}
