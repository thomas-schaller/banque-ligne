package banque.repository;

import banque.modele.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise,Long> {
    Devise findByNomDevise(String nomDevise);
}
