import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

public class LevelTest {
    void compareGameObjects(GameObject g1, GameObject g2) {
        Assertions.assertEquals(g1.height, g2.height);
        Assertions.assertEquals(g1.width, g2.width);
        Assertions.assertEquals(g1.x, g2.x);
        Assertions.assertEquals(g1.y, g2.y);
    }

    @Test
    public void TestLoadLevel() {
        ArrayList<String> level1String = new ArrayList<>(Arrays.asList(
                "1.............T.....",
                "..........._f__.....",
                "....................",
                "..........._f_......",
                ".._f_...............",
                "....................",
                ".......M............",
                "..........___.......",
                ".................B..",
                "--------------------"
        ));
        Level levelFromString = new Level();
        levelFromString.convertLevelToText(level1String);

        Level levelFromFile = new Level(1);

        Assertions.assertEquals(levelFromString.platforms.size(), levelFromFile.platforms.size());
        Assertions.assertEquals(levelFromString.fuels.size(), levelFromFile.fuels.size());
        Assertions.assertEquals(levelFromString.rocketPieces.size(), levelFromFile.rocketPieces.size());
        for (int i = 0; i < levelFromString.platforms.size(); i++) {
            Platform fromFile = levelFromFile.platforms.get(i);
            Platform fromText = levelFromFile.platforms.get(i);
            compareGameObjects(fromText, fromFile);
        }
        for (int i = 0; i < levelFromString.fuels.size(); i++) {
            Fuel fromFile = levelFromFile.fuels.get(i);
            Fuel fromText = levelFromFile.fuels.get(i);
            compareGameObjects(fromText, fromFile);
        }
        for (int i = 0; i < levelFromString.fuels.size(); i++) {
            Rocket fromFile = levelFromFile.rocketPieces.get(i);
            Rocket fromText = levelFromFile.rocketPieces.get(i);
            compareGameObjects(fromText, fromFile);
            Assertions.assertEquals(fromText.getClass(), fromFile.getClass());
        }
    }
}
