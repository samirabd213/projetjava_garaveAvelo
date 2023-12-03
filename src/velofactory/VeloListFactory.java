package velofactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import velofacade.Velo;


/**
 * La classe VeloListFactory fournit une méthode pour créer une liste de Velo à partir d'un fichier
 * JSON ou XML. Elle utilise la bibliothèque Jackson pour la désérialisation. Elle vérifie également
 * que tous les Velo ont des numéros de série différents.
 *
 * @author Abdelkadous_Samir
 */
public class VeloListFactory {

  /**
   * Crée une liste de Velo à partir d'un fichier JSON.
   *
   * @param cheminfichier Le chemin du fichier JSON.
   * @return Une liste de Velo.
   * @throws IOException En cas d'erreur de lecture du fichier.
   */
  public List<Velo> createVeloListFromJson(String cheminfichier) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File(cheminfichier);

    // Désérialisation du fichier JSON en liste de Velo
    CollectionType listType =
        objectMapper.getTypeFactory().constructCollectionType(List.class, Velo.class);
    List<Velo> veloList = objectMapper.readValue(file, listType);

    // Vérification des numéros de série
    if (verifierNumerosSerie(veloList)) {
      return veloList;
    } else {
      throw new IllegalArgumentException(
          "Erreur : Les numéros de série des vélos ne sont pas tous différents.");
    }
  }


  /**
   * Crée une liste de Velo à partir d'un fichier XML.
   *
   * @param cheminfichier Le chemin du fichier XML.
   * @return Une liste de Velo.
   * @throws IOException En cas d'erreur de lecture du fichier.
   */
  public List<Velo> createVeloListFromXml(String cheminfichier) throws IOException {
    XmlMapper xmlMapper = new XmlMapper();
    File file = new File(cheminfichier);

    // Désérialisation du fichier XML en liste de Velo
    CollectionType listType =
        xmlMapper.getTypeFactory().constructCollectionType(List.class, Velo.class);
    List<Velo> veloList = xmlMapper.readValue(file, listType);

    // Vérification des numéros de série
    if (verifierNumerosSerie(veloList)) {
      return veloList;
    } else {
      throw new IllegalArgumentException(
          "Erreur : Les numéros de série des vélos ne sont pas tous différents.");
    }
  }



  /**
   * Vérifie que tous les Velo d'une liste ont des numéros de série différents.
   *
   * @param veloList La liste de Velo à vérifier.
   * @return true si les numéros de série sont tous différents, false sinon.
   */
  private boolean verifierNumerosSerie(List<Velo> veloList) {
    for (int i = 0; i < veloList.size() - 1; i++) {
      for (int j = i + 1; j < veloList.size(); j++) {
        if (veloList.get(i).getNumeroSerie() == veloList.get(j).getNumeroSerie()) {
          return false;
        }
      }
    }
    return true;
  }
}
