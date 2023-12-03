package velocomposants;

import velovisiteur.ElementVisitable;
import velovisiteur.VisiteurElement;

/**
 * La classe Pneu représente un pneu avec une largeur spécifiée, une marque (héritée de Marque), et
 * un indicateur indiquant s'il est tubeless ou s'il contient une chambre à air.
 * 
 * <p>
 * <strong>Remarque :</strong> Cette classe implémente l'interface {@link ElementVisitable} pour
 * permettre la visite par un visiteur.
 * </p>
 *
 * @author Abdelkadous_Samir
 */
public class Pneu extends Marque implements ElementVisitable {

  /**
   * La largeur du pneu.
   */
  private int largeur;

  /**
   * Un indicateur indiquant si le pneu est tubeless (true) ou s'il contient une chambre à air
   * (false).
   */
  private boolean contientChambre;

  /**
   * Constructeur par défaut nécessaire pour la désérialisation avec Jackson. Initialise la classe
   * parente avec une marque par défaut.
   */
  public Pneu() {
    super(""); // Appel au constructeur de la classe parente avec une valeur par défaut
  }

  /**
   * Constructeur de la classe Pneu.
   *
   * @param largeur La largeur du pneu.
   * @param marque La marque du pneu (héritée de la classe Marque).
   * @param estTubeless Un indicateur indiquant si le pneu est tubeless.
   */
  public Pneu(int largeur, String marque, boolean estTubeless) {
    super(marque);
    this.largeur = largeur;
    this.contientChambre = estTubeless;
  }

  /**
   * Obtient la largeur du pneu.
   *
   * @return La largeur du pneu.
   */
  public int getLargeur() {
    return this.largeur;
  }

  /**
   * Indique si le pneu contient une chambre à air (false) ou s'il est tubeless (true).
   *
   * @return true si le pneu est tubeless, false s'il contient une chambre à air.
   */
  public boolean getContientChambre() {
    return this.contientChambre;
  }

  /**
   * Accepte un visiteur pour la visite de l'élément.
   *
   * @param visiteur Le visiteur à accepter.
   */
  @Override
  public void accepter(VisiteurElement visiteur) {
    visiteur.visiter(this);
  }
}
