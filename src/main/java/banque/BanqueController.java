package banque;

import banque.comportement.OperationTraitement;
import banque.modele.Operation;

import banque.modele.Compte;

import banque.services.ICompteService;
import banque.services.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class BanqueController {

private final IOperationService operationService;

private final ICompteService compteService;
private final static String nomTemplateListeCompte= "listeComptes";
@Autowired
    public BanqueController(ICompteService compteService, IOperationService operationService) {
        this.compteService = compteService;
        this.operationService = operationService;
    }

    @GetMapping("/comptes/{idClient:[0-9]+}")
    public String listerCompte(Model model, @PathVariable long idClient) {
        List<Compte> comptesClient = compteService.listerCompte(idClient);
        ajoutCompte2Modele(comptesClient,model);
        return nomTemplateListeCompte;
    }

    @GetMapping("/operations/{idClient:[0-9]+}/{idCompte:[0-9]+}")
    @ResponseBody
    public List<Operation> listerOperationsCompte(@PathVariable long idClient,@PathVariable long idCompte)
    {
        return operationService.ListerOperationParCompte(idClient, idCompte);
    }

    @PostMapping("/operation/{idClient:[0-9]+}/{idCompte:[0-9]+}")
    @ResponseBody
    public void appliquerOperation( @PathVariable long idClient,@PathVariable long idCompte,@RequestBody OperationTraitement operation) {

        operationService.applyOperation(idClient,idCompte,operation);
    }


    private void ajoutCompte2Modele(List<Compte> comptes,Model model)
    {
        model.addAttribute("comptes", comptes);
    }
}
