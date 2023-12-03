package velovisiteur;

import velocomposants.Batterie;
import velocomposants.Pneu;
import velofacade.Velo;

/**
 * La classe Visiteur1 est une implémentation concrète de l'interface VisiteurElement. Elle fournit
 * une manière spécifique d'afficher les caractéristiques des éléments du système.
 *
 *
 * @author Abdelkadous_Samir
 */
class Visiteur1 implements VisiteurElement {

  @Override
  public void visiter(Velo velo) {
    // Afficher les caractéristiques spécifiques du Velo
    System.out.println("Numero de serie de velo : " + velo.getNumeroSerie());
  }

  @Override
  public void visiter(Batterie batterie) {
    // Afficher les caractéristiques spécifiques de la Batterie
    System.out.println("Capacite de la batterie : " + batterie.getPuissance());
  }

  @Override

  public void visiter(Pneu pneu) {
    // Afficher les caractéristiques spécifiques du Pneu
    System.out.println("largeur de pneu : " + pneu.getLargeur());
  }
}

