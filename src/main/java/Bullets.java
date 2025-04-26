import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class Bullets extends GameObject{
	private int xVelocity;
	private double size;
	private Color bulletcolor;
	private Sound sound = new Sound(new File("../resources/sounds/bullet-sound.wav"));

	public Bullets(int startx, int starty, Color bulletcolor, int size) {
		super(startx, starty, size, size);
		this.xVelocity = 25;
		this.bulletcolor = bulletcolor;
		this.size = size;
		sound.playSoundOneShot();
	}

	public boolean move(int boundary) {
		this.x = this.x + this.xVelocity;
		return this.x > boundary;
	}

	public boolean moveLeft(int boundary) {
		this.x = this.x - this.xVelocity;
		return this.x < boundary;
	}

	public void drawOn(Graphics2D g) {
		g.setColor(this.bulletcolor);
		Rectangle2D.Double bullet = new Rectangle2D.Double(this.x, this.y, this.size, this.size);
		g.fill(bullet);
	}

	@Override
	public void leftEdgeHit() {

	}

	@Override
	public void rightEdgeHit() {

	}

	@Override
	public void topEdgeHit() {

	}

	@Override
	public void bottomEdgeHit() {

	}
}
