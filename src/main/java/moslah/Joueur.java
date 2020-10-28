package moslah;

import java.util.Objects;

/**
 * Écrire la classe Joueur ayant comme attributs : nomJoueur (le nom du joueur), age (son âge),
 * et nbButs (le nombre de buts qu’il a marqué dans la saison actuelle).
 * <p>
 * La classe Joueur dispose des méthodes suivantes :
 * - constructeur : permet d’initialiser tous les attributs de la classe,
 * - getNomJoueur : retourne le nom du joueur,
 * - getNbButs : retourne le nombre de buts marqués,
 * - marquerBut : permet d’incrémenter le nombre de Buts marqués.
 */
public class Joueur {
    private String nomJoueur;
    private int age;
    private int nbButs;

    public Joueur(String nomJoueur, int age, int nbButs) {
        this.nomJoueur = nomJoueur;
        this.age = age;
        this.nbButs = nbButs;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public int getNbButs() {
        return nbButs;
    }

    public void marquerBut() {
        nbButs++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return age == joueur.age &&
                nbButs == joueur.nbButs &&
                Objects.equals(nomJoueur, joueur.nomJoueur);
    }

}
