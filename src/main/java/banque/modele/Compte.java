package banque.modele;

import javax.persistence.*;

@Entity
public class Compte {
    double solde;
    double minimumAutorise;
    @Id @GeneratedValue
    long identifiant;
    @ManyToOne
    Devise devise;
    @ManyToMany(mappedBy = "appliqueSur")
    Operation operations;
}
