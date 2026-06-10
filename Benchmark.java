package iut.sae.algo;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Benchmark {

    // === CONFIGURATION ===
    private static final int NB_WARMUP = 1000;    // Nombre d'exécutions d'échauffement par algo
    private static final int NB_MESURES = 5000;   // Nombre d'exécutions mesurées par algo

    private static class AlgoResult {
        String name;
        String category;
        double avgTimeMicro;

        public AlgoResult(String category, String name, double avgTimeMicro) {
            this.category = category;
            this.name = name;
            this.avgTimeMicro = avgTimeMicro;
        }
    }

    public static void main(String[] args) {
        // Définir une grille de test représentative (200x200 avec 50 à [50][50])
        int[][] bacOrigine = new int[200][200];
        bacOrigine[50][50] = 50;

        List<Object[]> algos = scanAlgos();
        if (algos.isEmpty()) {
            System.err.println("Aucun algorithme trouvé à benchmarker !");
            return;
        }

        System.out.println("Début du Benchmark (" + algos.size() + " algorithmes détectés)...");
        System.out.println("Grille de test : 200x200 avec une pile de 50.");
        System.out.println("Warmup : " + NB_WARMUP + " itérations | Mesures : " + NB_MESURES + " itérations\n");

        List<AlgoResult> results = new ArrayList<>();

        for (Object[] algo : algos) {
            String category = (String) algo[0];
            String etu = (String) algo[1];
            Method method = (Method) algo[2];

            System.out.println("Analyse de " + category + " / " + etu + "...");

            try {
                // 1. ÉCHAUFFEMENT
                for (int i = 0; i < NB_WARMUP; i++) {
                    method.invoke(null, (Object) copier(bacOrigine));
                }

                // 2. MESURE
                long debut = System.nanoTime();
                for (int i = 0; i < NB_MESURES; i++) {
                    method.invoke(null, (Object) copier(bacOrigine));
                }
                long fin = System.nanoTime();

                double tempsMoyenMicro = (double) (fin - debut) / NB_MESURES / 1000.0;
                results.add(new AlgoResult(category, etu, tempsMoyenMicro));

            } catch (Exception e) {
                System.err.println("   [ERREUR] Impossible d'exécuter l'algorithme de " + etu + " : " + e.getMessage());
            }
        }

        // Tri des résultats par temps d'exécution croissant (du plus rapide au plus lent)
        Collections.sort(results, new Comparator<AlgoResult>() {
            @Override
            public int compare(AlgoResult r1, AlgoResult r2) {
                return Double.compare(r1.avgTimeMicro, r2.avgTimeMicro);
            }
        });

        // 3. AFFICHAGE DU CLASSEMENT GENERAL
        System.out.println("\n=====================================================================");
        System.out.println("                      CLASSEMENT DES ALGORITHMES                      ");
        System.out.println("=====================================================================");
        System.out.printf("%-4s | %-12s | %-12s | %-18s\n", "Rang", "Catégorie", "Étudiant", "Temps Moyen (µs)");
        System.out.println("---------------------------------------------------------------------");
        
        int rank = 1;
        for (AlgoResult res : results) {
            System.out.printf("%-4d | %-12s | %-12s | %-18.3f\n", rank++, res.category, res.name, res.avgTimeMicro);
        }
        System.out.println("=====================================================================");
    }

    private static List<Object[]> scanAlgos() {
        List<Object[]> data = new ArrayList<>();
        File baseDir = new File("src/main/java/iut/sae/algo");
        if (!baseDir.exists()) {
            baseDir = new File("../src/main/java/iut/sae/algo");
        }

        if (baseDir.exists() && baseDir.isDirectory()) {
            File[] categories = baseDir.listFiles();
            if (categories != null) {
                for (File catDir : categories) {
                    if (catDir.isDirectory() && !catDir.getName().startsWith(".")) {
                        String category = catDir.getName();
                        File[] etudiants = catDir.listFiles();
                        if (etudiants != null) {
                            for (File etuDir : etudiants) {
                                if (etuDir.isDirectory() && !etuDir.getName().startsWith(".")) {
                                    String etu = etuDir.getName();
                                    String className = "iut.sae.algo." + category + "." + etu + ".TasDeSable";
                                    try {
                                        Class<?> clazz = Class.forName(className);
                                        Method method = clazz.getMethod("effondrer", int[][].class);
                                        data.add(new Object[]{category, etu, method});
                                    } catch (Exception e) {
                                        // Ignorer les classes invalides ou avec erreurs
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return data;
    }

    private static int[][] copier(int[][] original) {
        int[][] copie = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copie[i] = original[i].clone();
        }
        return copie;
    }
}
