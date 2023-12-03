package velovisiteur;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import velocomposants.Batterie;
import velocomposants.Pneu;
import velofacade.Velo;


/**
 * Classe de tests unitaires pour la classe Visiteur1.
 */
public class Visiteur1Test {

  /**
   * Teste la méthode visiter(Velo velo) de la classe Visiteur1.
   */
  @Test
  public void testVisiterVelo() {

    Velo velo = new Velo();
    Visiteur1 visiteur1 = new Visiteur1();

    // Rediriger la sortie standard pour capturer les impressions
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    visiteur1.visiter(velo);

    // Récupérer la sortie standard
    String output = outContent.toString();

    assertTrue(output.contains("Caracteristiques du Velo"));
    assertTrue(output.contains("Numero de serie"));
  }

  /**
   * Teste la méthode visiter(Batterie batterie) de la classe Visiteur1.
   */
  @Test
  public void testVisiterBatterie() {
    Batterie batterie = new Batterie(800, "NouvelleMarque");
    Visiteur1 visiteur1 = new Visiteur1();

    // Rediriger la sortie standard pour capturer les impressions
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    visiteur1.visiter(batterie);

    // Récupérer la sortie standard
    String output = outContent.toString();

    assertTrue(output.contains("Caracteristiques de la Batterie"));
    assertTrue(output.contains("Capacite"));
  }

  /**
   * Teste la méthode visiter(Pneu pneu) de la classe Visiteur1.
   */
  @Test
  public void testVisiterPneu() {
    Pneu pneu = new Pneu(24, "NouveauPneu", true);
    Visiteur1 visiteur1 = new Visiteur1();

    // Rediriger la sortie standard pour capturer les impressions
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    visiteur1.visiter(pneu);

    // Récupérer la sortie standard
    String output = outContent.toString();

    assertTrue(output.contains("Caracteristiques des pneus"));
    assertTrue(output.contains("largeur"));
  }
}
