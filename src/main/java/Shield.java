import java.awt.Color;

public class Shield extends PowerUp {

    public Shield(int x, int y) {
        super(x, y, 30, 30, Color.RED);
    }

    @Override
    public void applyEffect(Player player) {
        player.activateShield();
    }
}
