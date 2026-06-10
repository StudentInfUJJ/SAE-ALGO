package iut.sae.algo.efficacite.etu34;

public class TasDeSable {
    public static int[][] effondrer(int bac[][]) {

        int ligne = bac.length;
        int colonne = bac[0].length;

        // pour l'efficacite on ne va chercher que dans la zone ou ca bouge au debut on prend toute la table
        int minLigne = 0;
        int maxLigne = ligne - 1;
        int minCol = 0;
        int maxCol = colonne - 1;

        // on remplace la boucle for par un while
        boolean auMoinsUnChangement = true;

        while (auMoinsUnChangement) {

            auMoinsUnChangement = false;

            // on prepare les futures limites de notre zone de recherche pour le tour suivant
            int futurMinLigne = ligne, futurMaxLigne = 0;
            int futurMinCol = colonne, futurMaxCol = 0;

            // la boucle lit que le carré de base, mais on repousse les limites si jamais ca coule en dehors
            for (int i = minLigne; i <= maxLigne; i++) {
                for (int j = minCol; j <= maxCol; j++) {
                    
                    //prend en valeur si une case a bougé pendant le tour
                    boolean caseBouge = false;

                    if ((i > 0) && (bac[i][j] - bac[i - 1][j] >= 2)) {
                        bac[i][j] -= 1;
                        bac[i - 1][j] += 1;
                        caseBouge = true;
                    }

                    if ((j < colonne - 1) && (bac[i][j] - bac[i][j + 1] >= 2)) {
                        bac[i][j] -= 1;
                        bac[i][j + 1] += 1;
                        caseBouge = true;
                    }

                    if ((i < ligne - 1) && (bac[i][j] - bac[i + 1][j] >= 2)) {
                        bac[i][j] -= 1;
                        bac[i + 1][j] += 1;
                        caseBouge = true;
                    }

                    if ((j > 0) && (bac[i][j] - bac[i][j - 1] >= 2)) {
                        bac[i][j] -= 1;
                        bac[i][j - 1] += 1;
                        caseBouge = true;
                    }

                    if (caseBouge) {
                        auMoinsUnChangement = true;

                        // On calcule la limite haut
                        int zoneHaut;
                        if (i > 0) {
                            zoneHaut = i - 1; // Si on n'est pas tout en haut, on monte d'une case
                        } else {
                            zoneHaut = 0; // Sinon, on reste bloqué à la ligne 0
                        }

                        // pareil pour le bas
                        int zoneBas;
                        if (i < ligne - 1) {
                            zoneBas = i + 1;
                        } else {
                            zoneBas = ligne - 1;
                        }

                        int zoneGauche;
                        if (j > 0) {
                            zoneGauche = j - 1;
                        } else {
                            zoneGauche = 0;
                        }

                        int zoneDroite;
                        if (j < colonne - 1) {
                            zoneDroite = j + 1;
                        } else {
                            zoneDroite = colonne - 1; //
                        }

                        // On enregistre les limites pour le prochain tour
                        if (zoneHaut < futurMinLigne)
                            futurMinLigne = zoneHaut;
                        if (zoneBas > futurMaxLigne)
                            futurMaxLigne = zoneBas;
                        if (zoneGauche < futurMinCol)
                            futurMinCol = zoneGauche;
                        if (zoneDroite > futurMaxCol)
                            futurMaxCol = zoneDroite;

                        // on pousse les limites tout de suite pour pas bloquer le sable qui tombe en bas a droite
                        if (zoneBas > maxLigne)
                            maxLigne = zoneBas;
                        if (zoneDroite > maxCol)
                            maxCol = zoneDroite;
                    }

                }
            }

            // on met à jour le carré pour le tour d'après
            if (auMoinsUnChangement) {
                minLigne = futurMinLigne;
                maxLigne = futurMaxLigne;
                minCol = futurMinCol;
                maxCol = futurMaxCol;
            }
        }

        return bac;
    }
}
