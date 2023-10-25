package banque.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TypeOperation {
    @Id @GeneratedValue
    long identifiant;
    String nomTypeOperation;
}
