package banque.modele;

import javax.persistence.*;

@Entity
public class Client {
    String nom;
    String prenom;
    @Id @GeneratedValue
    long identifiant;
    @OneToMany
    Compte[] possede;
    @ManyToMany
    Compte[] aDestinataireAutorise;

}
