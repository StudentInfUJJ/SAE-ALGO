package iut.sae.algo.sobriete.etu40;

public class TasDeSable {

    public static int[][] effondrer(int bac[][]) {
        boolean instable = true;
        int nbLignes = bac.length;
        int nbColonnes = bac[0].length;

        while (instable) {
            instable = false;
            for (int i = 0 ; i < nbLignes ; i ++) {
                for (int j = 0; j < nbColonnes  ; j ++) {
                    if (i > 0 && bac[i][j] - bac[i-1][j] >= 2) {
                        bac[i][j] --;
                        bac[i-1][j] ++;
                        instable = true;
                    }
                    if (j + 1 < nbColonnes  && bac[i][j] - bac[i][j+1] >= 2) {
                        bac[i][j] --;
                        bac[i][j+1] ++;
                        instable = true;
                    }
                    if (i + 1 < nbLignes && bac[i][j] - bac[i+1][j] >= 2) {
                        bac[i][j] --;
                        bac[i+1][j] ++;
                        instable = true;
                    }
                    if (j > 0 && bac[i][j] - bac[i][j-1] >= 2) {
                        bac[i][j] --;
                        bac[i][j-1] ++;
                        instable = true;
                    }
                }
            }
        }
        return bac;
    }
}
