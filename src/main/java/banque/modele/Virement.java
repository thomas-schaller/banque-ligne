package banque.modele;

public class Virement extends Operation{

    Retrait retrait;
    Depot depot;
    public Virement()
    {
        setTypeOperation(TypeOperation.virement);
    }

    @Override
    public void apply() {

    }
}
