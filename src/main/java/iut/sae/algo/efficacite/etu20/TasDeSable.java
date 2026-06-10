package iut.sae.algo.efficacite.etu20;

/**
 * Algorithme simulant l’effondrement d’un tas de sable
 * Le sable s’écoule jusqu’à atteindre un état de stabilité
 * 
 * @version Efficacité
 */
public class TasDeSable {

    public static int[][] effondrer(int bac[][]) {
        boolean instable = true;
        int minI = 0, minJ = 0;
        int maxI = bac.length - 1, maxJ = bac[0].length - 1;

        // Complexite O(n3)
        while (instable) {
            instable = false;
            int pMinI = bac.length, pMaxI = -1;
            int pMinJ = bac[0].length, pMaxJ = -1;

            for (int i = minI; i <= maxI; i++) {
                for (int j = minJ; j <= maxJ; j++) {

                    if (i > 0 && bac[i][j] - bac[i - 1][j] > 1) {
                        bac[i - 1][j]++;
                        bac[i][j]--;
                        instable = true;

                        // Complexité est de O(1)
                        pMinI = min(pMinI, i - 1);
                        pMaxI = max(pMaxI, i);
                        pMinJ = min(pMinJ, j);
                        pMaxJ = max(pMaxJ, j);
                    }
                    if (j < bac[i].length - 1 && bac[i][j] - bac[i][j + 1] > 1) {
                        bac[i][j + 1]++;
                        bac[i][j]--;
                        instable = true;

                        // Complexité est de O(1)
                        pMinI = min(pMinI, i);
                        pMaxI = max(pMaxI, i);
                        pMinJ = min(pMinJ, j);
                        pMaxJ = max(pMaxJ, j + 1);
                    }
                    if (i < bac.length - 1 && bac[i][j] - bac[i + 1][j] > 1) {
                        bac[i + 1][j]++;
                        bac[i][j]--;
                        instable = true;

                        // Complexité est de O(1)
                        pMinI = min(pMinI, i);
                        pMaxI = max(pMaxI, i + 1);
                        pMinJ = min(pMinJ, j);
                        pMaxJ = max(pMaxJ, j);
                    }
                    if (j > 0 && bac[i][j] - bac[i][j - 1] > 1) {
                        bac[i][j - 1]++;
                        bac[i][j]--;
                        instable = true;

                        // Complexité est de O(1)
                        pMinI = min(pMinI, i);
                        pMaxI = max(pMaxI, i);
                        pMinJ = min(pMinJ, j - 1);
                        pMaxJ = max(pMaxJ, j);
                    }
                }
            }

            if (instable) {
                // A chaque appel de min() ou de max(), la complexité est de O(1)
                minI = max(0, pMinI - 1);
                maxI = min(bac.length - 1, pMaxI + 1);
                minJ = max(0, pMinJ - 1);
                maxJ = min(bac[0].length - 1, pMaxJ + 1);
            }
        }
        return bac;
    }

    /**
     * Cherche le minimum entre deux nombres
     * 
     * Complexité : O(1)
     * 
     * @param a le premier nombre
     * @param b le deuxième nombre
     * @return le plus petit nombre
     */
    static int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
    }

    /**
     * Cherche le maximum entre deux nombres
     * 
     * Complexité : O(1)
     * 
     * @param a le premier nombre
     * @param b le deuxième nombre
     * @return le plus grand nombre
     */
    static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
