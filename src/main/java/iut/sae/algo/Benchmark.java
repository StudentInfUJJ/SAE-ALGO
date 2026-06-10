package iut.sae.algo;

public class Benchmark {

    public static void main(String[] args) {
        // Définir une grille de test représentative (ex: 7x7 avec une pile de 200)
        int[][] bacOrigine = new int[200][200];
        bacOrigine[50][50] = 50;

        int nbWarmup = 10000; // Nombre d'exécutions d'échauffement
        int nbMesures = 50000; // Nombre d'exécutions mesurées

        // 1. ÉCHAUFFEMENT
        System.out.println("Échauffement de la JVM...");
        for (int i = 0; i < nbWarmup; i++) {
            iut.sae.algo.simplicite.TasDeSable.effondrer(copier(bacOrigine));
            iut.sae.algo.efficacite.TasDeSable.effondrer(copier(bacOrigine));
        }

        // 2. MESURE SIMPLICITÉ
        System.out.println("Mesure de la version Simple...");
        long debutSimple = System.nanoTime();
        for (int i = 0; i < nbMesures; i++) {
            iut.sae.algo.simplicite.TasDeSable.effondrer(copier(bacOrigine));
        }
        long finSimple = System.nanoTime();

        // 3. MESURE EFFICACITÉ
        System.out.println("Mesure de la version Efficace...");
        long debutEfficace = System.nanoTime();
        for (int i = 0; i < nbMesures; i++) {
            iut.sae.algo.efficacite.TasDeSable.effondrer(copier(bacOrigine));
        }
        long finEfficace = System.nanoTime();

        // 4. MESURE Sobriete
        System.out.println("Mesure de la version Sobriete...");
        long debutSobriete = System.nanoTime();
        for (int i = 0; i < nbMesures; i++) {
            iut.sae.algo.sobriete.TasDeSable.effondrer(copier(bacOrigine));
        }
        long finSobriete = System.nanoTime();

        // 4. AFFICHAGE DES RÉSULTATS MOYENS
        double tempsSimpleMicro = (double) (finSimple - debutSimple) / nbMesures / 1000.0;
        double tempsEfficaceMicro = (double) (finEfficace - debutEfficace) / nbMesures / 1000.0;
        double tempsSobreMicro = (double) (finSobriete - debutSobriete) / nbMesures / 1000.0;

        System.out.println("\n=== RÉSULTATS (Temps moyen par exécution) ===");
        System.out.printf("Version simple   : %.3f µs\n", tempsSimpleMicro);
        System.out.printf("Version efficace   : %.3f µs\n", tempsEfficaceMicro);
        System.out.printf("Version Sobre : %.3f µs\n", tempsSobreMicro);
        System.out.printf("Gain de vitesse  : %.1f%%\n",
                (tempsEfficaceMicro - tempsSobreMicro) / tempsEfficaceMicro * 100.0);
    }

    // Helper pour cloner profondément le tableau 2D à chaque itération
    private static int[][] copier(int[][] original) {
        int[][] copie = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copie[i] = original[i].clone();
        }
        return copie;
    }
}
