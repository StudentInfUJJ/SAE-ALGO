package iut.sae.algo.simplicite.etu11;

public class TasDeSable {
    /**
     * permet de faire s'effondrer un bac de sable. si une case a un nombre de grains superieur a celui d'une de ses voisines, on fait passer un grain sur la cade voisine.
     * @param bac
     * @return le bac de sable effondre
     */
    public static int[][] effondrer(int[][] bac) {
        // calcule le nombre de lignes
        int nbLignes = bac.length;
        
        //calcule le nombre de colonnes
        int nbColonnes = bac[0].length;

        boolean change = true;
        // La boucle est répétée jusqu'à ce qu'aucun changement ne soit fait
        while (change) {
            change = false;

            // les 2 for permettent de parcourir tout le schéma
            for (int indexLigne = 0; indexLigne < nbLignes; indexLigne++) {
                for (int indexColonne = 0; indexColonne < nbColonnes; indexColonne++) {
                    // Les vérifications (*if*) fonctionnent de la même manière.
                    // Si la case séléctionnée (*bac[i][j]*) ne se situe pas sur le bord concerné, 
                    // Et si son nombre de grains est supérieure ou égale à l'un de ses voisins (dessus, droite, dessous ou gauche),
                    // on déplace le grain de sable de la case séllectionnée vers la case voisine correspodante.


                    // vérification si on se trouve sur la première ligne. 
                    // Si oui, il n'y a aucune valeur au dessus de celle séléctionnée
                    // On ne fait donc pas de modifications
                    // Sinon, on on compare les valeurs
                    if (indexLigne > 0) {
                        // Comparaison entre la valeur séléctionnée et celle du dessus
                        if (bac[indexLigne][indexColonne] - bac[indexLigne-1][indexColonne] >1) {
                            //décrémentation et incrémentation pour déplacer le grain de sable
                            bac[indexLigne][indexColonne]--;
                            bac[indexLigne-1][indexColonne]++;
                            //changement efféctué: la boucle recommencera
                            change = true;
                        }
                    }
                    
                    // Vérification si on se trouve sur la dernière colonne
                    if (indexColonne < nbColonnes - 1) {
                        // Comparaison entre la valeur séléctionnée et celle de droite
                        if (bac[indexLigne][indexColonne] - bac[indexLigne][indexColonne+1] > 1) {
                            //décrémentation et incrémentation pour déplacer le grain de sable
                            bac[indexLigne][indexColonne]--;
                            bac[indexLigne][indexColonne+1]++;
                            //changement efféctué: la boucle recommencera
                            change = true;
                        }
                    }
                    
                    // Vérification si on se trouve sur la dernière ligne
                    if (indexLigne < nbLignes - 1) {
                        // Comparaison entre la valeur séléctionnée et celle du dessous
                        if (bac[indexLigne][indexColonne] - bac[indexLigne+1][indexColonne] > 1) {
                            //décrémentation et incrémentation pour déplacer le grain de sable
                            bac[indexLigne][indexColonne]--;
                            bac[indexLigne+1][indexColonne]++;
                            //changement efféctué: la boucle recommencera
                            change = true;
                        }
                    }
                    
                    // Vérification si on se trouve sur la première colonne
                    if (indexColonne > 0) {
                        // Comparaison entre la valeur séléctionnée et celle de gauche
                        if (bac[indexLigne][indexColonne] - bac[indexLigne][indexColonne-1] > 1) {
                            //décrémentation et incrémentation pour déplacer le grain de sable
                            bac[indexLigne][indexColonne]--;
                            bac[indexLigne][indexColonne-1]++;
                            //changement efféctué: la boucle recommencera
                            change = true;
                        }
                    }
                }
            }
        }
        //la fonction renvoie le bac modifié correctement
        return bac;
    }
}
