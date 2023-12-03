package main;

import velofacade.Garageavelo;
import veloobserver.VueListeVelo;
import veloobserver.VueVelo;
import velovisiteur.Visiteur2;
import velovisiteur.VisiteurElement;

/**
 * Classe principale du programme.
 */

public class Main {

  /**
   * Méthode principale qui lance l'application.
   *
   * @param args Les arguments de la ligne de commande (non utilisés ici).
   */
  public static void main(String[] args) {

    // Crée un garage de vélos
    Garageavelo garageDeVelo = new Garageavelo();

    // Ajoute quelques vélos au garage
    garageDeVelo.ajouterVelo("VTT", "poiy", 500, "Batterie1", "Pneu1", 26, true);
    garageDeVelo.ajouterVelo("HPP", "woar", 800, "Batterie2", "Pneu2", 26, true);
    garageDeVelo.ajouterVelo("Twins", "piksso", 500, "Batterie3", "Pneu3", 26, true);

    // Crée une vue pour un vélo et une vue pour la liste des vélos, les liant au
    // garage
    VueVelo vueVelo = new VueVelo(garageDeVelo);
    VueListeVelo vueListeVelo = new VueListeVelo(garageDeVelo);
    VisiteurElement visiteur2 = new Visiteur2();
    //garageDeVelo.accepter(visiteur2); 
  }
}
