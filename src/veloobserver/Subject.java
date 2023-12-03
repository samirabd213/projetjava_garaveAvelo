package veloobserver;

/**
 * Interface définissant le sujet (Subject) dans le modèle de conception Observer. Un sujet est
 * capable d'ajouter, supprimer et notifier des observateurs.
 */
public interface Subject {

  /**
   * Ajoute un observateur au sujet.
   *
   * @param observer L'observateur à ajouter.
   */
  void addObserver(Observer observer);

  /**
   * Supprime un observateur du sujet.
   *
   * @param observer L'observateur à supprimer.
   */
  void removeObserver(Observer observer);

  /**
   * Notifie tous les observateurs enregistrés lorsque l'état du sujet change.
   */
  void notifyObservers();
  
 

}
