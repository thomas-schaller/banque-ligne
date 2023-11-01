package banque;

import banque.modele.Operation;
import banque.modele.TypeOperation;
import banque.repository.ClientRepository;
import banque.modele.Client;
import banque.modele.Compte;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
import banque.services.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BanqueController {

private final BanqueService banqueService;
private final static String nomTemplateListeCompte= "listeComptes";
@Autowired
    public BanqueController(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    @GetMapping("/{idClient:[0-9]+}")
    public String listerCompte(Model model, @PathVariable long idClient) {
        List<Compte> comptesClient = banqueService.listerCompte(idClient);
        ajoutCompte2Modele(comptesClient,model);
        return nomTemplateListeCompte;
    }

    @GetMapping("/{nomClient:.+}/{idCompte:[0-9]+}/depot/{montant:[0-9]+}")
    @ResponseBody
    public Operation depot(Model model, @PathVariable String nomClient,@PathVariable long idCompte,@PathVariable double montant) {

        return null;
    }

    @GetMapping("/{nomClient:.+}/{idCompte:[0-9]+}/retrait/{montant:[0-9]+}")
    @ResponseBody
    public Operation retrait(Model model, @PathVariable String nomClient,@PathVariable long idCompte,@PathVariable double montant) {

        return null;
    }


    private static void ajoutCompte2Modele(List<Compte> comptes,Model model)
    {
        model.addAttribute("comptes", comptes);
    }
}
