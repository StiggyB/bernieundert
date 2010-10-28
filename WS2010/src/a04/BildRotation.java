/**
 * @author Bernie und Ert
 */

package a04;

/**
 * Diese Klasse animiert zwei Bilder, die ineinander laufen sollen. 
 * Am Ende der Animation, "unterläuft" ein Bild das andere.
 */

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BildRotation {
	int ersteRichtung = 0;
	int zweiteRichtung = 0;

	public void bildlaufenlassen(final int weite, final int hoehe) {
		final BildPanel bild = new BildPanel(weite, hoehe, true, 0, 0);

		FrameZusammenBauen.show(bild);
		final int waagerechterRahmen = bild.getWidth();
		final int senkrechterRahmen = bild.getHeight();

		while (FrameZusammenBauen.frame.isVisible()) {
			final int katzenBildX = bild.getxKoordinate();
			final int katzenBildY = bild.getyKoordinate();

			final int katzenBildXWinkel = katzenBildX
					+ bild.getImage().getWidth(null);
			final int katzenBildYWinkel = katzenBildY
					+ bild.getImage().getHeight(null);
			final int bernieBildX = bild.getxBernie();
			final int bernieBildY = bild.getyBernie();

			final int bernieXWinkel = bernieBildX
					+ bild.getImageZwei().getWidth(null);
			final int bernieYWinkel = bernieBildY
					+ bild.getImageZwei().getHeight(null);

			if (((katzenBildX <= bernieXWinkel) && (katzenBildX >= bernieBildX))
					|| ((katzenBildXWinkel <= bernieXWinkel) && (katzenBildXWinkel >= bernieBildX))) {
				if (((katzenBildY <= bernieYWinkel) && (katzenBildY >= bernieBildY))
						|| ((katzenBildYWinkel <= bernieYWinkel) && (katzenBildYWinkel >= bernieBildY))) {
					if ((this.ersteRichtung == 0) && (this.zweiteRichtung == 2)) {
						this.ersteRichtung = 2;
						this.zweiteRichtung = 4;
					} else if ((this.ersteRichtung == 0)
							&& (this.zweiteRichtung == 3)) {
						this.ersteRichtung = 2;
						this.zweiteRichtung = 1;
					} else if ((this.ersteRichtung == 1)
							&& (this.zweiteRichtung == 2)) {
						this.ersteRichtung = 3;
						this.zweiteRichtung = 0;
					} else if ((this.ersteRichtung == 1)
							&& (this.zweiteRichtung == 3)) {
						this.ersteRichtung = 3;
						this.zweiteRichtung = 1;
					} else if ((this.ersteRichtung == 2)
							&& (this.zweiteRichtung == 0)) {
						this.ersteRichtung = 0;
						this.zweiteRichtung = 2;
					} else if ((this.ersteRichtung == 2)
							&& (this.zweiteRichtung == 1)) {
						this.ersteRichtung = 0;
						this.zweiteRichtung = 3;
					} else if ((this.ersteRichtung == 3)
							&& (this.zweiteRichtung == 0)) {
						this.ersteRichtung = 1;
						this.zweiteRichtung = 2;
					} else if ((this.ersteRichtung == 3)
							&& (this.zweiteRichtung == 1)) {
						this.ersteRichtung = 1;
						this.zweiteRichtung = 3;
					}
				}
			}
			this.kollisionBildEins(waagerechterRahmen, senkrechterRahmen,
					katzenBildX, katzenBildY, katzenBildXWinkel,
					katzenBildYWinkel);
			this.kollisionBildZwei(waagerechterRahmen, senkrechterRahmen,
					bernieBildX, bernieBildY, bernieXWinkel, bernieYWinkel);
			this.richtungBildEins(bild);
			this.richtungBildZwei(bild);

			try {
				Thread.sleep(3);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			bild.repaint();

		}
	}

	private void kollisionBildEins(final int xRahmen, final int yRahmen,
			final int bildEinsX, final int bildEinsY, final int bildEinsXWinkel,
			final int ersterYWinkel) {
		if (bildEinsX < 0) {
			if (this.ersteRichtung == 2) {
				this.ersteRichtung = 0;
			}
			if (this.ersteRichtung == 3) {
				this.ersteRichtung = 1;
			}

		}
		if (bildEinsXWinkel >= xRahmen) {
			if (this.ersteRichtung == 0) {
				this.ersteRichtung = 2;
			}
			if (this.ersteRichtung == 1) {
				this.ersteRichtung = 3;
			}

		}
		if (bildEinsY < 0) {
			if (this.ersteRichtung == 2) {
				this.ersteRichtung = 3;
			}
			if (this.ersteRichtung == 0) {
				this.ersteRichtung = 1;
			}

		}
		if (ersterYWinkel >= yRahmen) {
			if (this.ersteRichtung == 1) {
				this.ersteRichtung = 0;
			}
			if (this.ersteRichtung == 3) {
				this.ersteRichtung = 2;
			}
		}
	}

	private void kollisionBildZwei(final int xRahmen, final int yRahmen,
			final int zweitesBildX, final int zweitesBildY, final int zweiterXWinkel,
			final int zweiterYWinkel) {
		if (zweitesBildX < 0) {
			if (this.zweiteRichtung == 2) {
				this.zweiteRichtung = 0;
			}
			if (this.zweiteRichtung == 3) {
				this.zweiteRichtung = 1;
			}

		}
		if (zweiterXWinkel >= xRahmen) {
			if (this.zweiteRichtung == 0) {
				this.zweiteRichtung = 2;
			}
			if (this.zweiteRichtung == 1) {
				this.zweiteRichtung = 3;
			}

		}
		if (zweitesBildY < 0) {
			if (this.zweiteRichtung == 0) {
				this.zweiteRichtung = 1;
			}
			if (this.zweiteRichtung == 2) {
				this.zweiteRichtung = 3;
			}
		}
		if (zweiterYWinkel >= yRahmen) {
			if (this.zweiteRichtung == 1) {
				this.zweiteRichtung = 0;
			}
			if (this.zweiteRichtung == 3) {
				this.zweiteRichtung = 2;
			}
		}
	}

	private void richtungBildEins(final BildPanel bild) {
		if (this.ersteRichtung == 0) {
			bild.setxKatze(bild.getxKoordinate() + 1);
			bild.setyKatze(bild.getyKoordinate() - 1);
		}

		if (this.ersteRichtung == 1) {
			bild.setxKatze(bild.getxKoordinate() + 1);
			bild.setyKatze(bild.getyKoordinate() + 1);
		}
		if (this.ersteRichtung == 2) {
			bild.setxKatze(bild.getxKoordinate() - 1);
			bild.setyKatze(bild.getyKoordinate() - 1);
		}

		if (this.ersteRichtung == 3) {
			bild.setxKatze(bild.getxKoordinate() - 1);
			bild.setyKatze(bild.getyKoordinate() + 1);
		}
	}

	private void richtungBildZwei(final BildPanel bild) {
		if (this.zweiteRichtung == 0) {
			bild.setxBernie(bild.getxBernie() + 1);
			bild.setyBernie(bild.getyBernie() - 1);
		}
		if (this.zweiteRichtung == 1) {
			bild.setxBernie(bild.getxBernie() + 1);
			bild.setyBernie(bild.getyBernie() + 1);
		}
		if (this.zweiteRichtung == 2) {
			bild.setxBernie(bild.getxBernie() - 1);
			bild.setyBernie(bild.getyBernie() - 1);
		}
		if (this.zweiteRichtung == 3) {
			bild.setxBernie(bild.getxBernie() - 1);
			bild.setyBernie(bild.getyBernie() + 1);
		}
	}
}

class FrameZusammenBauen {
	static JFrame frame;

	public static void show(final JComponent c) {
		frame = new JFrame("Bernie und Ert Bildanimationszeugs 1.0");
		frame.setPreferredSize(new Dimension(1024, 768));
		frame.add(c);
		frame.pack();
		frame.setVisible(true);
	}
}
