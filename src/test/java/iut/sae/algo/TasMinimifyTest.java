package iut.sae.algo;

//Ligne d'import à faire varier selon le type d'algorithme
import iut.sae.algo.mini.TasDeSable;

public class TasMinimifyTest extends TasDeSableTest{

    @Override
    protected int[][] effondrer(int[][] x) {
        return TasDeSable.effondrer(x);
    }
    

}
