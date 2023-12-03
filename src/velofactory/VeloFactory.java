package velofactory;

import velocomposants.Batterie;
import velocomposants.Pneu;
import velofacade.Velo;

/**
 * La classe VeloFactory est une fabrique permettant de créer des objets de la classe Velo avec des
 * configurations spécifiques. Elle contient des méthodes pour créer des Velos, configurer la
 * batterie, les pneus, et le modèle. Elle garde également une trace du nombre de Velos créés.
 * Note : La fabrique prend en compte une configuration par défaut lors de la création d'un Velo.
 * Vous pouvez ensuite utiliser les méthodes de configuration pour personnaliser les
 * caractéristiques du Velo.
 * Exemple d'utilisation : VeloFactory factory = new VeloFactory("MarqueVelo"); Velo velo1 =
 * factory.createVelo(); // Crée un Velo avec la configuration par défaut
 * factory.configurerBatterie(800, "NouvelleMarque"); // Configure une nouvelle batterie
 * factory.configurerPneus("NouveauPneu", 24, true); // Configure de nouveaux pneus
 * factory.configurerModel("NouveauModele"); // Configure un nouveau modèle Velo velo2 =
 * factory.createVelo(); // Crée un deuxième Velo avec la nouvelle configuration
 *
 * @author Abdelkadous_Samir
 */
public class VeloFactory {

  /**
   * Le nombre de Velos créés.
   */
  private static int nbrvelo = 0;

  /**
   * La marque par défaut utilisée lors de la création d'un Velo.
   */
  private String marque;

  /**
   * Le modèle par défaut utilisé lors de la création d'un Velo.
   */
  private String modele;

  /**
   * La puissance de batterie par défaut utilisée lors de la création d'un Velo.
   */
  private int puissanceBatterie;

  /**
   * La marque de batterie par défaut utilisée lors de la création d'un Velo.
   */
  private String marqueBatterie;

  /**
   * La marque des pneus par défaut utilisée lors de la création d'un Velo.
   */
  private String pneuMarque;

  /**
   * La largeur des pneus par défaut utilisée lors de la création d'un Velo.
   */
  private Integer pneuLargeur;

  /**
   * Un indicateur indiquant si les pneus contiennent une chambre à air par défaut lors de la
   * création d'un Velo.
   */
  private boolean contienChambre;

  /**
   * Constructeur de la classe VeloFactory.
   *
   * @param marque La marque par défaut pour les Velos créés par la fabrique.
   */
  public VeloFactory(String marque) {
    this.marque = marque;
    this.modele = "model1";
    this.puissanceBatterie = 600;
    this.marqueBatterie = "GasGas";
    this.pneuMarque = "Hitwaypneus";
    this.pneuLargeur = 26;
    this.contienChambre = true;
  }

  /**
   * Crée un Velo avec la configuration actuelle de la fabrique. Incrémente le nombre de Velos
   * créés.
   *
   * @return Un nouvel objet Velo créé avec la configuration actuelle.
   */
  public Velo createVelo() {
    Batterie battery = new Batterie(this.puissanceBatterie, this.marqueBatterie);
    Pneu[] lesPneus = new Pneu[2];
    lesPneus[0] = new Pneu(this.pneuLargeur, this.pneuMarque, this.contienChambre);
    lesPneus[1] = new Pneu(this.pneuLargeur, this.pneuMarque, this.contienChambre);

    Velo newVelo = new Velo(this.modele, this.marque, battery, lesPneus);
    newVelo.setNumeroSerie(nbrvelo);

    nbrvelo++;

    return newVelo;
  }

  /**
   * Configure la puissance et la marque de la batterie pour les Velos créés par la fabrique.
   *
   * @param puissanceBatterie La puissance de batterie à attribuer aux Velos créés.
   * @param marqueBatterie La marque de batterie à attribuer aux Velos créés.
   */
  public void configurerBatterie(int puissanceBatterie, String marqueBatterie) {
    this.puissanceBatterie = puissanceBatterie;
    this.marqueBatterie = marqueBatterie;
  }

  /**
   * Configure la marque, la largeur et l'indicateur de chambre à air des pneus pour les Velos créés
   * par la fabrique.
   *
   * @param pneuMarque La marque des pneus à attribuer aux Velos créés.
   * @param pneuLargeur La largeur des pneus à attribuer aux Velos créés.
   * @param contienChambre L'indicateur de chambre à air à attribuer aux Velos créés.
   */
  public void configurerPneus(String pneuMarque, int pneuLargeur, boolean contienChambre) {
    this.pneuMarque = pneuMarque;
    this.pneuLargeur = pneuLargeur;
    this.contienChambre = contienChambre;
  }

  /**
   * Configure le modèle par défaut pour les Velos créés par la fabrique.
   *
   * @param modele Le modèle à attribuer aux Velos créés.
   */
  public void configurerModel(String modele) {
    this.modele = modele;
  }

  /**
   * Obtient le nombre total de Velos créés par la fabrique.
   *
   * @return Le nombre total de Velos créés.
   */
  public static int getNumberOfVelosCreer() {
    return nbrvelo;
  }
}

