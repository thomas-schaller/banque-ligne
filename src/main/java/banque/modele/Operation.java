package banque.modele;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class Operation {

    @Column(nullable = false)
    @Basic
    private LocalDateTime dateOperation;
    private double montant;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long identifiant;
    @ManyToOne
    private Devise devise;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Compte compte;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    public Operation(double montant, Devise devise) {
        this.montant = montant;
        this.devise = devise;
    }

    public Operation(){

    }
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

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte comptes) {
        this.compte = comptes;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Double.compare(getMontant(), operation.getMontant()) == 0 && Objects.equals(getDateOperation(), operation.getDateOperation()) && Objects.equals(getDevise(), operation.getDevise()) && Objects.equals(getClient(), operation.getClient()) && Objects.equals(getCompte(), operation.getCompte()) && getTypeOperation() == operation.getTypeOperation();
    }

    @Override
    public String toString() {
        return "Operation{" +
                "dateOperation=" + dateOperation +
                ", montant=" + montant +
                ", identifiant=" + identifiant +
                ", devise=" + devise +
                ", client=" + client +
                ", compte=" + compte +
                ", typeOperation=" + typeOperation +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateOperation(), getMontant(), getDevise(), getClient(), getCompte(), getTypeOperation());
    }
}
