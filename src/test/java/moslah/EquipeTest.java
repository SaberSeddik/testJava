package moslah;


import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class EquipeTest {

    @Test
    public void recruteJoueur_ajouteUnJoueur_siLequipeADeLaPlace() {
        Equipe equipe = new Equipe("Montreal", "123, rue xyz Montreal");
        Joueur joueur = new Joueur("Foulan", 25, 0);

        equipe.recruterJoueur(joueur);

        assertThat(equipe.getTabJoueurs(), hasItemInArray(joueur));
    }

    @Test(expected = IllegalStateException.class)
    public void recruteJoueur_echoue_siLequipeEstDejaPleine() {
        Equipe equipe = creeUneEquipePleine();
        Joueur joueur = new Joueur("Foulan", 25, 0);

        equipe.recruterJoueur(joueur);
    }

    @Test
    public void renvoyerJoueur_enleveJustLeJoueurRenvoyer_siLeJoueurExiste() {
        Equipe equipe = creeUneEquipeVide();
        Joueur joueur1 = new Joueur("Foulan1", 25, 0);
        Joueur joueur2 = new Joueur("Foulan2", 25, 0);
        Joueur joueur3 = new Joueur("Foulan3", 25, 0);
        equipe.recruterJoueur(joueur1);
        equipe.recruterJoueur(joueur2);
        equipe.recruterJoueur(joueur3);

        equipe.renvoyerJoueur(joueur2);

        assertThat(equipe.getNbreDeJoueur(), equalTo(2));
        assertThat(equipe.getTabJoueurs(), not(hasItemInArray(joueur2)));
        assertThat(equipe.getTabJoueurs(), hasItemInArray(joueur1));
        assertThat(equipe.getTabJoueurs(), hasItemInArray(joueur3));
    }

    @Test(expected = IllegalStateException.class)
    public void renvoyerJoueur_echoue_siLeJoueurNExiste() {
        Equipe equipe = creeUneEquipePleine();
        Joueur joueur = new Joueur("Foulan", 25, 0);

        equipe.renvoyerJoueur(joueur);
    }

    @Test
    public void getButeur_retourneNull_siPasDeJoueurs() {
        Equipe equipe = creeUneEquipeVide();

        Joueur resultat = equipe.getButeur();

        assertThat(resultat, is(nullValue()));
    }

    @Test
    public void getButeur_retourneLeButeur_siLequipeADesJoueurs() {
        Equipe equipe = creeUneEquipeVide();
        Joueur joueurNonButeur1 = new Joueur("Foulan1", 25, 0);
        Joueur joueurButeur = new Joueur("Buteur", 25, 10);
        Joueur joueurNonButeur2 = new Joueur("Foulan2", 25, 0);
        equipe.recruterJoueur(joueurNonButeur1);
        equipe.recruterJoueur(joueurButeur);
        equipe.recruterJoueur(joueurNonButeur2);

        Joueur resultat = equipe.getButeur();

        assertThat(resultat, equalTo(joueurButeur));
    }

    private Equipe creeUneEquipeVide() {
        return new Equipe("Montreal", "123, rue xyz Montreal");
    }

    private Equipe creeUneEquipePleine() {
        Equipe equipe = creeUneEquipeVide();
        for (int i = 0; i < 24; ++i) {
            Joueur joueur = new Joueur("Foulan" + i, 25, 0);
            equipe.recruterJoueur(joueur);
        }
        return equipe;
    }


}