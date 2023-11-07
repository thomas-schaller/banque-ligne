package banque;

import banque.dto.OperationDTO;
import banque.modele.Operation;

import banque.modele.Compte;

import banque.services.BanqueService;
import banque.services.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class BanqueController {

private final IBanqueService banqueService;
private final static String nomTemplateListeCompte= "listeComptes";
@Autowired
    public BanqueController(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    @GetMapping("/comptes/{idClient:[0-9]+}")
    public String listerCompte(Model model, @PathVariable long idClient) {
        List<Compte> comptesClient = banqueService.listerCompte(idClient);
        ajoutCompte2Modele(comptesClient,model);
        return nomTemplateListeCompte;
    }

    @GetMapping("/operations/{idClient:[0-9]+}/{idCompte:[0-9]+}")
    @ResponseBody
    public List<Operation> listerOperationsCompte(@PathVariable long idClient,@PathVariable long idCompte)
    {
        return banqueService.ListerOperationParCompte(idClient, idCompte);
    }

    @PostMapping("/operation/{idClient:[0-9]+}/{idCompte:[0-9]+}")
    @ResponseBody
    public void appliquerOperation( @PathVariable long idClient,@PathVariable long idCompte,@RequestBody OperationDTO operation) {

        banqueService.applyOperation(idClient,idCompte,operation);
    }


    private static void ajoutCompte2Modele(List<Compte> comptes,Model model)
    {
        model.addAttribute("comptes", comptes);
    }
}
