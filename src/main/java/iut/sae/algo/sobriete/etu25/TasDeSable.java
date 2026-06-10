package iut.sae.algo.sobriete.etu25;

public class TasDeSable {

    /**
     * Gère l'effondrement global d'un bac de sable de manière sobre,
     * jusqu'à ce qu'il atteigneun état de stabilité complète.
     * @param bac Le tableau représentant la grille de sable.
     * @return Le tableau après stabilisation des grains.
     */
    public static int[][] effondrer(int[][] bac) {
        // Vérification grille valide
        if (bac == null || bac.length == 0 || bac[0].length == 0) {
            return bac;
        }

        int hauteur = bac.length;
        int largeur = bac[0].length;

        // Initialisation espace de travail
        int ligMin = 0;
        int ligMax = hauteur - 1;
        int colMin = 0;
        int colMax = largeur - 1;

        // Déclaration des variables hors des boucles pour économiser la mémoire
        int nextLigMin, nextLigMax, nextColMin, nextColMax;
        boolean aBouger;
        boolean instable = true;

        // Tant que pas stable : pas fini
        while (instable) {
            instable = false;

            // Taille de la boite
            nextLigMin = hauteur;
            nextLigMax = -1;
            nextColMin = largeur;
            nextColMax = -1;

            // Parcours dans la zone de travail
            for (int i = ligMin; i <= ligMax; i++) {
                int[] ligne = bac[i];
                for (int j = colMin; j <= colMax; j++) {
                    
                    aBouger = false;

                    // haut
                    if (i - 1 >= 0 && bac[i - 1][j] <= ligne[j] - 2) {
                        bac[i - 1][j] = bac[i - 1][j] + 1;
                        ligne[j] = ligne[j] - 1;
                        aBouger = true;
                        
                        if (i - 1 < nextLigMin) nextLigMin = i - 1;
                    }

                    // droite
                    if (j + 1 < largeur && ligne[j + 1] <= ligne[j] - 2) {
                        ligne[j + 1] = ligne[j + 1] + 1;
                        ligne[j] = ligne[j] - 1;
                        aBouger = true;
                        
                        if (j + 1 > nextColMax) nextColMax = j + 1;
                    }

                    // bas
                    if (i + 1 < hauteur && bac[i + 1][j] <= ligne[j] - 2) {
                        bac[i + 1][j] = bac[i + 1][j] + 1;
                        ligne[j] = ligne[j] - 1;
                        aBouger = true;
                        
                        if (i + 1 > nextLigMax) nextLigMax = i + 1;
                    }

                    // gauche
                    if (j - 1 >= 0 && ligne[j - 1] <= ligne[j] - 2) {
                        ligne[j - 1] = ligne[j - 1] + 1;
                        ligne[j] = ligne[j] - 1;
                        aBouger = true;
                        
                        if (j - 1 < nextColMin) nextColMin = j - 1;
                    }

                    // redefini la zone de travail
                    if (aBouger) {
                        instable = true;
                        if (i < nextLigMin) nextLigMin = i;
                        if (i > nextLigMax) nextLigMax = i;
                        if (j < nextColMin) nextColMin = j;
                        if (j > nextColMax) nextColMax = j;
                    }
                }
            }

            // case de sécurité pour anticiper les réactions
            if (instable) {
                ligMin = Math.max(0, nextLigMin - 1);
                ligMax = Math.min(hauteur - 1, nextLigMax + 1);
                colMin = Math.max(0, nextColMin - 1);
                colMax = Math.min(largeur - 1, nextColMax + 1);
            }
        }

        return bac;
    }
}
