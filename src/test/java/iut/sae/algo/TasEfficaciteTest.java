package iut.sae.algo;

//Ligne d'import à faire varier selon le type d'algorithme
import iut.sae.algo.efficacite.etu6.TasDeSable;

public class TasEfficaciteTest extends TasDeSableTest {

    @Override
    protected int[][] effondrer(int[][] x) {
        return TasDeSable.effondrer(x);
    }

}
