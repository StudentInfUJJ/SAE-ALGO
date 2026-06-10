package iut.sae.algo.sobriete;

public class TasDeSable {

    public static int[][] effondrer(int bac[][]) {

        /*
         * short lignes = (short) bac.length;
         * short colonnes = (short) bac[0].length;
         */
        // L'objectif de départ était de faire du narrowing afin d'optimiser la taille
        // en octet des variables mais java rend intuile cette optimisation et crée même
        // un peu de latence en raison de la JVM qui alloue toujours 32 bits (1 slot)
        // sur la pile.

        int lignes = bac.length;
        int colonnes = bac[0].length;

        if (lignes == 0 || colonnes == 0)
            return bac;

        // On cherche à limiter la zone de calcul
        int largeurMin = (colonnes - 1);
        int largeurMax = 0;
        int hauteurMin = (lignes - 1); // Narrowing pour réduire la taille en octet des variables
        int hauteurMax = 0;
        int rayon;
        // Cet algorithme est censé être efficace quand la zone délimitée est plus
        // petite que le bac de base.
        // Pour un bac trés grand avec des tas rapprochés
        // cet algorithme sera plus efficace que la version de simplicité
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {// On définit les bornes...
                if (bac[i][j] != 0) {
                    if (j < largeurMin) {
                        // le début de la largeur (borne gauche)
                        largeurMin = j;
                    }
                    if (i < hauteurMin) {
                        // Le début de la hauteur (borne haute)
                        hauteurMin = i;
                    }
                    if (j > largeurMax) {
                        // La fin de la largeur (borne droite)
                        largeurMax = j;
                    }
                    if (i > hauteurMax) {
                        // La fin de la hauteur (borne basse)
                        hauteurMax = i;
                    }
                    // On élargit cette zone en fonction de la hauteur du tas de sable actuel
                    rayon = (bac[i][j] / 2);
                    // On détermine jusqu'où on peut élargir la zone délimitée
                    if (largeurMax + rayon <= colonnes) {
                        largeurMax += rayon; // Si la zone délimitée + le rayon de la pile rentre dans le bac alors on
                                             // ajoute le rayon à la largeur maximale de la zone délimitée
                    } else {
                        largeurMax = colonnes; // Sinon on élargit la zone délimitée autant qu'on peut (c'est à
                                               // dire la largeur du bac)
                    }
                    if (largeurMin - rayon >= 0) { // S'il y'a de la place à gauche...
                        largeurMin -= rayon; // On élargit la zone délimitée vers la gauche de largeur rayon
                    } else {
                        largeurMin = 0; // Sinon on élargit autant qu'on peut vers la gauche
                    }
                    if (hauteurMin - rayon >= 0) {// On verifie la possibilite d'élargir en haut
                        hauteurMin -= rayon;
                    } else {
                        hauteurMin = 0; // Sinon on élargit autant qu'on peut jusqu'à la limite
                    }
                    if (hauteurMax + rayon <= lignes) { // On vérifie si on peut élargir en bas
                        hauteurMax += rayon;
                    } else {
                        hauteurMax = lignes; // Sinon on élargit autant qu'on peut
                    }
                }
            }
        }

        boolean bloquee = false;
        while (!bloquee) {
            int modif = 0;
            for (int i = hauteurMin; i < hauteurMax; i++) {
                for (int j = largeurMin; j < largeurMax; j++) {
                    // Petite optimisation pour éviter de faire des comparaisons sur des cases
                    // inutiles (tas stables = 1)
                    if (bac[i][j] >= 2) {
                        // Haut
                        // Dans la version sobriété on fait les comparaison et modification au même
                        // endroit afin d'optimiser l'algorithme
                        if (i > 0 && (bac[i][j] - bac[i - 1][j]) >= 2) {
                            bac[i - 1][j] += 1;
                            bac[i][j] -= 1;
                            modif++;
                        }
                        // Droite
                        if (j + 1 < colonnes && (bac[i][j] - bac[i][j + 1]) >= 2) {
                            bac[i][j + 1] += 1;
                            bac[i][j] -= 1;
                            modif++;
                        }
                        // Bas
                        if (i + 1 < lignes && (bac[i][j] - bac[i + 1][j]) >= 2) {
                            bac[i + 1][j] += 1;
                            bac[i][j] -= 1;
                            modif++;
                        }
                        // Gauche
                        if (j > 0 && (bac[i][j] - bac[i][j - 1]) >= 2) {
                            bac[i][j - 1] += 1;
                            bac[i][j] -= 1;
                            modif++;
                        }

                    }

                }
            }
            if (modif == 0) {
                bloquee = true;
            }
        }

        return bac;
    }

}