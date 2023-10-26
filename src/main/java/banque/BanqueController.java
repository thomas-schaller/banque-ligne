package banque;

import banque.repository.ClientRepository;
import banque.modele.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.stream.Collectors;

@Controller
public class BanqueController {

    private final ClientRepository clientService;
@Autowired
    public BanqueController(ClientRepository clientService) {
        this.clientService = clientService;
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
}
