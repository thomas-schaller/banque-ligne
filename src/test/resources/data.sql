INSERT INTO devise(identifiant,nom_devise) VALUES (1,'euro');

INSERT INTO client (identifiant,nom,prenom) VALUES (1,'SCHALLER','Thomas');

INSERT INTO compte (identifiant,devise_identifiant,solde,minimum_autorise,proprietaire_identifiant) VALUES (1,1,100,-100,1);
INSERT INTO compte (identifiant,devise_identifiant,solde,minimum_autorise,proprietaire_identifiant) VALUES (2,1,500,-240,1);

INSERT INTO operation (identifiant,devise_identifiant,montant,compte_identifiant,client_identifiant,type_operation,date_operation) VALUES (1,1,200,1,1,'depot','2023-11-17 15:32:01.12');

