package banque.modele;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Devise {
    @Id @GeneratedValue
    long identifiant;
    private String nomDevise;
}
