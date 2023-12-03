package velofacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import velocomposants.Batterie;
import velocomposants.Pneu;
import velofactory.VeloFactory;
import velofactory.VeloListFactory;
import veloobserver.Observer;
import veloobserver.Subject;
import veloverification.VerifierVelo;
import velovisiteur.ElementVisitable;
import velovisiteur.VisiteurElement;



/**
 * La classe GarageAVelo est une façade pour la gestion d'un ensemble de Velo. Elle permet
 * d'effectuer des opérations CRUD (Create, Read, Update, Delete) sur les vélos et de vérifier la
 * validité d'un vélo. Elle implémente l'interface ElementVisitable pour être visitée par des objets
 * visiteurs. Elle implémente également l'interface Subject pour la gestion des observateurs.
 *
 * @author Abdelkadous_Samir
 */
public class Garageavelo implements ElementVisitable, Subject {
  private List<Velo> velos;
  private List<Observer> observers = new ArrayList<>();
  private int selectedNumeroSerie = -1;

  /**
   * Constructeur de la classe GarageAVelo.
   */
  public Garageavelo() {
    this.velos = new ArrayList<>();
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  /**
   * Charge la liste de Velo à partir d'un fichier JSON en utilisant la VeloListFactory.
   *
   * @param chemindefichier Le chemin du fichier JSON.
   * @throws IOException En cas d'erreur de lecture du fichier.
   */
  public void chargerVelosDepuisFichierjson(String chemindefichier) throws IOException {
    VeloListFactory veloListFactory = new VeloListFactory();
    this.velos.addAll(veloListFactory.createVeloListFromJson(chemindefichier));
    notifyObservers();
  }

  /**
   * Charge la liste de Velo à partir d'un fichier JSON en utilisant la VeloListFactory.
   *
   * @param chemindefichier Le chemin du fichier JSON.
   * @throws IOException En cas d'erreur de lecture du fichier.
   */
  public void chargerVelosDepuisFichierxml(String chemindefichier) throws IOException {
    VeloListFactory veloListFactory = new VeloListFactory();
    this.velos.addAll(veloListFactory.createVeloListFromXml(chemindefichier));
    notifyObservers();
  }

  /**
   * Ajoute un nouveau vélo au garage avec des paramètres spécifiques.
   *
   * @param modele Le modèle du vélo.
   * @param marque La marque du vélo.
   * @param puissanceBatterie La puissance de la batterie du vélo.
   * @param marqueBatterie La marque de la batterie du vélo.
   * @param pneuMarque La marque des pneus du vélo.
   * @param pneuLargeur La largeur des pneus du vélo.
   * @param contientChambre Indique si les pneus du vélo contiennent une chambre à air.
   */
  public void ajouterVelo(String modele, String marque, int puissanceBatterie,
      String marqueBatterie, String pneuMarque, int pneuLargeur, boolean contientChambre) {
    VeloFactory usine = new VeloFactory(marque);

    // Configuration de la batterie
    usine.configurerBatterie(puissanceBatterie, marqueBatterie);

    // Configuration des pneus
    usine.configurerPneus(pneuMarque, pneuLargeur, contientChambre);

    // Configuration du modèle (facultatif)
    usine.configurerModel(modele);

    Velo velo = usine.createVelo();
    this.velos.add(velo);
  }

  /**
   * Récupère tous les vélos du garage.
   *
   * @return La liste de tous les vélos dans le garage.
   */
  public List<Velo> getVelos() {
    return this.velos;
  }

  /**
   * Supprime un vélo du garage.
   *
   * @param numeroSerie de vélo à supprimer.
   */
  public void supprimerVelo(int numeroSerie) {
    for (Velo velo : this.velos) {
      if (velo.getNumeroSerie() == numeroSerie) {
        this.velos.remove(velo);
        break;
      }
    }
  }

  /**
   * Change la batterie d'un vélo spécifié par son numéro de série.
   *
   * @param numeroSerie Le numéro de série du vélo dont la batterie doit être changée.
   * @param puissanceNouvelleBatterie La nouvelle puissance de la batterie.
   * @param marqueNouvelleBatterie La nouvelle marque de la batterie.
   */
  public void changerBatterie(int numeroSerie, int puissanceNouvelleBatterie,
      String marqueNouvelleBatterie) {
    for (Velo velo : this.velos) {
      if (velo.getNumeroSerie() == numeroSerie) {
        Batterie nouvelleBatterie = new Batterie(puissanceNouvelleBatterie, marqueNouvelleBatterie);
        velo.setBatterie(nouvelleBatterie);
        break;
      }
    }
    
  }

  /**
   * Change les pneus d'un vélo spécifié par son numéro de série.
   *
   * @param numeroSerie Le numéro de série du vélo dont les pneus doivent être changés.
   * @param nouvelleMarque La nouvelle marque des pneus.
   * @param nouvelleLargeur La nouvelle largeur des pneus.
   * @param contientChambre Indique si les nouveaux pneus contiennent une chambre à air.
   */
  public void changerPneu(int numeroSerie, String nouvelleMarque, int nouvelleLargeur,
      boolean contientChambre) {
    for (Velo velo : this.velos) {
      if (velo.getNumeroSerie() == numeroSerie) {
        // Création des nouveaux pneus
        Pneu nouveauPneu = new Pneu(nouvelleLargeur, nouvelleMarque, contientChambre);

        // Mise à jour des pneus du vélo
        velo.getLesPneus()[0] = nouveauPneu;
        velo.getLesPneus()[1] = nouveauPneu;

        break;
      }
    }
    
  }

  /**
   * Change le pneu avant d'un vélo spécifié par son numéro de série.
   *
   * @param numeroSerie Le numéro de série du vélo dont le pneu avant doit être changé.
   * @param nouvelleMarque La nouvelle marque du pneu avant.
   * @param nouvelleLargeur La nouvelle largeur du pneu avant.
   * @param contientChambre Indique si le nouveau pneu avant contient une chambre à air.
   */
  public void changerPneuAvant(int numeroSerie, String nouvelleMarque, int nouvelleLargeur,
      boolean contientChambre) {
    for (Velo velo : this.velos) {
      if (velo.getNumeroSerie() == numeroSerie) {
        // Création du nouveau pneu avant
        Pneu nouveauPneuAvant = new Pneu(nouvelleLargeur, nouvelleMarque, contientChambre);

        // Mise à jour du pneu avant du vélo
        velo.getLesPneus()[1] = nouveauPneuAvant;

        break;
      }
    }
    
  }

  /**
   * Change le pneu arrière d'un vélo spécifié par son numéro de série.
   *
   * @param numeroSerie Le numéro de série du vélo dont le pneu arrière doit être changé.
   * @param nouvelleMarque La nouvelle marque du pneu arrière.
   * @param nouvelleLargeur La nouvelle largeur du pneu arrière.
   * @param contientChambre Indique si le nouveau pneu arrière contient une chambre à air.
   */
  public void changerPneuArriere(int numeroSerie, String nouvelleMarque, int nouvelleLargeur,
      boolean contientChambre) {
    for (Velo velo : this.velos) {
      if (velo.getNumeroSerie() == numeroSerie) {
        // Création du nouveau pneu arrière
        Pneu nouveauPneuArriere = new Pneu(nouvelleLargeur, nouvelleMarque, contientChambre);

        // Mise à jour du pneu arrière du vélo
        velo.getLesPneus()[0] = nouveauPneuArriere;

        break;
      }
    }
  
  }

  /**
   * Vérifie la validité d'un vélo.
   *
   * @param velo Le vélo à vérifier.
   * @return True si le vélo est valide, false sinon.
   */
  public boolean verifierVelo(Velo velo) {
    return VerifierVelo.isVeloValid(velo);
  }

  /**
   * Méthode de l'interface ElementVisitable. Accepte un visiteur et lui permet de visiter tous les
   * éléments du garage.
   *
   * @param visiteur L'objet visiteur.
   */
  @Override
  public void accepter(VisiteurElement visiteur) {
    for (Velo velo : velos) {
      velo.accepter(visiteur);

    }
  }


  /**
   * Obtient le numéro de série actuellement sélectionné.
   *
   * @return Le numéro de série actuellement sélectionné.
   */
  public int getSelectedNumeroSerie() {
    return selectedNumeroSerie;
  }

  /**
   * Définit le numéro de série sélectionné et notifie les observateurs du changement.
   *
   * @param numeroSerie Le nouveau numéro de série à sélectionner.
   */
  public void setSelectedNumeroSerie(int numeroSerie) {
    this.selectedNumeroSerie = numeroSerie;
      
    // Notifie les observateurs du changement dans la propriété selectedNumeroSerie.
    this.notifyObservers();
  }

}

