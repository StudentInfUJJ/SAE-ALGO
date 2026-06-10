package iut.sae.algo.sobriete.etu28;

    /**
     * Simule l'effondrement du tas de sable.
     * Optimise l'impact numérique en limitant au strict minimum l'allocation de mémoire.
     * @param bac Matrice initiale du tas de sable.
     * @return Matrice stabilisée.
     */
public class TasDeSable {
    public static int[][] effondrer(int[][] bac) {
        if (bac == null) return null;

        int nombreLignes = bac.length;
        int[][] resultat = new int[nombreLignes][];
        for (int i = 0; i < nombreLignes; i++) {
            resultat[i] = bac[i].clone();
        }

        int[] directions = {-1, 0, 0, 1, 1, 0, 0, -1};
        boolean modification = true;

        while (modification) {
            modification = false;
            
            for (int ligne = 0; ligne < nombreLignes; ligne++) {
                int nombreColonnes = resultat[ligne].length;
                for (int colonne = 0; colonne < nombreColonnes; colonne++) {
                    
                    for (int i = 0; i < 8; i += 2) {
                        int ligneVoisin = ligne + directions[i];
                        int colonneVoisin = colonne + directions[i + 1];

                        if (ligneVoisin >= 0 && ligneVoisin < nombreLignes && 
                            colonneVoisin >= 0 && colonneVoisin < resultat[ligneVoisin].length) {
                            
                            if (resultat[ligne][colonne] - resultat[ligneVoisin][colonneVoisin] > 1) {
                                resultat[ligne][colonne]--;
                                resultat[ligneVoisin][colonneVoisin]++;
                                modification = true;
                            }
                        }
                    }
                    
                }
            }
        }
        return resultat;
    }
}
