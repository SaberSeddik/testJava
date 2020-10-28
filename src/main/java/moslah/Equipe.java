package moslah;

/**
 * Écrire la classe Équipe caractérisée par les attributs suivants : nomEquipe (le nom de l’équipe), TabJoueurs
 * (la liste des joueurs appartenant à l’équipe, de taille maximale 24), nbJoueurs (le nombre de joueurs de l’équipe) et siege (adresse du siège officiel de l’équipe).
 * <p>
 * La classe Équipe est composée des méthodes suivantes :
 * <p>
 * - constructeur permettant de donner un nom à l’équipe et de définir le siège,
 * - recruterJoueur pour ajouter un nouveau joueur à l’équipe (respecter la condition que le nombre maximal de joueurs est 24),
 * - renvoyerJoueur pour supprimer un joueur de l’équipe,
 * - getButeur pour retourner le nom du meilleur buteur de l’équipe.
 */
public class Equipe {
    private static final int NBRE_MAX_DE_JOUEUR = 25;

    private String nomEquipe;
    private final Joueur[] tabJoueurs = new Joueur[NBRE_MAX_DE_JOUEUR];
    private int nbJoueurs = 0;
    private String siege;

    public Equipe(String nomEquipe, String siege) {
        this.nomEquipe = nomEquipe;
        this.siege = siege;
    }

    public Joueur[] getTabJoueurs() {
        return tabJoueurs;
    }

    public int getNbreDeJoueur() {
        return nbJoueurs;
    }

    public void recruterJoueur(Joueur joueur) {
        if (nbJoueurs == NBRE_MAX_DE_JOUEUR) {
            throw new IllegalStateException("Nombre de joueur est deja 24. On ne peux plus ajouter persone.");
        }
        tabJoueurs[nbJoueurs] = joueur;
        nbJoueurs++;
    }

    public void renvoyerJoueur(Joueur joueur) {
        int indexDuJoueur = -1;
        for (int i = 0; i < nbJoueurs; i++) {
            if (tabJoueurs[i].equals(joueur)) {
                indexDuJoueur = i;
                break;
            }
        }
        //Joueur non trouver
        if (indexDuJoueur == -1) {
            throw new IllegalStateException("Joueur ne fesant pas partie de l'equipe.");
        }
        for (int i = indexDuJoueur; i < nbJoueurs; i++) {
            tabJoueurs[i] = tabJoueurs[i + 1];
        }
        nbJoueurs--;
    }

    /**
     * Methode qui va retourner le premier joueur dand le table qui a leplus de points.
     */
    public Joueur getButeur() {
        if (nbJoueurs == 0) {
            return null;
        }
        Joueur buteur = tabJoueurs[0];
        for (int i = 0; i < nbJoueurs; i++) {
            if (buteur.getNbButs() < tabJoueurs[i].getNbButs()) {
                buteur = tabJoueurs[i];
            }
        }
        return buteur;
    }
}
