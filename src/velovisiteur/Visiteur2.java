package velovisiteur;

import velocomposants.Batterie;
import velocomposants.Pneu;
import velofacade.Velo;

/**
 * La classe Visiteur2 est une implémentation concrète de l'interface VisiteurElement. Elle fournit
 * une manière spécifique d'afficher la marque des éléments du système.
 * Exemple d'utilisation :
 * Visiteur2 visiteur2 = new Visiteur2(); // Créez un objet Visiteur2 Velo veloA = ... // Créez un
 * objet Velo Batterie batterieA = ... // Créez un objet Batterie Pneu pneuA = ... // Créez un objet
 * Pneu
 * visiteur2.visiter(veloA); // Appel de la méthode visiter pour afficher la marque du vélo
 * visiteur2.visiter(batterieA); // Appel de la méthode visiter pour afficher la marque de la
 * batterie visiteur2.visiter(pneuA); // Appel de la méthode visiter pour afficher la marque du pneu
 *
 * @author Abdelkadous_Samir
 */
public class Visiteur2 implements VisiteurElement {

  @Override
  public void visiter(Velo velo) {
    // Afficher les caractéristiques spécifiques du Velo
    System.out.println("Marque Velo : " + velo.getMarque());
    visiter(velo.getLabatterie());
  }

  @Override
  public void visiter(Batterie batterie) {
    // Afficher les caractéristiques spécifiques de la Batterie
    System.out.println("Marque Batterie : " + batterie.getMarque());
  }

  @Override
  public void visiter(Pneu pneu) {
    // Afficher les caractéristiques spécifiques du Pneu
    System.out.println("Marque des Pneus : " + pneu.getMarque());
  }
}
