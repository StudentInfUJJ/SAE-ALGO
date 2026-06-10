package iut.sae.algo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class TestTousLesEtudiants extends TasDeSableTest {

    // === FILTRES DE TEST ===
    // Modifiez ces constantes pour filtrer les tests à exécuter
    // Exemples :
    // - pour tester uniquement l'efficacité : private static final String
    // FILTER_CATEGORIE = "efficacite";
    // - pour tester uniquement l'étudiant 25 : private static final String
    // FILTER_ETUDIANT = "etu25";
    private static final String FILTER_CATEGORIE = "efficacite"; // null pour toutes les catégories
    private static final String FILTER_ETUDIANT = "etu6"; // null pour tous les étudiants

    private final String info;
    private final Method effondrerMethod;

    public TestTousLesEtudiants(String info, Method effondrerMethod) {
        super();
        this.info = info;
        this.effondrerMethod = effondrerMethod;
    }

    @Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        File baseDir = new File("src/main/java/iut/sae/algo");

        // Fallback si exécuté depuis un autre dossier de travail
        if (!baseDir.exists()) {
            baseDir = new File("../src/main/java/iut/sae/algo");
        }

        if (baseDir.exists() && baseDir.isDirectory()) {
            File[] categories = baseDir.listFiles();
            if (categories != null) {
                for (File catDir : categories) {
                    if (catDir.isDirectory() && !catDir.getName().startsWith(".")) {
                        String category = catDir.getName();

                        // Application du filtre de catégorie
                        if (FILTER_CATEGORIE != null && !FILTER_CATEGORIE.equalsIgnoreCase(category)) {
                            continue;
                        }

                        File[] etudiants = catDir.listFiles();
                        if (etudiants != null) {
                            for (File etuDir : etudiants) {
                                if (etuDir.isDirectory() && !etuDir.getName().startsWith(".")) {
                                    String etu = etuDir.getName();

                                    // Application du filtre d'étudiant
                                    if (FILTER_ETUDIANT != null && !FILTER_ETUDIANT.equalsIgnoreCase(etu)) {
                                        continue;
                                    }

                                    String className = "iut.sae.algo." + category + "." + etu + ".TasDeSable";
                                    try {
                                        Class<?> clazz = Class.forName(className);
                                        Method method = clazz.getMethod("effondrer", int[][].class);
                                        data.add(new Object[] { category + " / " + etu, method });
                                    } catch (ClassNotFoundException e) {
                                        System.err.println("Classe non trouvée pour : " + className);
                                    } catch (NoSuchMethodException e) {
                                        System.err
                                                .println("Méthode effondrer non trouvée dans la classe : " + className);
                                    } catch (Exception e) {
                                        System.err.println("Erreur lors du chargement de la classe : " + className
                                                + " -> " + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.err.println("Dossier des algorithmes introuvable à l'adresse : " + baseDir.getAbsolutePath());
        }

        // Si aucune classe n'est trouvée, on ajoute un élément vide
        if (data.isEmpty()) {
            System.err.println("Aucun algorithme d'étudiant n'a été trouvé avec les filtres actuels.");
        }

        return data;
    }

    @Override
    protected int[][] effondrer(int[][] x) {
        if (effondrerMethod == null) {
            fail("Aucune méthode effondrer disponible.");
        }
        try {
            return (int[][]) effondrerMethod.invoke(null, (Object) x);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof AssertionError) {
                throw (AssertionError) cause;
            }
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            }
            if (cause instanceof Error) {
                throw (Error) cause;
            }
            throw new RuntimeException("Erreur d'exécution de effondrer pour " + info, cause);
        }
    }

    public static void main(String[] args) {
        int nbRuns = 500; // Nombre d'exécutions de la liste de tests
        System.out.println("=== MESURE DE LA DURÉE DE LA LISTE DE TESTS ===");
        System.out.println("Lancement de la suite de tests " + nbRuns + " fois...");

        List<Double> durations = new ArrayList<>();
        int failures = 0;

        for (int i = 0; i < nbRuns; i++) {
            long start = System.nanoTime();
            org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(TestTousLesEtudiants.class);
            long duration = System.nanoTime() - start;
            double durationMs = duration / 1_000_000.0;
            durations.add(durationMs);

            if (!result.wasSuccessful()) {
                failures++;
            }

            // Évite d'inonder la console : affiche la progression tous les 50 passages
            if ((i + 1) % 50 == 0 || i < 5) {
                System.out.printf("  Progression : %d/%d passages effectués...\n", (i + 1), nbRuns);
            }
        }

        // Tri des durées pour pouvoir filtrer les valeurs aberrantes (Garbage
        // Collector, warmup)
        java.util.Collections.sort(durations);

        // 1. Moyenne brute (inclut toutes les pauses GC)
        double totalSum = 0;
        for (double d : durations) {
            totalSum += d;
        }
        double avgTotal = totalSum / nbRuns;

        // 2. Moyenne stable (on retire les 10% de runs les plus lents - ex: les pauses
        // GC et le warmup de départ)
        int indexCut = (int) (nbRuns * 0.90);
        double stableSum = 0;
        for (int i = 0; i < indexCut; i++) {
            stableSum += durations.get(i);
        }
        double avgStable = stableSum / indexCut;

        // 3. Médiane
        double median = durations.get(nbRuns / 2);

        System.out.println("\n==================================================");
        System.out.println("            RÉSULTATS DE L'ÉVALUATION             ");
        System.out.println("==================================================");
        System.out.printf("  Total passages    : %d (%s)\n", nbRuns,
                failures == 0 ? "Tous réussis" : failures + " échecs");
        System.out.printf("  Moyenne brute     : %.2f ms  (inclut les pauses GC)\n", avgTotal);
        System.out.printf("  Médiane           : %.2f ms\n", median);
        System.out.printf("  Moyenne stable    : %.2f ms  (recommandée - sans aberrations)\n", avgStable);
        System.out.println("==================================================");
    }

    private static int[][] copier(int[][] original) {
        int[][] copie = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copie[i] = original[i].clone();
        }
        return copie;
    }
}
