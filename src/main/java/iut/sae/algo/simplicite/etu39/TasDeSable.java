package iut.sae.algo.simplicite.etu39;

public class TasDeSable {

    /**
     * Simule l'effondrement complet du tas de sable
     * jusqu'à atteindre un état stable
     * 
     * @param bacASable
     * @return le bac à sable
     */
    public static int[][] effondrer(int bacASable[][]) {

        boolean changement = true;
        while (changement) {
            changement = false;
            for (int i = 0; i < bacASable.length; i++) {
                for (int j = 0; j < bacASable[0].length; j++) {
                    if (estInstable(bacASable, i, j)) {
                        effondrerCase(bacASable, i, j);
                        changement = true;
                    }
                }
            }
        }
        return bacASable;
    }

    /**
     * Vérifie si une case est instable,
     * c'est-à-dire si elle a un voisin avec plus d'1 grain de moins
     * 
     * @param bacASable
     * @param ligne
     * @param colonne
     * @return etat du bac à sable
     */
    public static boolean estInstable(int bacASable[][], int ligne, int colonne) {

        if (ligne - 1 >= 0 && bacASable[ligne][colonne] - bacASable[ligne - 1][colonne] >= 2)// haut
            return true;
        if (colonne + 1 < bacASable[0].length && bacASable[ligne][colonne] - bacASable[ligne][colonne + 1] >= 2)// droite
            return true;
        if (ligne + 1 < bacASable.length && bacASable[ligne][colonne] - bacASable[ligne + 1][colonne] >= 2)// bas
            return true;
        if (colonne - 1 >= 0 && bacASable[ligne][colonne] - bacASable[ligne][colonne - 1] >= 2)// gauche
            return true;
        return false;

    }

    /**
     * Distribue 1 grain aux voisins instables
     * dans le sens des aiguilles d'une montre
     * 
     * @param bacASable
     * @param ligne
     * @param colonne
     */
    public static void effondrerCase(int[][] bacASable, int ligne, int colonne) {

        if (ligne - 1 >= 0 && bacASable[ligne][colonne] - bacASable[ligne - 1][colonne] >= 2) {
            bacASable[ligne][colonne]--;
            bacASable[ligne - 1][colonne]++;
        }

        if (colonne + 1 < bacASable[0].length && bacASable[ligne][colonne] - bacASable[ligne][colonne + 1] >= 2) {
            bacASable[ligne][colonne]--;
            bacASable[ligne][colonne + 1]++;
        }

        if (ligne + 1 < bacASable.length && bacASable[ligne][colonne] - bacASable[ligne + 1][colonne] >= 2) {
            bacASable[ligne][colonne]--;
            bacASable[ligne + 1][colonne]++;
        }

        if (colonne - 1 >= 0 && bacASable[ligne][colonne] - bacASable[ligne][colonne - 1] >= 2) {
            bacASable[ligne][colonne]--;
            bacASable[ligne][colonne - 1]++;
        }
    }

    /**
     * On vérifie si le bac est stable
     * 
     * @param bacASable
     * @return true si le bac est stable
     */
    public static boolean bacEstStable(int[][] bacASable) {

        for (int i = 0; i < bacASable.length; i++) {
            for (int j = 0; j < bacASable[0].length; j++) {
                if (estInstable(bacASable, i, j))
                    return false;
            }
        }
        return true;
    }
}
