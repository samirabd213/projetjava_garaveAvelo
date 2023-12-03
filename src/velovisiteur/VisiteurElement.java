package velovisiteur;

import velocomposants.Batterie;
import velocomposants.Pneu;
import velofacade.Velo;

/**
 * L'interface VisiteurElement définit des méthodes pour visiter différents types d'éléments du
 * système. Chaque méthode correspond à un type d'élément spécifique et permet au visiteur
 * d'effectuer des actions spécifiques sur cet élément.
 * Exemple d'utilisation :
 * VisiteurElement visiteur = new MonVisiteur(); // Créez un objet VisiteurElement personnalisé Velo
 * veloA = ... // Créez un objet Velo Batterie batterieA = ... // Créez un objet Batterie Pneu pneuA
 * = ... // Créez un objet Pneu
 * visiteur.visiter(veloA); // Appel de la méthode visiter pour traiter le vélo
 * visiteur.visiter(batterieA); // Appel de la méthode visiter pour traiter la batterie
 * visiteur.visiter(pneuA); // Appel de la méthode visiter pour traiter le pneu

 * @author Abdelkadous_Samir
 */
public interface VisiteurElement {

  /**
   * Méthode pour visiter un objet de type Velo.

   * @param velo L'objet Velo à visiter.
   */
  void visiter(Velo velo);

  /**
   * Méthode pour visiter un objet de type Batterie.
   *
   * @param batterie L'objet Batterie à visiter.
   */
  void visiter(Batterie batterie);

  /**
   * Méthode pour visiter un objet de type Pneu.
   *
   * @param pneu L'objet Pneu à visiter.
   */
  void visiter(Pneu pneu);
}

