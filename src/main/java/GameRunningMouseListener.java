import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class GameRunningMouseListener implements MouseInputListener {
    Player player;

    public GameRunningMouseListener(Player player) {
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.shoot();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.setDirectionToFace(e.getX());
    }
}
