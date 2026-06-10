package iut.sae.algo;

//Ligne d'import à faire varier selon le type d'algorithme
import iut.sae.algo.sobriete.etu25.TasDeSable;

public class TasSobrieteTest extends TasDeSableTest {

    @Override
    protected int[][] effondrer(int[][] x) {
        return TasDeSable.effondrer(x);
    }
}
