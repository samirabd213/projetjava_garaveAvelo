package velocomposants;

import velovisiteur.ElementVisitable;
import velovisiteur.VisiteurElement;

/**
 * La classe Batterie représente une batterie avec une puissance spécifiée et une marque.
 * Elle hérite de la classe Marque et implémente l'interface ElementVisitable.

 * @author Abdelkadous_Samir
 */
public class Batterie extends Marque implements ElementVisitable {
    
  /**
     * La puissance de la batterie.
     */
  private int puissance;
    
  /**
     * Constructeur par défaut nécessaire pour la désérialisation avec Jackson.
     * Initialise la classe parente avec une marque par défaut.
     */
  public Batterie() {
    super(""); // Appel au constructeur de la classe parente avec une valeur par défaut
  }

  /**
     * Constructeur de la classe Batterie.

     * @param puissance La puissance de la batterie.
     * @param marque    La marque de la batterie.
     */
  public Batterie(int puissance, String marque) {
    super(marque);
    this.puissance = puissance;
  }
    
  /**
     * Obtient la puissance de la batterie.
     *
     * @return La puissance de la batterie.
     */
  public int getPuissance() {
    return this.puissance;
  }

  /**
     * Méthode d'acceptation pour le visiteur.
     * Appelle la méthode correspondante du visiteur pour la Batterie.
     *
     * @param visiteur Le visiteur qui va traiter la Batterie.
     */
  @Override
  public void accepter(VisiteurElement visiteur) {
    visiteur.visiter(this);
  }
}

