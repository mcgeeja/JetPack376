import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MyComponentPlayerHitTest {
    @Test
    public void testPlayerHit() {
        //setup
        Astronaut mockPlayer = EasyMock.mock(Astronaut.class);
        List<Alien> aliensType1 = new ArrayList<>();
        List<Alien> aliensType2 = new ArrayList<>();

        aliensType1.add(EasyMock.mock(Alien.class));
        aliensType1.add(EasyMock.mock(Alien.class));
        aliensType2.add(EasyMock.mock(Alien.class));
        aliensType2.add(EasyMock.mock(Alien.class));

        List<PowerUp> powerUps = new ArrayList<>();

        Level level = EasyMock.mock(Level.class);

        AmmoCrate ammoCrate = EasyMock.mock(AmmoCrate.class);

        List<Platform> platforms = new ArrayList<>();
        platforms.add(EasyMock.mock(Platform.class));

        List<Rocket> rocketPieces = new ArrayList<>();
        rocketPieces.add(EasyMock.mock(Rocket.class));
        rocketPieces.add(EasyMock.mock(Rocket.class));
        rocketPieces.add(EasyMock.mock(Rocket.class));

        //record
        EasyMock.expect(level.getPlatforms()).andReturn(platforms).anyTimes();
        EasyMock.expect(level.getRocketPieces()).andReturn(rocketPieces).anyTimes();

        EasyMock.expect(level.getBottomRocketPiece()).andReturn(EasyMock.mock(Rocket.class)).anyTimes();

        EasyMock.expect(mockPlayer.intersects(aliensType1.get(0))).andReturn(true);
        mockPlayer.isHit();

        EasyMock.expect(mockPlayer.intersects(aliensType1.get(1))).andReturn(false);
        EasyMock.expect(aliensType1.get(1).shotPlayer(mockPlayer)).andReturn(true);
        mockPlayer.isHit();

        EasyMock.expect(mockPlayer.intersects(aliensType2.get(0))).andReturn(true);
        mockPlayer.isHit();

        EasyMock.expect(mockPlayer.intersects(aliensType2.get(1))).andReturn(false);



        //replay
        EasyMock.replay(mockPlayer, aliensType1.get(0), aliensType1.get(1), aliensType2.get(0), aliensType2.get(1), level);
        MyComponent unitUnderTest = new MyComponent(mockPlayer, aliensType1, aliensType2, level, powerUps, ammoCrate);
        unitUnderTest.playerHit();

        //verify
        EasyMock.verify(mockPlayer, aliensType1.get(0), aliensType1.get(1), aliensType2.get(0), aliensType2.get(1));

    }
}
