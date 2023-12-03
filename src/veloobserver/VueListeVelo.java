package veloobserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import velofacade.Garageavelo;
import velofacade.Velo;


/**
 * Classe VueListeVelo - représente la vue de la liste des vélos dans une interface graphique Swing.
 * Implémente l'interface Observer pour être notifiée des changements du modèle.
 */
public class VueListeVelo extends JFrame implements Observer {


  private static final long serialVersionUID = 1L;
  private Subject observable;
  private DefaultListModel<String> model;
  private JList<String> veloList;

  /**
   * Constructeur de la classe VueListeVelo.
   *
   * @param observable Le sujet (modèle) à observer.
   */
  public VueListeVelo(Subject observable) {
    this.observable = observable;
    this.observable.addObserver(this);

    model = new DefaultListModel<>();
    veloList = new JList<>(model);

    JButton ajouterButton = new JButton("Ajouter Velo");
    JButton supprimerButton = new JButton("Supprimer Velo");
    JButton importerButton = new JButton("Importer depuis un fichier");
    ajouterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ajouterVelo();
      }
    });

    supprimerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        supprimerVelo();
      }
    });

    importerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        importerDepuisFichier();
      }
    });
    JButton afficherCaracteristiquesButton = new JButton("Afficher Caractéristiques");
    afficherCaracteristiquesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        afficherCaracteristiquesVelo();
      }
    });

    JPanel panel = new JPanel();
    panel.add(new JScrollPane(veloList));
    panel.add(ajouterButton);
    panel.add(supprimerButton);
    panel.add(importerButton);
    panel.add(afficherCaracteristiquesButton);
    add(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    afficherListeVeloInitial();
  }

  /**
   * Affiche la liste initiale des vélos.
   */
  private void afficherListeVeloInitial() {
    List<Velo> velos = ((Garageavelo) observable).getVelos();
    model.clear();
    for (Velo velo : velos) {
      model.addElement(
          "Numéro de série : " + velo.getNumeroSerie() + " | Marque : " + velo.getMarque());
      // Ajoutez d'autres informations si nécessaire
    }
  }

  @Override
  public void update() {
    List<Velo> velos = ((Garageavelo) this.observable).getVelos();
    model.clear();
    for (Velo velo : velos) {
      model.addElement(
          "Numéro de série : " + velo.getNumeroSerie() + ", Marque : " + velo.getMarque());
      // Ajoutez d'autres informations si nécessaire
    }
  }

  private void afficherCaracteristiquesVelo() {
    int numeroSerie = this.selectionnerVelo();
    ((Garageavelo) this.observable).setSelectedNumeroSerie(numeroSerie);
  }

  /**
   * Ajoute un nouveau vélo en demandant à l'utilisateur de saisir les informations nécessaires.
   */
  private void ajouterVelo() {
    String modele = JOptionPane.showInputDialog(this, "Modèle du vélo :");
    String marque = JOptionPane.showInputDialog(this, "Marque du vélo :");
    int puissanceBatterie =
        Integer.parseInt(JOptionPane.showInputDialog(this, "Puissance de la batterie :"));
    String marqueBatterie = JOptionPane.showInputDialog(this, "Marque de la batterie :");
    String pneuMarque = JOptionPane.showInputDialog(this, "Marque des pneus :");
    int pneuLargeur = Integer.parseInt(JOptionPane.showInputDialog(this, "Largeur des pneus :"));
    boolean contientChambre = Boolean.parseBoolean(JOptionPane.showInputDialog(this,
        "Les pneus contiennent-ils une chambre à air ? (true/false)"));

    ((Garageavelo) observable).ajouterVelo(modele, marque, puissanceBatterie, marqueBatterie,
        pneuMarque, pneuLargeur, contientChambre);
    ((Garageavelo) this.observable).setSelectedNumeroSerie(-1);

  }

  /**
   * Supprime le vélo sélectionné de la liste.
   */
  private void supprimerVelo() {


    int numeroSerie = this.selectionnerVelo();

    if (numeroSerie != -1) {
      // Supprimer le vélo seulement si le numéro de série est valide
      ((Garageavelo) observable).supprimerVelo(numeroSerie);
      ((Garageavelo) this.observable).setSelectedNumeroSerie(-1);

    } else {
      // Gérez le cas où le numéro de série n'est pas valide
      System.err.println("aucun velo selectionner");
    }

  }



  /**
   * Méthode appelée lorsqu'un utilisateur sélectionne un vélo.
   *
   * @return Le numéro de série du vélo sélectionné, ou -1 s'il n'y a pas de vélo sélectionné.
   */
  private int selectionnerVelo() {
    int selectedIndex = veloList.getSelectedIndex();
    if (selectedIndex != -1) {
      String selectedValue = model.getElementAt(selectedIndex);
      int numeroSerie = extractNumeroSerie(selectedValue);
      if (numeroSerie != -1) {
        ((Garageavelo) this.observable).setSelectedNumeroSerie(numeroSerie);
        // Notifie les observateurs seulement si le numéro de série est valide

        return numeroSerie; // Retourne le numéro de série
      } else {
        // Gérez le cas où le numéro de série n'est pas valide
        System.err.println("Aucun vélo sélectionné");
        return -1; // Retourne -1 pour indiquer qu'aucun vélo n'est sélectionné
      }
    } else {

      return -1;
    }
  }

  /**
   * Extracte le numéro de série à partir de la chaîne spécifiée.
   *
   * @param value La chaîne contenant le numéro de série.
   * @return Le numéro de série extrait en tant qu'entier, ou -1 si l'extraction échoue.
   */
  private int extractNumeroSerie(String value) {
    try {
      // Supprimez tous les caractères non numériques de la chaîne
      String numeroSerieStr = value.replaceAll("\\D+", "");
      // Convertissez le numéro de série en entier
      return Integer.parseInt(numeroSerieStr);
    } catch (NumberFormatException e) {
      // Gérez l'exception ici, par exemple, imprimez un message de débogage
      System.err.println("Erreur lors de l'extraction du numéro de série : " + e.getMessage());
      return -1; // ou une autre valeur par défaut
    }
  }

  /**
   * Importe la liste des vélos à partir d'un fichier sélectionné par l'utilisateur.
   */
  private void importerDepuisFichier() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      // Obtenez le fichier sélectionné
      java.io.File selectedFile = fileChooser.getSelectedFile();

      // Obtenez l'extension du fichier
      String extension = getFileExtension(selectedFile);

      // Appelez la méthode appropriée pour charger depuis le fichier
      try {
        if ("json".equalsIgnoreCase(extension)) {
          ((Garageavelo) observable).chargerVelosDepuisFichierjson(selectedFile.getAbsolutePath());
        } else if ("xml".equalsIgnoreCase(extension)) {
          ((Garageavelo) observable).chargerVelosDepuisFichierxml(selectedFile.getAbsolutePath());
        } else {
          System.err.println("Format de fichier non pris en charge.");
          JOptionPane.showMessageDialog(this, "Format de fichier non pris en charge.", "Erreur",
              JOptionPane.ERROR_MESSAGE);
        }
        ((Garageavelo) this.observable).setSelectedNumeroSerie(-1);

      } catch (IOException ex) {

        JOptionPane.showMessageDialog(this,
            "Erreur lors du chargement du fichier : " + ex.getMessage(), "Erreur",
            JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException e) {


        JOptionPane.showMessageDialog(this,
            "Erreur : Les numéros de série des vélos ne sont pas tous différents.", "Erreur",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }


  /**
   * Obtient l'extension du fichier.
   *
   * @param file Le fichier dont on veut obtenir l'extension.
   * @return L'extension du fichier.
   */
  private String getFileExtension(java.io.File file) {
    String name = file.getName();
    int lastIndexOfDot = name.lastIndexOf(".");
    if (lastIndexOfDot == -1 || lastIndexOfDot == 0) {
      return "";
    }
    return name.substring(lastIndexOfDot + 1);
  }



}


