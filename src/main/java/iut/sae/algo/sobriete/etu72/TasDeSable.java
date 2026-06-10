package iut.sae.algo.sobriete.etu72;

public class TasDeSable {

    private static boolean donner(int[][] bac, int i1, int j1, int i2, int j2) {
        if (bac[i1][j1] - bac[i2][j2] > 1) {
            bac[i1][j1]--;
            bac[i2][j2]++;
            return true;
        }
        return false;
    }

    public static int[][] effondrer(int[][] bac) {
        boolean instable = true;
        while (instable) {
            instable = false;
            for (int i = 0; i < bac.length; i++) {
                for (int j = 0; j < bac[i].length; j++) {
                    if (i > 0)               instable = donner(bac, i, j, i-1, j) || instable;
                    if (j < bac[i].length-1) instable = donner(bac, i, j, i, j+1) || instable;
                    if (i < bac.length-1)    instable = donner(bac, i, j, i+1, j) || instable;
                    if (j > 0)               instable = donner(bac, i, j, i, j-1) || instable;
                }
            }
        }
        return bac;
    }
}
