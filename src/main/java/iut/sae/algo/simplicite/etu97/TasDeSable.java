package iut.sae.algo.simplicite.etu97;

public class TasDeSable {

    /**
    * Méthode simulant l'effondrement d'un tas de sable dans un bac (matrice d'entiers)
    * La lecture principale se fait de gauche à droite puis de haut en bas
    * Si un grain de sable a plus de 1 de hauteur d'écart avec son voisin, un grain glisse à côté
    * (les glissements se font dans le sens horaire depuis la case du haut).
    * Tant que le bac n'est pas stable, on recommence
    * 
    * @param bac tableau d'entier à 2 dimensions représentant le bac à effondrer
    * ATTENTION : Dans un but d'optimisation, le tableau d'entrée est modifié !
    * @return le tableau d'entier à 2 dimensions représentant le bac après effondrement
    */
    public static int[][] effondrer(int bac[][]) {
        boolean stable;
        do{ //Nécessite une première itération pour vérifier l'état du bac
            stable=true;

            // On parcourt le bac dans le sens de lecture européen
            for(int y=0; y<bac.length; y++){
                for(int x=0; x<bac[0].length; x++){
                    // Vérification si le tas est instable par rapport aux 4 points cardinaux.
                    // Càd : La case en cours est plus haute d'au moins 2 grains que sa voisine étudiée

                    // Nord
                    if(y>0 && bac[y][x]>bac[y-1][x]+1){
                        bac[y][x]--;// Un grain glisse...
                        bac[y-1][x]++;// ... vers son voisin
                        stable=false;// Et on devra relancer une itération complète
                    }

                    //Est
                    if(x<bac[0].length-1 && bac[y][x]>bac[y][x+1]+1){
                        bac[y][x]--;
                        bac[y][x+1]++;
                        stable=false;
                    }

                    //Sud
                    if(y<bac.length-1 && bac[y][x]>bac[y+1][x]+1){
                        bac[y][x]--;
                        bac[y+1][x]++;
                        stable=false;
                    }

                    //Ouest
                    if(x>0 && bac[y][x]>bac[y][x-1]+1){
                        bac[y][x]--;
                        bac[y][x-1]++;
                        stable=false;
                    }
                }
            }
        } while(!stable);

        return bac;
    }
}
