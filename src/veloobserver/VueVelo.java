package veloobserver;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import velofacade.Garageavelo;
import velofacade.Velo; 



/**
 * Classe VueVelo - représente la vue d'un vélo dans une interface graphique Swing. Implémente
 * l'interface Observer pour être notifiée des changements du modèle.
 */
public class VueVelo extends JFrame implements Observer {

  private static final long serialVersionUID = 1L;
  private Subject observable;
  private JLabel label;
  private JTextArea caracteristiquesArea;

  /**
   * Constructeur de la classe VueVelo.
   *
   * @param observable Le sujet (modèle) à observer.
   */
  public VueVelo(Subject observable) {
    this.observable = observable;
    this.observable.addObserver(this);

    label = new JLabel();

    // Crée une icône pour le coin supérieur gauche
    ImageIcon cornerIcon =
        new ImageIcon("C:\\Users\\hp\\Desktop\\archi_log\\TpArchiLog\\icones\\interg.jpeg");
    label.setIcon(cornerIcon); // Définir l'icône dans le label dès le départ
    label.setBorder(new EmptyBorder(1, 0, 0, 0));

    JButton modifierButton = new JButton("Modifier Vélo");
    modifierButton.addActionListener(e -> afficherOptionsModification());

    caracteristiquesArea = new JTextArea(20, 30);
    caracteristiquesArea.setEditable(false);
    caracteristiquesArea.setBorder(new EmptyBorder(40, 0, 0, 0));

    // Création d'un panneau pour l'icône, le bouton et la zone de texte
    JPanel iconButtonTextAreaPanel = new JPanel(new BorderLayout());
    iconButtonTextAreaPanel.add(label, BorderLayout.WEST);
    iconButtonTextAreaPanel.add(modifierButton, BorderLayout.CENTER);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(iconButtonTextAreaPanel, BorderLayout.NORTH);

    // Ajout de la zone de texte au centre du panneau principal
    panel.add(caracteristiquesArea, BorderLayout.CENTER);

    // Ajout du panneau au frame
    add(panel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);

    label.setText("");
    caracteristiquesArea.setText("                               Aucun vélo sélectionné");
  }


  @Override
  public void update() {

    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();

    if (selectedNumeroSerie == -1) {
      caracteristiquesArea.setText("             Aucun vélo sélectionné");
      // Crée une icône pour le coin supérieur gauche
      ImageIcon cornerIcon =
          new ImageIcon("C:\\Users\\hp\\Desktop\\archi_log\\TpArchiLog\\icones\\interg.jpeg");
      label.setIcon(cornerIcon);
    } else {

      afficherCaracteristiquesVelo();
    }
  }

  /**
   * Affiche les caractéristiques du vélo sélectionné dans l'interface graphique.
   *
   * @see GarageAVelo
   */
  public void afficherCaracteristiquesVelo() {
    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();
    Velo selectedVelo = null;

    // Recherche du vélo correspondant au numéro de série sélectionné
    for (Velo velo : ((Garageavelo) observable).getVelos()) {
      if (velo.getNumeroSerie() == selectedNumeroSerie) {
        selectedVelo = velo;
        break;
      }
    }

    // Vérification de l'existence du vélo
    if (selectedVelo != null) {
      // Vérification de la validité du vélo
      boolean veloValide = ((Garageavelo) observable).verifierVelo(selectedVelo);
      ImageIcon greenIcon = createGreenIcon();
      ImageIcon redIcon = createRedIcon();

      // Sélectionner l'icône en fonction de la validité du vélo
      ImageIcon selectedIcon = veloValide ? greenIcon : redIcon;

      // Afficher l'icône à côté du texte
      label.setIcon(selectedIcon);

      // Construction du message avec les caractéristiques du vélo
      String message = String.format(
          "                     Caractéristiques du Vélo:\n\n" + "       Numéro de Série: %d\n"
              +  "       Marque: %s\n"
              + "       Puissance de la Batterie: %d\n" + "       Marque de la Batterie: %s\n"
              + "       Marque de Pneu Arriere: %s\n" + "       Largeur de Pneu Arriere: %d\n"
              + "       Chambre à Air de Pneus Arriere : %s\n" + "       Marque de Pneu Avant: %s\n"
              + "       Largeur de Pneu Avant: %d\n" + "       Chambre à Air de Pneu Avant: %s\n\n",
          selectedVelo.getNumeroSerie(), selectedVelo.getMarque(),
          selectedVelo.getLabatterie().getPuissance(), selectedVelo.getLabatterie().getMarque(),
          selectedVelo.getLesPneus()[0].getMarque(), selectedVelo.getLesPneus()[0].getLargeur(),
          selectedVelo.getLesPneus()[0].getContientChambre() ? "Oui" : "Non",
          selectedVelo.getLesPneus()[1].getMarque(), selectedVelo.getLesPneus()[1].getLargeur(),
          selectedVelo.getLesPneus()[1].getContientChambre() ? "Oui" : "Non");

      // Affichage du message dans la zone de texte
      caracteristiquesArea.setText(message);
    } else {

      caracteristiquesArea.setText("Aucun vélo trouvé avec le numéro de série spécifié.");
    }
  }

  /**
   * Crée une icône verte.
   *
   * @return L'icône verte.
   */
  private ImageIcon createGreenIcon() {
    // Remplacez le chemin d'accès par le chemin de votre icône verte
    String greenIconPath = "C:\\Users\\hp\\Desktop\\archi_log\\TpArchiLog\\icones\\green_icon.jpeg";
    return new ImageIcon(greenIconPath);
  }

  /**
   * Crée une icône rouge.
   *
   * @return L'icône rouge.
   */
  private ImageIcon createRedIcon() {
    // Remplacez le chemin d'accès par le chemin de votre icône rouge
    String redIconPath = "C:\\Users\\hp\\Desktop\\archi_log\\TpArchiLog\\icones\\red_icon.jpeg";
    return new ImageIcon(redIconPath);

  }


  /**
   * Affiche les options de modification (Batterie, Pneus) et appelle la méthode appropriée.
   */
  private void afficherOptionsModification() {
    String[] options = {"Modifier Batterie", "Modifier Pneus"};
    int choix =
        JOptionPane.showOptionDialog(this, "Choisir une option de modification :", "Modifier Vélo",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    if (choix == 0) {
      modifierBatterie();
    } else if (choix == 1) {
      afficherOptionsModificationPneus();
    }
  }

  /**
   * Modifie la batterie du vélo en demandant à l'utilisateur de saisir de nouvelles informations.
   */
  public void modifierBatterie() {

    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();

    // Recherche du vélo dans la liste du garage
    Velo selectedVelo = null;
    for (Velo velo : ((Garageavelo) observable).getVelos()) {
      if (velo.getNumeroSerie() == selectedNumeroSerie) {
        selectedVelo = velo;
        break;
      }
    }

    if (selectedVelo != null) {
      // Demander à l'utilisateur de saisir les nouvelles informations pour la batterie
      int nouvellePuissance = Integer
          .parseInt(JOptionPane.showInputDialog(this, "Nouvelle puissance de la batterie :"));
      String nouvelleMarque = JOptionPane.showInputDialog(this, "Nouvelle marque de la batterie :");

      // Appeler la méthode changerBatterie de GarageAVelo
      ((Garageavelo) observable).changerBatterie(selectedNumeroSerie, nouvellePuissance,
          nouvelleMarque);

      // Mettre à jour la vue
      update();
    } else {
      JOptionPane.showMessageDialog(this, "Aucun vélo trouvé avec le numéro de série spécifié.",
          "Erreur", JOptionPane.ERROR_MESSAGE);
    }

  }

  /**
   * Affiche les options de modification des pneus (Avant, Arrière, Les Deux) et appelle la méthode
   * appropriée.
   */
  private void afficherOptionsModificationPneus() {
    String[] options = {"Modifier Pneu Avant", "Modifier Pneu Arrière", "Modifier les Deux"};
    int choix = JOptionPane.showOptionDialog(this, "Choisir une option de modification des pneus :",
        "Modifier Pneus", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
        options[0]);

    if (choix == 0) {
      modifierPneuAvant();
    } else if (choix == 1) {
      modifierPneuArriere();
    } else if (choix == 2) {
      modifierPneus();
    }
  }

  /**
   * Modifie les pneus du vélo en demandant à l'utilisateur de saisir de nouvelles informations.
   */
  public void modifierPneus() {

    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();

    // Recherche du vélo dans la liste du garage
    Velo selectedVelo = null;
    for (Velo velo : ((Garageavelo) observable).getVelos()) {
      if (velo.getNumeroSerie() == selectedNumeroSerie) {
        selectedVelo = velo;
        break;
      }
    }

    if (selectedVelo != null) {
      // Demander à l'utilisateur de saisir les nouvelles informations pour les pneus
      String nouvelleMarque = JOptionPane.showInputDialog(this, "Nouvelle marque des pneus :");
      int nouvelleLargeur =
          Integer.parseInt(JOptionPane.showInputDialog(this, "Nouvelle largeur des pneus :"));
      boolean contientChambre = Boolean.parseBoolean(JOptionPane.showInputDialog(this,
          "Les pneus contiennent-ils une chambre à air ? (true/false)"));

      // Appeler la méthode changerPneu de GarageAVelo
      ((Garageavelo) observable).changerPneu(selectedNumeroSerie, nouvelleMarque, nouvelleLargeur,
          contientChambre);

      // Mettre à jour la vue
      update();
    } else {
      JOptionPane.showMessageDialog(this, "Aucun vélo trouvé avec le numéro de série spécifié.",
          "Erreur", JOptionPane.ERROR_MESSAGE);
    }

  }

  /**
   * Modifie le pneu arrière du vélo en demandant à l'utilisateur de saisir de nouvelles
   * informations.
   */
  private void modifierPneuArriere() {

    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();

    // Recherche du vélo dans la liste du garage
    Velo selectedVelo = null;
    for (Velo velo : ((Garageavelo) observable).getVelos()) {
      if (velo.getNumeroSerie() == selectedNumeroSerie) {
        selectedVelo = velo;
        break;
      }
    }

    if (selectedVelo != null) {
      // Demander à l'utilisateur de saisir les nouvelles informations pour le pneu arrière
      String nouvelleMarque =
          JOptionPane.showInputDialog(this, "Nouvelle marque du pneu arrière :");
      int nouvelleLargeur =
          Integer.parseInt(JOptionPane.showInputDialog(this, "Nouvelle largeur du pneu arrière :"));
      boolean contientChambre = Boolean.parseBoolean(JOptionPane.showInputDialog(this,
          "Le pneu arrière contient-il une chambre à air ? (true/false)"));

      // Appeler la méthode changerPneuArriere de GarageAVelo
      ((Garageavelo) observable).changerPneuArriere(selectedNumeroSerie, nouvelleMarque,
          nouvelleLargeur, contientChambre);

      // Mettre à jour la vue
      update();
    } else {
      JOptionPane.showMessageDialog(this, "Aucun vélo trouvé avec le numéro de série spécifié.",
          "Erreur", JOptionPane.ERROR_MESSAGE);
    }

  }

  /**
   * Modifie le pneu avant du vélo en demandant à l'utilisateur de saisir de nouvelles informations.
   */
  private void modifierPneuAvant() {

    int selectedNumeroSerie = ((Garageavelo) observable).getSelectedNumeroSerie();
    // Recherche du vélo dans la liste du garage
    Velo selectedVelo = null;
    for (Velo velo : ((Garageavelo) observable).getVelos()) {
      if (velo.getNumeroSerie() == selectedNumeroSerie) {
        selectedVelo = velo;
        break;
      }
    }

    if (selectedVelo != null) {
      // Demander à l'utilisateur de saisir les nouvelles informations pour le pneu avant
      String nouvelleMarque = JOptionPane.showInputDialog(this, "Nouvelle marque du pneu avant :");
      int nouvelleLargeur =
          Integer.parseInt(JOptionPane.showInputDialog(this, "Nouvelle largeur du pneu avant :"));
      boolean contientChambre = Boolean.parseBoolean(JOptionPane.showInputDialog(this,
          "Le pneu avant contient-il une chambre à air ? (true/false)"));

      // Appeler la méthode changerPneuAvant de GarageAVelo
      ((Garageavelo) observable).changerPneuAvant(selectedNumeroSerie, nouvelleMarque,
          nouvelleLargeur, contientChambre);

      // Mettre à jour la vue
      update();
    } else {
      JOptionPane.showMessageDialog(this, "Aucun vélo trouvé avec le numéro de série spécifié.",
          "Erreur", JOptionPane.ERROR_MESSAGE);
    }

  }



}
