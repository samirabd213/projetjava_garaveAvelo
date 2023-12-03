package velofacade;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Garageavelotest {
  private Garageavelo garage;

  @BeforeEach
  void setUp() {
    garage = new Garageavelo();
  }

  @Test
  void testChargerVelosDepuisFichierJson() {
    // À compléter avec un chemin de fichier JSON valide
    String cheminFichierJson = "C:\\Users\\hp\\Documents\\fichier_Json\\velos.json";

    try {
      garage.chargerVelosDepuisFichierjson(cheminFichierJson);
      // Vérifiez ici si les vélos ont été correctement chargés depuis le fichier JSON
      Assertions.assertNotNull(garage.getVelos());
      Assertions.assertTrue(garage.getVelos().size() > 0);
    } catch (IOException e) {
      Assertions.fail("Erreur lors du chargement du fichier JSON");
    }
  }

  @Test
  void testAjouterVelo() {
    // À compléter avec les paramètres de votre vélo
    String modele = "ModèleTest";
    String marque = "MarqueTest";
    int puissanceBatterie = 100;
    String marqueBatterie = "MarqueBatterieTest";
    String pneuMarque = "PneuMarqueTest";
    int pneuLargeur = 30;
    boolean contientChambre = true;

    garage.ajouterVelo(modele, marque, puissanceBatterie, marqueBatterie, pneuMarque, pneuLargeur,
        contientChambre);

    // Vérifiez ici si le vélo a été correctement ajouté
    Assertions.assertTrue(garage.getVelos().size() > 0);
  }

  @Test
  void testChangerBatterie() {
    // Add a test bicycle with initial battery details
    garage.ajouterVelo("ModèleTest", "MarqueTest", 100, "MarqueBatterieTest", "PneuMarqueTest", 30,
        true);

    // Get the initial battery details
    int numeroSerie = garage.getVelos().get(0).getNumeroSerie();
    int initialPuissance = garage.getVelos().get(0).getLabatterie().getPuissance();
    String initialMarque = garage.getVelos().get(0).getLabatterie().getMarque();

    // Change the battery
    int nouvellePuissance = 120;
    String nouvelleMarque = "NouvelleMarqueBatterie";
    garage.changerBatterie(numeroSerie, nouvellePuissance, nouvelleMarque);
    Assertions.assertNotEquals(initialPuissance,
        garage.getVelos().get(0).getLabatterie().getPuissance());
    Assertions.assertNotEquals(initialMarque, garage.getVelos().get(0).getLabatterie().getMarque());
    // Assertions
    Assertions.assertEquals(nouvellePuissance,
        garage.getVelos().get(0).getLabatterie().getPuissance());
    Assertions.assertEquals(nouvelleMarque, garage.getVelos().get(0).getLabatterie().getMarque());
  }

  @Test
  void testChangerPneu() {
    // Add a test bicycle with initial tire details
    garage.ajouterVelo("ModèleTest", "MarqueTest", 100, "MarqueBatterieTest", "PneuMarqueTest", 30,
        true);

    // Get the initial tire details
    int numeroSerie = garage.getVelos().get(0).getNumeroSerie();
    String initialMarque = garage.getVelos().get(0).getLesPneus()[0].getMarque();
    int initialLargeur = garage.getVelos().get(0).getLesPneus()[0].getLargeur();

    // Change the tires
    String nouvelleMarque = "NouvelleMarquePneu";
    int nouvelleLargeur = 35;
    garage.changerPneu(numeroSerie, nouvelleMarque, nouvelleLargeur, false);

    // Assertions
    Assertions.assertNotEquals(initialMarque,
        garage.getVelos().get(0).getLesPneus()[0].getMarque());
    Assertions.assertNotEquals(initialLargeur,
        garage.getVelos().get(0).getLesPneus()[0].getLargeur());
    Assertions.assertEquals(nouvelleMarque, garage.getVelos().get(0).getLesPneus()[0].getMarque());
    Assertions.assertEquals(nouvelleLargeur,
        garage.getVelos().get(0).getLesPneus()[0].getLargeur());
  }

  @Test
  void testChangerPneuAvant() {
    // Add a test bicycle with initial front tire details
    garage.ajouterVelo("ModèleTest", "MarqueTest", 100, "MarqueBatterieTest", "PneuMarqueTest", 30,
        true);

    // Get the initial front tire details
    int numeroSerie = garage.getVelos().get(0).getNumeroSerie();
    String initialMarque = garage.getVelos().get(0).getLesPneus()[1].getMarque();
    int initialLargeur = garage.getVelos().get(0).getLesPneus()[1].getLargeur();

    // Change the front tire
    String nouvelleMarque = "NouvelleMarquePneuAvant";
    int nouvelleLargeur = 40;
    garage.changerPneuAvant(numeroSerie, nouvelleMarque, nouvelleLargeur, true);

    // Assertions
    Assertions.assertNotEquals(initialMarque,
        garage.getVelos().get(0).getLesPneus()[1].getMarque());
    Assertions.assertNotEquals(initialLargeur,
        garage.getVelos().get(0).getLesPneus()[1].getLargeur());
    Assertions.assertEquals(nouvelleMarque, garage.getVelos().get(0).getLesPneus()[1].getMarque());
    Assertions.assertEquals(nouvelleLargeur,
        garage.getVelos().get(0).getLesPneus()[1].getLargeur());
  }
    

  @Test
  void testChangerPneuArriere() {
    // Add a test bicycle with initial rear tire details
    garage.ajouterVelo("ModèleTest", "MarqueTest", 100, "MarqueBatterieTest", "PneuMarqueTest", 30,
        true);

    // Get the initial rear tire details
    int numeroSerie = garage.getVelos().get(0).getNumeroSerie();
    String initialMarque = garage.getVelos().get(0).getLesPneus()[0].getMarque();
    int initialLargeur = garage.getVelos().get(0).getLesPneus()[0].getLargeur();

    // Change the rear tire
    String nouvelleMarque = "NouvelleMarquePneuArriere";
    int nouvelleLargeur = 45;
    garage.changerPneuArriere(numeroSerie, nouvelleMarque, nouvelleLargeur, false);
    Assertions.assertNotEquals(initialMarque,
            garage.getVelos().get(0).getLesPneus()[0].getMarque());
    Assertions.assertNotEquals(initialLargeur,
            garage.getVelos().get(0).getLesPneus()[0].getLargeur());
    // Assertions
    Assertions.assertEquals(nouvelleMarque, garage.getVelos().get(0).getLesPneus()[0].getMarque());
    Assertions.assertEquals(nouvelleLargeur,
        garage.getVelos().get(0).getLesPneus()[0].getLargeur());
  }
}

