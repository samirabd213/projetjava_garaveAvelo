package velofacade;


import java.util.Calendar;
import velocomposants.Batterie;
import velocomposants.Marque;
import velocomposants.Pneu;
import velovisiteur.ElementVisitable;
import velovisiteur.VisiteurElement;


/**
 * La classe Velo représente un vélo avec des propriétés telles que la marque, le modèle, le numéro
 * de série, la batterie, et les pneus. Elle hérite de la classe Marque et implémente l'interface
 * ElementVisitable.
 * Note : Le numéro de série peut être défini à l'aide de la méthode setNumeroSerie. Cependant, dans
 * le constructeur actuel, le numéro de série est initialisé à zéro par défaut. Si le numéro de
 * série est important, assurez-vous de le définir explicitement après la création de l'objet Velo.
 *
 * @author Abdelkadous_Samir
 */
public class Velo extends Marque implements ElementVisitable {

  /**
   * Le modèle du vélo.
   */
  private String modele;

  /**
   * Le numéro de série du vélo.
   */
  private int numeroSerie;

  /**
   * La batterie du vélo.
   */
  private Batterie labatterie;

  /**
   * Les pneus du vélo.
   */
  private Pneu[] lesPneus = new Pneu[2];


  /**
   * Constructeur par défaut nécessaire pour la désérialisation avec Jackson. Initialise la classe
   * parente avec une marque par défaut.
   */
  public Velo() {
    super(""); // Appel au constructeur de la classe parente avec une valeur par défaut
  }

  /**
   * Constructeur de la classe Velo.
   *
   * @param modele Le modèle du vélo.
   * @param marque La marque du vélo (héritée de la classe Marque).
   * @param labatterie La batterie du vélo.
   * @param lesPneus Les pneus du vélo.
   */
  public Velo(String modele, String marque, Batterie labatterie, Pneu[] lesPneus) {
    super(marque);
    this.modele = modele;
    this.labatterie = labatterie;
    this.lesPneus = lesPneus;
  }

  /**
   * Obtient le modèle du vélo.
   *
   * @return Le modèle du vélo.
   */
  public String getModele() {
    return this.modele;
  }

  /**
   * Obtient le numéro de série du vélo.
   *
   * @return Le numéro de série du vélo.
   */
  public int getNumeroSerie() {
    return this.numeroSerie;
  }

  /**
   * Obtient la batterie du vélo.
   *
   * @return La batterie du vélo.
   */
  public Batterie getLabatterie() {
    return this.labatterie;
  }

  /**
   * Obtient les pneus du vélo.
   *
   * @return Les pneus du vélo.
   */
  public Pneu[] getLesPneus() {
    return this.lesPneus;
  }

  /**
   * Définit une nouvelle batterie pour le vélo.
   *
   * @param nouvBatr La nouvelle batterie à attribuer au vélo.
   */
  public void setBatterie(Batterie nouvBatr) {
    this.labatterie = nouvBatr;
  }

  /**
   * Définit de nouveaux pneus pour le vélo.
   *
   * @param nouvPneus Les nouveaux pneus à attribuer au vélo.
   */
  public void setPneus(Pneu[] nouvPneus) {
    this.lesPneus = nouvPneus;
  }

  /**
   * Définit un numéro de série pour le vélo en concaténant avec l'année actuelle.
   *
   * @param numeroSerie Le nouveau numéro de série à attribuer au vélo.
   */
  public void setNumeroSerie(int numeroSerie) {
    int anneeActuelle = Calendar.getInstance().get(Calendar.YEAR);
    this.numeroSerie = Integer.parseInt(anneeActuelle + "" + numeroSerie);
  }

  /**
   * Méthode d'acceptation pour le visiteur. Appelle la méthode correspondante du visiteur pour le
   * Velo.
   *
   * @param visiteur Le visiteur qui va traiter le Velo.
   */
  @Override
  public void accepter(VisiteurElement visiteur) {
    visiteur.visiter(this);
    visiteur.visiter(labatterie);
    visiteur.visiter(this.lesPneus[0]);
    visiteur.visiter(this.lesPneus[1]);
  }
}

