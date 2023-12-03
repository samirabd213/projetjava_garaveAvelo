package velovisiteur;


/**
 * L'interface ElementVisitable définit la méthode accepter, qui permet à un visiteur de traiter
 * l'élément. Tout élément implémentant cette interface peut être visité par des objets implémentant
 * l'interface VisiteurElement.
 * Exemple d'utilisation :
 * Velo veloA = ... // Créez un objet Velo VisiteurElement visiteur = new MonVisiteur(); // Créez un
 * visiteur veloA.accepter(visiteur); // Appel de la méthode accepter pour visiter le vélo
 *
 * @author Abdelkadous_Samir
 */
public interface ElementVisitable {

  /**
   * Permet à un visiteur de traiter l'élément.
   *
   * @param visiteur L'objet VisiteurElement qui traitera l'élément.
   */
  void accepter(VisiteurElement visiteur);
}

