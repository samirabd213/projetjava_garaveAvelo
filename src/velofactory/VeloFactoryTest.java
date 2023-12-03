package velofactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import velocomposants.Pneu;
import velofacade.Velo;

/**
 * Classe de tests unitaires pour la classe VeloFactory.
 */
public class VeloFactoryTest {

  /**
   * Teste la création d'un Velo avec la configuration par défaut.
   */
  @Test
  public void testCreateVelo() {
    VeloFactory factory = new VeloFactory("MarqueVelo");
    Velo velo = factory.createVelo();

    assertNotNull(velo);
    assertEquals("MarqueVelo", velo.getMarque());
    assertEquals("model1", velo.getModele());
    assertEquals(600, velo.getLabatterie().getPuissance());
    assertEquals("GasGas", velo.getLabatterie().getMarque());

    Pneu[] pneus = velo.getLesPneus();
    assertNotNull(pneus);
    assertEquals(2, pneus.length);
    assertEquals("Hitwaypneus", pneus[0].getMarque());
    assertEquals(26, pneus[0].getLargeur());
    assertTrue(pneus[0].getContientChambre());

    assertEquals(2, VeloFactory.getNumberOfVelosCreer());
  }

  /**
   * Teste la configuration de la batterie.
   */
  @Test
  public void testConfigurerBatterie() {
    VeloFactory factory = new VeloFactory("MarqueVelo");
    factory.configurerBatterie(800, "NouvelleMarque");

    Velo velo = factory.createVelo();
    assertEquals(800, velo.getLabatterie().getPuissance());
    assertEquals("NouvelleMarque", velo.getLabatterie().getMarque());
  }

  /**
   * Teste la configuration des pneus.
   */
  @Test
  public void testConfigurerPneus() {
    VeloFactory factory = new VeloFactory("MarqueVelo");
    factory.configurerPneus("NouveauPneu", 24, true);

    Velo velo = factory.createVelo();
    Pneu[] pneus = velo.getLesPneus();
    assertNotNull(pneus);
    assertEquals("NouveauPneu", pneus[0].getMarque());
    assertEquals(24, pneus[0].getLargeur());
    assertTrue(pneus[0].getContientChambre());
  }

  /**
   * Teste la configuration du modèle.
   */
  @Test
  public void testConfigurerModel() {
    VeloFactory factory = new VeloFactory("MarqueVelo");
    factory.configurerModel("NouveauModele");

    Velo velo = factory.createVelo();
    assertEquals("NouveauModele", velo.getModele());
  }
}
