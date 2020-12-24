import com.languagelatte.simplechaos_java.RandomChaosAttacks;
import com.languagelatte.simplechaos_java.SimpleChaosConstants;
import org.junit.Test;

public class Test1 {

  @Test
  public void test1() {

    System.setProperty(SimpleChaosConstants.ENABLED, "true");
    System.setProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_ENABLED, "true");
    System.setProperty(SimpleChaosConstants.RANDOM_CHAOS_EXCEPTION_CHANCE, "0.1");

    RandomChaosAttacks randomChaosAttacks = new RandomChaosAttacks();

    int happy = 0;
    int sad = 0;
    for (int x = 0; x < 10000; x++) {

      try {
        randomChaosAttacks.exception();
        happy++;

      } catch (Exception e) {
        sad++;
      }
    }
    System.out.println("HAPPY " + happy);
    System.out.println("SAD " + sad);
  }
}
