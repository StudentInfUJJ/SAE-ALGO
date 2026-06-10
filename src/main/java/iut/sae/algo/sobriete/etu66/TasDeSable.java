package iut.sae.algo.sobriete.etu66;

public class TasDeSable {

    /*
     * SOBRIETE(memoire + calcul). Calcul sur place, zero allocation. Une seule
     * ecriture memoire par case(et seulement si elle change), cache des lignes
     * pour moins de lectures, arret des qu'une passe ne bouge plus.
     * Aucune methode de bibliotheque (pas de split/sort).
     * Memoire auxiliaire : O(1). Temps : O(P*L*C), P (balayages) <= O(H).
     */
    public static int[][] effondrer(int[][] bac) {
        if (bac == null || bac.length == 0 || bac[0].length == 0) {
            return bac;
        }

        final int nbLignes = bac.length;
        final int nbColonnes = bac[0].length;
        final int derniereLigne = nbLignes - 1;
        final int derniereColonne = nbColonnes - 1;

        boolean modifie = true;
        while (modifie) {
            modifie = false;
            for (int i = 0; i < nbLignes; i++) {
                final int[] ligne = bac[i];
                final int[] ligneHaut = (i > 0) ? bac[i - 1] : null;
                final int[] ligneBas = (i < derniereLigne) ? bac[i + 1] : null;
                for (int j = 0; j < nbColonnes; j++) {
                    int courant = ligne[j];
                    // haut
                    if (ligneHaut != null && courant - ligneHaut[j] >= 2) {
                        courant--;
                        ligneHaut[j]++;
                    }
                    // droite
                    if (j < derniereColonne && courant - ligne[j + 1] >= 2) {
                        courant--;
                        ligne[j + 1]++;
                    }
                    // bas
                    if (ligneBas != null && courant - ligneBas[j] >= 2) {
                        courant--;
                        ligneBas[j]++;
                    }
                    // gauche
                    if (j > 0 && courant - ligne[j - 1] >= 2) {
                        courant--;
                        ligne[j - 1]++;
                    }
                    // Une seule ecriture memoire, et seulement si necessaire.
                    if (courant != ligne[j]) {
                        ligne[j] = courant;
                        modifie = true;
                    }
                }
            }
        }
        return bac;
    }
}
