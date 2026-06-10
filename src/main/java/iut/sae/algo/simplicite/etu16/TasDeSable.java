package iut.sae.algo.simplicite.etu16;

public class TasDeSable {
    
    /**
     * 
     * @param bac IN/OUT : le bac
     * @return le bac avec l'effondrement
     */
    public static int[][] effondrer(int bac[][]) throws Exception{

        if(!matriceValide(bac)){
            throw new Exception("Le bac ne doit contenir aucune valeur < 0 ");
        }

        //doubles boucle pour parcourir de gauche a droite et haut vers le bas
        for(int i = 0; i < bac.length; i++){
            for(int j = 0; j < bac[i].length; j++){
                
                //on regarde en haut si on peut s'éffondrer
                if( i != 0){
                    if(bac[i][j] - bac[i-1][j] > 1){
                        bac[i][j] -= 1; // le tas
                        bac[i-1][j] ++; // le voisinageDuhaut
                    }
                }
                
                //on regarde en a droite
                if(j != bac[i].length-1){
                    if(bac[i][j] - bac[i][j+1] > 1){
                        bac[i][j] -= 1; // le tas
                        bac[i][j+1] ++; // le voisin de droite
                    }
                }

                //on regarde en bas
                if(i != bac.length-1){
                    if(bac[i][j] - bac[i+1][j] > 1){
                        bac[i][j] -= 1; // le tas
                        bac[i+1][j] ++; // le voisin du bas
                    }
                }

                //on regarde a gauche
                if(j != 0){
                    if(bac[i][j] - bac[i][j-1] > 1){
                        bac[i][j] -= 1; // le tas
                        bac[i][j-1] ++; // le voisin de gauche
                    }
                }

                
                
            }
        }
        
        
        return bac;
    }

    /**Permet d'obtenir une chaine de car qui représente notre bac
     * 
     * @param bac IN : bac
     * @return String qui représente le bac
     */
    public static String stringBac(int bac[][]){
        String stringBac = "";

        for(int i = 0; i < bac.length; i++){
            for(int j = 0; j < bac[i].length; j++){
                //valeur de la case en cours
                stringBac += bac[i][j];
                // 2 espace
                stringBac += "  ";
            }
            //retour à la ligne
            stringBac += "\n";
        }

        return stringBac;
    }

    /**Permet de copié les valeurs d'un tableau dans un autre
     * 
     * @param tab1 IN : tableau qui se fait copier
     * @param tab2 OUT : tableau qui copie
     * @throws Exception si les tableaux n'ont pas le meme nombre de colonnes et/ou de lignes
     */
    public static void copieTab(int tab1[][], int tab2[][]) throws Exception{
        if(tab1.length != tab2.length || tab1[0].length != tab2[0].length){
            throw new Exception("Les tableaux doivent être de la même longueur");
        }
        else{
            for (int i = 0;i < tab1.length; i++) {
                for (int j = 0;j < tab1[i].length;j++) {
                    tab2[i][j] = tab1[i][j];
                }
            }
        }
    }

    /**Permet de comparer deux tableaux et vérifier si ils ont les memes valeurs
     * 
     * @param tab1 IN : tableau 1
     * @param tab2 IN : tableau 2
     * @return vrai si ils ont les memes valeurs sinon faux
     * @throws Exception si les tableaux n'ont pas le meme nombre de colonnes et/ou de lignes
     */
    public static boolean isPareil(int tab1[][], int tab2[][]) throws Exception{
        if(tab1.length != tab2.length || tab1[0].length != tab2[0].length){
            throw new Exception("Les tableaux doivent être de la même longueur");
        }
        else{
            for (int i = 0;i < tab1.length; i++) {
                for (int j = 0;j < tab1[i].length;j++) {
                    if(tab2[i][j] != tab1[i][j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Vérifie que la matrice du bac n'a pas de valeur négative
     * 
     * @param matrice : la matrice
     * @return true si la matrice est valide sinon false
     */
    public static boolean matriceValide(int[][] matrice){
        for(int i = 0; i < matrice.length; i++){
            for(int j = 0; j < matrice[i].length; j++){
                if(matrice[i][j] < 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    /** Programme principal
     *  - crée un tableau 2d qui représente le bac et un tableau temporaire qui servira pour les comparaison des changements
     *  - boucle jusqu'a ce qu'il y'est plus de changement dans le tableau
     *      - copier le tableau du bac dans le tableau temporaire
     *      - fait effondrer le bac
     *      - affiche le bac
     * 
     * @param args
     */
    public static void main(String[] args) {
        try{
            int bac[][] = {{0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0},     
                            {0, 0, 12, 0, 0},
                            {0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0}};
            
            int tempBac[][] = new int[bac.length][bac[0].length]; //bac temporaire pour optimiser l'affichage

            //1ere affichage
            System.out.println(stringBac(bac));

            //boucle qui répète jusqu'à ce que isPareil deviens vrai si les tableaux sont apreils
            while(!isPareil(bac, tempBac)){
                copieTab(bac, tempBac);
                effondrer(bac);
                //le if permet de ne pas afficher le tableau une fois de trop
                if(!isPareil(bac, tempBac)){
                    System.out.println(stringBac(bac));
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
}
