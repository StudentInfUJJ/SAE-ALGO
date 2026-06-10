package iut.sae.algo.simplicite;

public class TasDeSable {

    private static int lignes = 5;

    private static int colonnes = 5;

    public static int[][] effondrer(int bac[][]) {
        int lignes = bac.length;
        if (lignes == 0)
            return bac;
        int colonnes = bac[0].length;
        if (colonnes == 0)
            return bac;

        // cet algorithme parcours tout le tableau entierement à chaque tour par
        // conséquent non optimisé
        boolean bloquee = false;
        while (!bloquee) {
            int modif = 0;
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    if (ecoulerHaut(bac, i, j, lignes, colonnes))
                        modif++;
                    if (ecoulerDroite(bac, i, j, lignes, colonnes))
                        modif++;
                    if (ecoulerBas(bac, i, j, lignes, colonnes))
                        modif++;
                    if (ecoulerGauche(bac, i, j, lignes, colonnes))
                        modif++;
                }
            }
            if (modif == 0) {
                bloquee = true;
            }
        }
        return bac;
    }

    /**
     * Cette fonction vérifie si le tas peut s'écouler en haut et s'écoule
     */
    public static boolean ecoulerHaut(int[][] bac, int i, int j, int lignes, int colonnes) {
        if (i > 0 && (bac[i][j] - bac[i - 1][j]) >= 2) {
            bac[i - 1][j] += 1;
            bac[i][j] -= 1;
            return true;
        }
        return false;
    }

    /**
     * Cette fonction vérifie si le tas peut s'écouler à droite et s'écoule
     */
    public static boolean ecoulerDroite(int[][] bac, int i, int j, int lignes, int colonnes) {
        if (j + 1 < colonnes && (bac[i][j] - bac[i][j + 1]) >= 2) {
            bac[i][j + 1] += 1;
            bac[i][j] -= 1;
            return true;
        }
        return false;
    }

    /**
     * Cette fonction vérifie si le tas peut s'écouler en Bas et s'écoule
     */
    public static boolean ecoulerBas(int[][] bac, int i, int j, int lignes, int colonnes) {
        if (i + 1 < lignes && (bac[i][j] - bac[i + 1][j]) >= 2) {
            bac[i + 1][j] += 1;
            bac[i][j] -= 1;
            return true;
        }
        return false;
    }

    /**
     * Cette fonction vérifie si le tas peut s'écouler à Gauche et s'écoule
     */
    public static boolean ecoulerGauche(int[][] bac, int i, int j, int lignes, int colonnes) {
        if (j > 0 && (bac[i][j] - bac[i][j - 1]) >= 2) {
            bac[i][j - 1] += 1;
            bac[i][j] -= 1;
            return true;
        }
        return false;
    }

}