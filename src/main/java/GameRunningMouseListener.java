import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class GameRunningMouseListener extends MouseInputAdapter {
    Player player;

    public GameRunningMouseListener(Player player) {
        this.player = player;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.shoot();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.setDirectionToFace(e.getX());
    }
}
