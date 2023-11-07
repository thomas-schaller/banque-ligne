package banque;

import banque.modele.Operation;

import banque.modele.Compte;

import banque.services.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

    @GetMapping("/{idClient:[0-9]+}/idCompte:[0-9]+}/operations")
    @ResponseBody
    public List<Operation> listerOperationsCompte(@PathVariable long idClient,@PathVariable long idCompte)
    {
        return null;
    }

    @PostMapping("/{idClient:[0-9]+}/{idCompte:[0-9]+}")
    @ResponseBody
    public Operation appliquerOperation( @PathVariable long idClient,@PathVariable long idCompte,@RequestBody Operation operation) {

        return null;
    }


    private static void ajoutCompte2Modele(List<Compte> comptes,Model model)
    {
        model.addAttribute("comptes", comptes);
    }
}
