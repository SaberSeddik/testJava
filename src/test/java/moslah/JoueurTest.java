package moslah;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JoueurTest extends TestCase {

    @Test
    public void test_marquerBut_incrimente_le_nombre_de_buts() {
        Joueur joueur = new Joueur("Foulan El Foulani", 33, 3);
        int nbreDeButInitiale = joueur.getNbButs();

        joueur.marquerBut();

        assertThat(joueur.getNbButs(), equalTo(nbreDeButInitiale + 1));
    }
}