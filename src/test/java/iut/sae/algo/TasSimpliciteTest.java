package iut.sae.algo;

//Ligne d'import à faire varier selon le type d'algorithme
import iut.sae.algo.simplicite.TasDeSable;

public class TasSimpliciteTest extends TasDeSableTest{

    @Override
    protected int[][] effondrer(int[][] x) {
        return TasDeSable.effondrer(x);
    }
}
