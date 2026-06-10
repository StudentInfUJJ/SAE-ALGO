package iut.sae.algo.efficacite;

// etu 6

public class TasDeSable {

    public static int[][] effondrer(int[][] bac) {

        if (bac == null || bac.length == 0 || bac[0].length == 0) {
            return bac;
        }

        int lignes = bac.length;
        int cols = bac[0].length;

        // Copie du tableau
        int[][] grille = new int[lignes][cols];
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < cols; j++) {
                grille[i][j] = bac[i][j];
            }
        }

        boolean modif = true;
        while (modif) {
            modif = false;
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < cols; j++) {
                    // Haut - O(1)
                    if (i > 0 && grille[i][j] - grille[i - 1][j] > 1) {
                        grille[i][j]--;
                        grille[i - 1][j]++;
                        modif = true;
                    }
                    // Droite - O(1)
                    if (j < cols - 1 && grille[i][j] - grille[i][j + 1] > 1) {
                        grille[i][j]--;
                        grille[i][j + 1]++;
                        modif = true;
                    }
                    // Bas - O(1)
                    if (i < lignes - 1 && grille[i][j] - grille[i + 1][j] > 1) {
                        grille[i][j]--;
                        grille[i + 1][j]++;
                        modif = true;
                    }
                    // Gauche - O(1)
                    if (j > 0 && grille[i][j] - grille[i][j - 1] > 1) {
                        grille[i][j]--;
                        grille[i][j - 1]++;
                        modif = true;
                    }
                }
            }
        }

        return grille;
    }
}
