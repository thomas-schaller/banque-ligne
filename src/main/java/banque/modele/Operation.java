package banque.modele;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Operation {
    LocalDateTime dateOperation;
    double montant;
    @Id @GeneratedValue
    long identifiant;
    @ManyToOne
    Devise devise;
    @OneToMany
    Client aeffectue;
    @ManyToMany
    Compte[] appliqueSur;
}
