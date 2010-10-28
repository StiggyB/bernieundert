/**
 * @author Bernie und Ert
 */
package a04;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BildPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) {
		final BildRotation hitAction = new BildRotation();
		hitAction.bildlaufenlassen(500,500);
	}

	private Image katzenImage = new ImageIcon("src\\a04\\JPG\\01.jpg").getImage();
	private boolean position = false;
	private Image bernieImage = new ImageIcon("src\\a04\\JPG\\0.jpg").getImage();
	private boolean bildZweiVorhanden = false;
	private int xKoordinate = 0;
	private int xKoordinateZwei;
	private int yKoordinate = 0;
	private int yKoordinateZwei;

	public BildPanel(final int xKoordinate, final int yKoordinate) {
		super();
		this.setxKatze(xKoordinate);
		this.setyKatze(yKoordinate);
	}

	public BildPanel(final int x, final int y,
			final boolean bildZweiVorhanden, final int xZwei,
			final int yZwei) {
		super();
		this.setxKatze(x);
		this.setyKatze(y);
		this.bildZweiVorhanden = bildZweiVorhanden;
		this.setxBernie(xZwei);
		this.setyBernie(yZwei);
	}

	public Image getImage() {
		return this.katzenImage;
	}

	public Image getImageZwei() {
		return this.bernieImage;
	}

	public int getxKoordinate() {
		return this.xKoordinate;
	}

	public int getxBernie() {
		return this.xKoordinateZwei;
	}

	public int getyKoordinate() {
		return this.yKoordinate;
	}

	public int getyBernie() {
		return this.yKoordinateZwei;
	}

	public boolean isPosition() {
		return this.position;
	}

	@Override
	public void paintComponent(final Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (this.bildZweiVorhanden && this.isPosition()) {
			g.drawImage(this.getImageZwei(), this.getxBernie(),
					this.getyBernie(), this);
		}
		g.drawImage(this.getImage(), this.getxKoordinate(), this.getyKoordinate(), this);
		if (this.bildZweiVorhanden && (this.isPosition() == false)) {
			g.drawImage(this.getImageZwei(), this.getxBernie(),
					this.getyBernie(), this);
		}

	}

	public void setImage(final Image image) {
		this.katzenImage = image;
	}

	public void setPosition(final boolean position) {
		this.position = position;
	}

	public void setImageZwei(final Image secondImage) {
		this.bernieImage = secondImage;
	}

	public void setxKatze(final int xCoord) {
		this.xKoordinate = xCoord;
	}

	public void setxBernie(final int xCoordSecond) {
		this.xKoordinateZwei = xCoordSecond;
	}

	public void setyKatze(final int yCoord) {
		this.yKoordinate = yCoord;
	}

	public void setyBernie(final int yCoordSecond) {
		this.yKoordinateZwei = yCoordSecond;
	}
}