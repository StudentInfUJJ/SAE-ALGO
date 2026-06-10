package iut.sae.algo.simplicite.etu98;

/**
 * Une classe pour les quatre directions.
 */
enum Directions {
    N, E, S, O;

    /**
     * Dans le sens des aiguilles d'une montre, en partant du nord.
     */
    public static Directions[] horloge() {
        return new Directions[]{N, E, S, O};
    }
}

/**
 * Une classe pour stocker le bac à sable et ses méthodes.
 */
class MonBac {

    public final int nLi;
    public final int nCo;
    public final int[][] bac;

    public MonBac(int[][] bac) {
        this.nLi = bac.length;
        if (this.nLi == 0) {
            this.nCo = 0;
        } else {
            this.nCo = bac[0].length;
        }
        this.bac = new int[this.nLi][this.nCo];
        for (int i = 0; i < this.nLi; i++) {
            for (int j = 0; j < this.nCo; j++) {
                this.bac[i][j] = bac[i][j];
            }
        }
    }

    public void effondrerBac() {
        boolean stable = false;
        while(!stable) {
            stable = true;
            for (int i = 0; i < this.nLi; i++) {
                for (int j = 0; j < this.nCo; j++) {
                    Tas tas = new Tas(i, j);
                    stable &= tas.effondrerTas();
                    // if (!stable) break;  // BREAK THE TESTS
                }
                // if (!stable) break;  // BREAK THE TESTS
            }
        }
    }

    /**
     * Une classe qui désigne une case (un tas).
     */
    class Tas {
        private final int li;
        private final int co;

        public Tas(int line, int col) {
            this.li = line;
            this.co = col;
        }

        public int sable() {
            return bac[this.li][this.co];
        }

        private Tas voisin(Directions dir) {
            Tas candidat;

            if (dir == Directions.N) candidat = new Tas(this.li - 1, this.co);
            else if (dir == Directions.S) candidat = new Tas(this.li + 1, this.co);
            else if (dir == Directions.O) candidat = new Tas(this.li, this.co - 1);
            else if (dir == Directions.E) candidat = new Tas(this.li, this.co + 1);
            else throw new IllegalArgumentException();

            if (candidat.estDansBac()) return candidat;
            else return null;
        }

        private boolean estDansBac() {
            return (0 <= this.li && this.li < nLi) && (0 <= this.co && this.co < nCo);
        }

        public boolean effondrerTas() {
            boolean stable = true;
            for (Directions d : Directions.horloge()) {
                Tas voisin = this.voisin(d);
                if (voisin == null) continue;
                if (this.sable() - 1 >= voisin.sable() + 1) {
                    this.diminuer();
                    voisin.augmenter();
                    stable = false;
                }
            }
            return stable;
        }

        private void diminuer() {
            bac[this.li][this.co]--;
        }

        private void augmenter() {
            bac[this.li][this.co]++;
        }
    }
}

public class TasDeSable {

    public static int[][] effondrer(int[][] bac) {
        MonBac monBac = new MonBac(bac);
        monBac.effondrerBac();
        return monBac.bac;
    }
}
