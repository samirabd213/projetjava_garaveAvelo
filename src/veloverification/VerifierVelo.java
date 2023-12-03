package veloverification;

import velocomposants.Pneu;
import velofacade.Velo;

/**
 * La classe VerifierVelo fournit des méthodes pour vérifier la validité d'un objet Velo. Elle
 * contient une méthode isVeloValid qui effectue différentes vérifications sur les propriétés du
 * Velo. Elle utilise des méthodes privées pour vérifier des critères spécifiques.
 * Exemple d'utilisation :
 * Velo veloA = ... // Créez un objet Velo if (VerifierVelo.isVeloValid(veloA)) {
 * System.out.println("Le vélo est valide."); } else { System.out.println("Le vélo n'est pas
 * valide."); }
 *
 * @author Abdelkadous_Samir
 */
public class VerifierVelo {

  /**
   * Vérifie la validité d'un Velo en effectuant différentes vérifications sur ses propriétés.
   * Affiche des messages d'erreur si le Velo n'est pas valide.
   *
   * @param velo L'objet Velo à vérifier.
   * @return true si le Velo est valide, false sinon.
   */
  public static boolean isVeloValid(Velo velo) {
    Pneu[] lesPneus = velo.getLesPneus();

    // Vérifier que les pneus ont la même marque et la même largeur
    if (!pneusMemeMarqueEtLargeur(lesPneus[0], lesPneus[1])) {
      return false;
    }

    // Vérifier que la largeur des pneus est dans l'intervalle spécifié
    int largeurPneu = lesPneus[0].getLargeur();
    if (largeurPneu < 19 || largeurPneu > 30) {
      return false;
    }

    // Vérifier que les pneus ont le même type
    if (!pneusMemeType(lesPneus[0], lesPneus[1])) {
      return false;
    }

    // Le Velo est valide si toutes les vérifications sont passées
    return true;
  }

  /**
   * Vérifie si deux pneus ont la même marque et la même largeur.
   *
   * @param pneu1 Le premier pneu.
   * @param pneu2 Le deuxième pneu.
   * @return true si les pneus ont la même marque et la même largeur, false sinon.
   */
  private static boolean pneusMemeMarqueEtLargeur(Pneu pneu1, Pneu pneu2) {
    return pneu1.getMarque().equals(pneu2.getMarque()) && pneu1.getLargeur() == pneu2.getLargeur();
  }

  /**
   * Vérifie si deux pneus ont le même type.
   *
   * @param pneu1 Le premier pneu.
   * @param pneu2 Le deuxième pneu.
   * @return true si les pneus ont le même type, false sinon.
   */
  private static boolean pneusMemeType(Pneu pneu1, Pneu pneu2) {
    return pneu1.getContientChambre() == pneu2.getContientChambre();
  }
}

