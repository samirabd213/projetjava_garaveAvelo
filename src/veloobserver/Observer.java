package veloobserver;

/**
 * Interface définissant un observateur dans le modèle de conception Observer. Un observateur est
 * notifié lorsqu'un changement d'état se produit chez le sujet auquel il est associé.
 */
public interface Observer {

  /**
   * Méthode appelée lorsque le sujet notifie un changement d'état. Les observateurs doivent
   * implémenter cette méthode pour réagir aux mises à jour du sujet.
   */
  void update();

}
