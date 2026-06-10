package iut.sae.algo;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import junit.framework.TestCase;

//Ligne d'import à faire varier selon le type d'algorithme
//import iut.sae.algo.efficacite.TasDeSable;

public class TasDeSableTest extends TestCase {

        @Test
        public void testBacNul() {
                int[][] bac = { {} };
                int[][] objectif = { {} };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testBacVide() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testBacTropPetit() {
                int[][] bac = {
                                { 5 } };
                int[][] objectif = {
                                { 5 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testBacPlat() {
                int[][] bac = {
                                { 5, 5, 5 },
                                { 5, 5, 5 },
                                { 5, 5, 5 } };
                int[][] objectif = {
                                { 5, 5, 5 },
                                { 5, 5, 5 },
                                { 5, 5, 5 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasSimple() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 5, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0 },
                                { 1, 1, 1 },
                                { 0, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasSimpleStable() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 1, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 0, 0 },
                                { 0, 1, 0 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTrou() {
                int[][] bac = {
                                { 5, 5, 5 },
                                { 5, 4, 5 },
                                { 5, 6, 5 } };
                int[][] objectif = {
                                { 5, 5, 5 },
                                { 5, 5, 5 },
                                { 5, 5, 5 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testPartiel1() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 2, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0 },
                                { 0, 1, 0 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testPartiel2() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 3, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0 },
                                { 0, 1, 1 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testPartiel3() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 4, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0 },
                                { 0, 1, 1 },
                                { 0, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin1HG() {
                int[][] bac = {
                                { 1, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin1HD() {
                int[][] bac = {
                                { 0, 0, 1 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 0, 1 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin1BG() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 1, 0, 0 } };
                int[][] objectif = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 1, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin1BD() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 1 } };
                int[][] objectif = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin10HG() {
                int[][] bac = {
                                { 10, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 2, 2, 1 },
                                { 2, 1, 1 },
                                { 1, 0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin10HD() {
                int[][] bac = {
                                { 0, 0, 10 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 2, 2 },
                                { 0, 1, 2 },
                                { 0, 1, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin10BG() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 10, 0, 0 } };
                int[][] objectif = {
                                { 1, 1, 0 },
                                { 2, 1, 0 },
                                { 2, 2, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasCoin10BD() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 10 } };
                int[][] objectif = {
                                { 0, 1, 1 },
                                { 0, 1, 2 },
                                { 1, 2, 2 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasLigneH() {
                int[][] bac = {
                                { 4, 4, 4 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 2, 2, 2 },
                                { 1, 1, 1 },
                                { 1, 1, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasLigneB() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 4, 4, 4 } };
                int[][] objectif = {
                                { 1, 1, 1 },
                                { 1, 1, 1 },
                                { 2, 2, 2 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testTasLigneC() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 4, 4, 4 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 1, 1 },
                                { 2, 2, 2 },
                                { 1, 1, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testDoubleTas5() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0 },
                                { 0, 5, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 5, 0 },
                                { 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0, 0, 0 },
                                { 1, 1, 1, 0, 0 },
                                { 0, 1, 0, 1, 0 },
                                { 0, 0, 1, 1, 1 },
                                { 0, 0, 0, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testDoubleTas10() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0 },
                                { 0, 10, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 10, 0 },
                                { 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 1, 1, 0, 0 },
                                { 1, 2, 1, 1, 0 },
                                { 1, 1, 1, 1, 1 },
                                { 0, 0, 1, 2, 1 },
                                { 0, 0, 1, 1, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void test500() {
                int[][] bac = new int[11][11];
                bac[5][5] = 500;
                int[][] objectif = {
                                { 0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 1 },
                                { 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1 },
                                { 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2 },
                                { 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3 },
                                { 4, 5, 6, 7, 8, 8, 8, 7, 6, 5, 4 },
                                { 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4 },
                                { 3, 4, 5, 6, 7, 8, 7, 6, 5, 5, 4 },
                                { 2, 3, 4, 5, 6, 7, 6, 5, 4, 4, 3 },
                                { 1, 2, 3, 4, 5, 6, 5, 4, 4, 3, 2 },
                                { 0, 1, 2, 3, 4, 5, 4, 3, 3, 2, 1 },
                                { 0, 1, 2, 2, 3, 4, 3, 2, 2, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        @Test
        public void testIUT() {
                int[][] bac = {
                                { 0, 9, 0, 9, 0, 9, 9, 9, 9 },
                                { 0, 9, 0, 9, 0, 9, 0, 9, 0 },
                                { 0, 9, 0, 9, 9, 9, 0, 9, 0 } };
                int[][] objectif = {
                                { 3, 4, 5, 5, 6, 6, 6, 6, 6 },
                                { 3, 4, 5, 5, 6, 6, 6, 6, 5 },
                                { 3, 4, 4, 5, 6, 6, 5, 5, 4 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // ===================================================================
        // CAS AVEC UN SEUL TAS
        // Vérifient la propagation complète d'un unique tas (cascade jusqu'à
        // stabilité) dans des grilles non carrées et en bordure : pièges
        // classiques des algos qui ne bouclent pas assez ou supposent une
        // grille carrée.
        // ===================================================================

        // Tas unique dans une grille large (3x7) : oblige une cascade
        // horizontale. Piège les algos qui confondent lignes et colonnes.
        @Test
        public void testUnTasRectangleLarge() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 30, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 2, 3, 2, 1, 0 },
                                { 1, 1, 2, 3, 2, 2, 1 },
                                { 0, 1, 2, 3, 2, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Tas unique dans une grille haute (7x3) : cascade verticale.
        @Test
        public void testUnTasRectangleHaut() {
                int[][] bac = {
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 30, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 },
                                { 0, 0, 0 } };
                int[][] objectif = {
                                { 0, 1, 0 },
                                { 1, 2, 1 },
                                { 2, 3, 2 },
                                { 2, 3, 3 },
                                { 1, 2, 2 },
                                { 1, 2, 1 },
                                { 0, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Tas unique dans le coin bas-droite : le sable doit remonter vers le
        // haut et la gauche. Piège les algos "efficaces" dont la zone de
        // recherche ne s'étend que vers le bas/la droite.
        @Test
        public void testUnTasCoinBasDroite() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 30 } };
                int[][] objectif = {
                                { 0, 0, 0, 0, 1 },
                                { 0, 0, 0, 1, 2 },
                                { 0, 0, 1, 2, 3 },
                                { 0, 1, 2, 3, 3 },
                                { 1, 1, 2, 3, 4 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Tas unique dans une grille à une seule ligne (1x7).
        @Test
        public void testUnTasUneLigne() {
                int[][] bac = {
                                { 0, 0, 0, 16, 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 2, 3, 4, 3, 2, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Tas unique dans une grille à une seule colonne (7x1).
        @Test
        public void testUnTasUneColonne() {
                int[][] bac = {
                                { 0 },
                                { 0 },
                                { 0 },
                                { 16 },
                                { 0 },
                                { 0 },
                                { 0 } };
                int[][] objectif = {
                                { 1 },
                                { 2 },
                                { 3 },
                                { 4 },
                                { 3 },
                                { 2 },
                                { 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // ===================================================================
        // CAS AVEC PLUSIEURS TAS
        // Vérifient l'interaction entre plusieurs tas dont les avalanches se
        // rencontrent. Piège les algos qui traitent les tas isolément ou qui
        // s'arrêtent dès qu'une zone semble stable.
        // ===================================================================

        // Deux tas collés : leurs grains se redistribuent ensemble.
        @Test
        public void testDeuxTasAdjacents() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0 },
                                { 0, 8, 8, 0, 0 },
                                { 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 1, 2, 1, 0 },
                                { 2, 2, 2, 1, 0 },
                                { 1, 1, 1, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Deux gros tas aux extrémités d'une grille large : leurs avalanches
        // se rejoignent au centre.
        @Test
        public void testDeuxTasQuiSeRencontrent() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 20, 0, 0, 0, 0, 0, 0, 0, 20 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 3, 2, 1, 1, 0, 0, 1, 2, 3 },
                                { 3, 2, 2, 1, 0, 1, 2, 2, 3 },
                                { 2, 2, 1, 0, 0, 0, 1, 2, 3 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Quatre tas, un dans chaque coin.
        @Test
        public void testQuatreTasCoins() {
                int[][] bac = {
                                { 12, 0, 0, 0, 12 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0 },
                                { 12, 0, 0, 0, 12 } };
                int[][] objectif = {
                                { 3, 2, 2, 2, 3 },
                                { 2, 1, 1, 2, 2 },
                                { 2, 1, 0, 1, 2 },
                                { 2, 2, 1, 2, 3 },
                                { 3, 2, 2, 2, 3 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Deux tas de hauteurs différentes : le plus gros déborde sur le plus
        // petit.
        @Test
        public void testDeuxTasInegaux() {
                int[][] bac = {
                                { 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 15, 0, 0, 0, 5, 0 },
                                { 0, 0, 0, 0, 0, 0, 0 } };
                int[][] objectif = {
                                { 1, 2, 1, 0, 0, 1, 0 },
                                { 2, 2, 2, 1, 1, 1, 1 },
                                { 1, 2, 1, 0, 0, 1, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // Trois tas alignés sur une seule ligne : avalanches qui se touchent.
        @Test
        public void testTroisTasAlignes() {
                int[][] bac = {
                                { 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0 } };
                int[][] objectif = {
                                { 1, 2, 3, 3, 3, 3, 3, 3, 3, 2, 1 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        // ===================================================================
        // CAS DE CHARGE (grosse grille)
        // Un très gros tas (2000) au centre d'une grille 31x31 : ~14500
        // transferts de grains, soit environ 10x le travail de test500.
        // Stresse les algos lents et vérifie qu'ils bouclent bien jusqu'au
        // bout sur une grande surface. Le tas est entièrement contenu (aucun
        // grain n'atteint les bords).
        // ===================================================================
        @Test
        public void testGrosTas2000() {
                int[][] bac = new int[31][31];
                bac[15][15] = 2000;
                int[][] objectif = {
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0,
                                                0, 0, 0 },
                                { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0,
                                                0, 0, 0 },
                                { 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
                                                0, 0, 0, 0 },
                                { 0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
                                                0, 0, 0, 0 },
                                { 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3,
                                                2, 1, 0, 0, 0 },
                                { 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4,
                                                3, 2, 1, 0, 0 },
                                { 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 12, 11, 10, 9, 8, 7, 6, 5, 5, 4,
                                                3, 2, 1, 0, 0 },
                                { 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 11, 10, 9, 8, 7, 6, 5, 4, 4, 3, 2,
                                                1, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 9, 8, 7, 6, 5, 4, 4, 3, 2, 1, 0,
                                                0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 3, 2, 1, 0, 0,
                                                0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 2, 1, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                                0, 0 } };
                assertBacEquals(objectif, effondrer(bac));
        }

        protected int[][] effondrer(int[][] x) {
                return null;
        }

        public static void afficherBac(int[][] bac) {
                for (int[] ligne : bac) {
                        for (int valeur : ligne) {
                                System.out.print(valeur + " ");
                        }
                        System.out.println();
                }
        }

        private void assertBacEquals(int[][] expecteds, int[][] actuals) {
                try {
                        assertArrayEquals(expecteds, actuals);
                } catch (AssertionError e) {
                        System.out.println("=== ATTENDU ===");
                        afficherBac(expecteds);
                        System.out.println("=== OBTENU  ===");
                        afficherBac(actuals);
                        throw e;
                }
        }
}