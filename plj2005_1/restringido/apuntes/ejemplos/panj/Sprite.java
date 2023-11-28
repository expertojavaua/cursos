
import java.awt.*;
import java.awt.geom.*;

// ------------------------------------------------------------------------------------
// 	Clase general para los sprites (bounding box y calculo de colisiones)
// ------------------------------------------------------------------------------------

public abstract class Sprite 
{
	public final static int COLLIDE_NONE = 0;
	public final static int COLLIDE_TOP = 1;
	public final static int COLLIDE_BOTTOM = 2;
	public final static int COLLIDE_LEFT = 4;
	public final static int COLLIDE_RIGHT = 8;
	public final static int COLLIDE_INNER = 16;

	// Bounding box del sprite
	protected Rectangle2D.Double bb;	

	// Codigo de colision entre sprites
	private int collision_code;

	// Rectangulo auxiliar para calcular la interseccion
	private Rectangle2D.Double inter;

	public Sprite() 
	{
		bb = new Rectangle2D.Double(0.0, 0.0, 0.0, 0.0);
		inter = new Rectangle2D.Double();
	}

	public Sprite(int width, int height) 
	{
		bb = new Rectangle2D.Double(0.0, 0.0, width, height);
		inter = new Rectangle2D.Double();
	}

	public void setLocation(double x, double y)
	{
		bb.setRect(x, y, bb.width, bb.height);
	}

	public void setSize(int width, int height) {
		bb.setRect(bb.x, bb.y, width, height);
	}

	public Rectangle2D.Double getBoundingBox() {
		return bb;
	}

	public int collide(Sprite s) {
		Rectangle2D.Double bb2 = s.getBoundingBox();

		// Dos sprites colisionan si sus bounding boxes intersectan
		// Devolvemos un codigo que indica por que lado ha colisionado

		if(bb.intersects(bb2)) {

			Rectangle2D.intersect(bb,bb2,inter);

			collision_code = 0;

			if(inter.getMinX() == bb.getMinX()) {
				collision_code += COLLIDE_LEFT;
			}
			if(inter.getMaxX() == bb.getMaxX()) {
				collision_code += COLLIDE_RIGHT;
			}
			if(inter.getMinY() == bb.getMinY()) {
				collision_code += COLLIDE_TOP;
			}
			if(inter.getMaxY() == bb.getMaxY()) {
				collision_code += COLLIDE_BOTTOM;
			}
			if(collision_code == 0) {
				collision_code += COLLIDE_INNER;
			}

		} else {
			collision_code = 0;
		}

		return collision_code;
	}

	public abstract void render(Graphics g);
}