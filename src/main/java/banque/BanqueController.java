package banque;

import banque.modele.Operation;
import banque.modele.TypeOperation;
import banque.repository.ClientRepository;
import banque.modele.Client;
import banque.modele.Compte;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
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

    private final ClientRepository clientService;
    private final CompteRepository compteService;

    private final OperationRepository operationService;
@Autowired
    public BanqueController(ClientRepository clientService, CompteRepository compteService, OperationRepository operationService) {
        this.clientService = clientService;
        this.compteService = compteService;
        this.operationService= operationService;
    }

    @GetMapping("/{nomClient:.+}")
    public String listCompte(Model model, @PathVariable String nomClient) throws IOException {
        Client client = clientService.findByNom(nomClient);
        if (client != null) {
            model.addAttribute("comptes", client.getPossede());
        }
        else {
            model.addAttribute("comptes", new Array [0] );
        }

        return "listeComptes";
    }

    @GetMapping("/{nomClient:.+}/{idCompte:[0-9]+}/depot/{montant:[0-9]+}")
    @ResponseBody
    public Operation depot(Model model, @PathVariable String nomClient,@PathVariable long idCompte,@PathVariable double montant) throws IOException {
        Client client = clientService.findByNom(nomClient);
        Optional<Compte> compte = compteService.findById(idCompte);
        Operation operation = null;
        if (client != null && compte.isPresent() && Arrays.asList(client.getPossede()).contains(compte.get())) {
            Compte com= compte.get();
            List<Compte> listeCompte = new ArrayList<>();
            listeCompte.add(com);
            operation= new Operation(montant,compte.get().getDevise());
            operation.setTypeOperation(TypeOperation.depot);
            operation.setClient(client);
            operation.setDateOperation( LocalDateTime.now());
            //operation.setComptes(listeCompte);
            operationService.save(operation);
            List<Operation> listeOpe= com.getOperations();
            if (listeOpe == null)
            {
                listeOpe = new ArrayList<>();

            }
            listeOpe.add(operation);
            com.setOperations(listeOpe);
            com.setSolde(com.getSolde()+montant);
            compteService.save(com);

        }


        return operation;
    }
}
