import java.awt.Color;

public class SpeedBoost extends PowerUp {

    public SpeedBoost(int x, int y) {
        super(x, y, 30, 30, Color.BLUE);
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseSpeed(1.35);
        
    }
}
