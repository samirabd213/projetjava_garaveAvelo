package velocomposants;

/**
 * La classe Marque représente la marque d'un objet. Elle fournit un moyen de stocker et d'obtenir
 * la marque.
 *
 * @author Abdelkadous_Samir
 */
public class Marque {

  /**
   * La marque de l'objet.
   */
  protected String marque;

  /**
   * Constructeur de la classe Marque.
   *
   * @param marque La marque à attribuer à l'objet.
   */
  public Marque(String marque) {
    this.marque = marque;
  }

  /**
   * Obtient la marque de l'objet.
   *
   * @return La marque de l'objet.
   */
  public String getMarque() {
    return this.marque;
  }
}

