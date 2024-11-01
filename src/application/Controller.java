 package application;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Controller {
	// Getting numbers and the MESH from Main class
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static int XMAX = Main.XMAX;
	public static int YMAX = Main.YMAX;
	public static int[][] MESH = Main.MESH;
	private static Pane group = Main.group;
	
	
	public static void MoveRight(Tetromino t) {
		if (t.a.getX() + MOVE <= XMAX - SIZE && t.b.getX() + MOVE <= XMAX - SIZE
				&& t.c.getX() + MOVE <= XMAX - SIZE && t.d.getX() + MOVE <= XMAX - SIZE) {
			int movea = MESH[((int) t.a.getX() / SIZE) + 1][((int) t.a.getY() / SIZE)];
			int moveb = MESH[((int) t.b.getX() / SIZE) + 1][((int) t.b.getY() / SIZE)];
			int movec = MESH[((int) t.c.getX() / SIZE) + 1][((int) t.c.getY() / SIZE)];
			int moved = MESH[((int) t.d.getX() / SIZE) + 1][((int) t.d.getY() / SIZE)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setX(t.a.getX() + MOVE);
				t.b.setX(t.b.getX() + MOVE);
				t.c.setX(t.c.getX() + MOVE);
				t.d.setX(t.d.getX() + MOVE);
			}
		}
	}
	
	public static void RemoveRows(Pane pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newrects = new ArrayList<Node>();
		int full = 0;
		
		for (int i = 0; i < MESH[0].length; i++) {
			for (int j = 0; j < MESH.length; j++) {
				if (MESH[j][i] == 1) full++;
			}
			if (full == MESH.length) lines.add(i);
			full = 0;
		}
		
		if (lines.size() > 0)
			do {
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle) rects.add(node);
				}
				Main.score += 50;
				Main.lineno++;
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() == lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						pane.getChildren().remove(node);
					} else
						newrects.add(node);
				}

				for (Node node : newrects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() < lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						a.setY(a.getY() + SIZE);
					}
				}
				lines.remove(0);
				rects.clear();
				newrects.clear();
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					try {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				rects.clear();
			} while (lines.size() > 0);
	}
	
	//moveturn function
	
		public static void MoveTurn(Tetromino t) {
			int f = t.count;
			Rectangle a = t.a;
			Rectangle b = t.b;
			Rectangle c = t.c;
			Rectangle d = t.d;
			switch (t.getName()) {
			case "j":
				if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
					MoveRight(t.a);
					MoveDown(t.a);
					MoveDown(t.c);
					MoveLeft(t.c);
					MoveDown(t.d);
					MoveDown(t.d);
					MoveLeft(t.d);
					MoveLeft(t.d);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveLeft(t.c);
					MoveUp(t.c);
					MoveLeft(t.d);
					MoveLeft(t.d);
					MoveUp(t.d);
					MoveUp(t.d);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
					MoveLeft(t.a);
					MoveUp(t.a);
					MoveUp(t.c);
					MoveRight(t.c);
					MoveUp(t.d);
					MoveUp(t.d);
					MoveRight(t.d);
					MoveRight(t.d);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
					MoveUp(t.a);
					MoveRight(t.a);
					MoveRight(t.c);
					MoveDown(t.c);
					MoveRight(t.d);
					MoveRight(t.d);
					MoveDown(t.d);
					MoveDown(t.d);
					t.changeForm();
					break;
				}
				break;
			case "l":
				if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
					MoveRight(t.a);
					MoveDown(t.a);
					MoveUp(t.c);
					MoveRight(t.c);
					MoveUp(t.b);
					MoveUp(t.b);
					MoveRight(t.b);
					MoveRight(t.b);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveRight(t.b);
					MoveRight(t.b);
					MoveDown(t.b);
					MoveDown(t.b);
					MoveRight(t.c);
					MoveDown(t.c);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
					MoveLeft(t.a);
					MoveUp(t.a);
					MoveDown(t.c);
					MoveLeft(t.c);
					MoveDown(t.b);
					MoveDown(t.b);
					MoveLeft(t.b);
					MoveLeft(t.b);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
					MoveUp(t.a);
					MoveRight(t.a);
					MoveLeft(t.b);
					MoveLeft(t.b);
					MoveUp(t.b);
					MoveUp(t.b);
					MoveLeft(t.c);
					MoveUp(t.c);
					t.changeForm();
					break;
				}
				break;
			case "o":
				break;
			case "s":
				if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveLeft(t.c);
					MoveUp(t.c);
					MoveUp(t.d);
					MoveUp(t.d);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
					MoveUp(t.a);
					MoveRight(t.a);
					MoveRight(t.c);
					MoveDown(t.c);
					MoveDown(t.d);
					MoveDown(t.d);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveLeft(t.c);
					MoveUp(t.c);
					MoveUp(t.d);
					MoveUp(t.d);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
					MoveUp(t.a);
					MoveRight(t.a);
					MoveRight(t.c);
					MoveDown(t.c);
					MoveDown(t.d);
					MoveDown(t.d);
					t.changeForm();
					break;
				}
				break;
			case "t":
				if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
					MoveUp(t.a);
					MoveRight(t.a);
					MoveDown(t.d);
					MoveLeft(t.d);
					MoveLeft(t.c);
					MoveUp(t.c);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
					MoveRight(t.a);
					MoveDown(t.a);
					MoveLeft(t.d);
					MoveUp(t.d);
					MoveUp(t.c);
					MoveRight(t.c);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveUp(t.d);
					MoveRight(t.d);
					MoveRight(t.c);
					MoveDown(t.c);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
					MoveLeft(t.a);
					MoveUp(t.a);
					MoveRight(t.d);
					MoveDown(t.d);
					MoveDown(t.c);
					MoveLeft(t.c);
					t.changeForm();
					break;
				}
				break;
			case "z":
				if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
					MoveUp(t.b);
					MoveRight(t.b);
					MoveLeft(t.c);
					MoveUp(t.c);
					MoveLeft(t.d);
					MoveLeft(t.d);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
					MoveDown(t.b);
					MoveLeft(t.b);
					MoveRight(t.c);
					MoveDown(t.c);
					MoveRight(t.d);
					MoveRight(t.d);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
					MoveUp(t.b);
					MoveRight(t.b);
					MoveLeft(t.c);
					MoveUp(t.c);
					MoveLeft(t.d);
					MoveLeft(t.d);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
					MoveDown(t.b);
					MoveLeft(t.b);
					MoveRight(t.c);
					MoveDown(t.c);
					MoveRight(t.d);
					MoveRight(t.d);
					t.changeForm();
					break;
				}
				break;
			case "i":
				if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
					MoveUp(t.a);
					MoveUp(t.a);
					MoveRight(t.a);
					MoveRight(t.a);
					MoveUp(t.b);
					MoveRight(t.b);
					MoveDown(t.d);
					MoveLeft(t.d);
					t.changeForm();
					break;
				}
				if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
					MoveDown(t.a);
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveLeft(t.a);
					MoveDown(t.b);
					MoveLeft(t.b);
					MoveUp(t.d);
					MoveRight(t.d);
					t.changeForm();
					break;
				}
				if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
					MoveUp(t.a);
					MoveUp(t.a);
					MoveRight(t.a);
					MoveRight(t.a);
					MoveUp(t.b);
					MoveRight(t.b);
					MoveDown(t.d);
					MoveLeft(t.d);
					t.changeForm();
					break;
				}
				if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
					MoveDown(t.a);
					MoveDown(t.a);
					MoveLeft(t.a);
					MoveLeft(t.a);
					MoveDown(t.b);
					MoveLeft(t.b);
					MoveUp(t.d);
					MoveRight(t.d);
					t.changeForm();
					break;
				}
				break;
			}
		}
		
		//end of moveturn function
		
		public static boolean cB(Rectangle rect, int x, int y) {
			boolean xb = false;
			boolean yb = false;
			if (x >= 0)
				xb = rect.getX() + x * MOVE <= XMAX - SIZE;
			if (x < 0)
				xb = rect.getX() + x * MOVE >= 0;
			if (y >= 0)
				yb = rect.getY() - y * MOVE > 0;
			if (y < 0)
				yb = rect.getY() + y * MOVE < YMAX;
			return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
		}

		public static void MoveDown(Rectangle rect) {
			if (rect.getY() + MOVE < YMAX)
				rect.setY(rect.getY() + MOVE);

		}

		public static void MoveRight(Rectangle rect) {
			if (rect.getX() + MOVE <= XMAX - SIZE)
				rect.setX(rect.getX() + MOVE);
		}

		public static void MoveLeft(Rectangle rect) {
			if (rect.getX() - MOVE >= 0)
				rect.setX(rect.getX() - MOVE);
		}

		public static void MoveUp(Rectangle rect) {
			if (rect.getY() - MOVE > 0)
				rect.setY(rect.getY() - MOVE);
		}
		
	
	public static void MoveDown(Tetromino t) {
		if (t.a.getY() == YMAX - SIZE || t.b.getY() == YMAX - SIZE || t.c.getY() == YMAX - SIZE
				|| t.d.getY() == YMAX - SIZE || moveA(t) || moveB(t) || moveC(t) || moveD(t)) {
			MESH[(int) t.a.getX() / SIZE][(int) t.a.getY() / SIZE] = 1;
			MESH[(int) t.b.getX() / SIZE][(int) t.b.getY() / SIZE] = 1;
			MESH[(int) t.c.getX() / SIZE][(int) t.c.getY() / SIZE] = 1;
			MESH[(int) t.d.getX() / SIZE][(int) t.d.getY() / SIZE] = 1;
			RemoveRows(group);
			Main.generate_tetromino();
		}
		if (t.a.getY() + MOVE < YMAX && t.b.getY() + MOVE < YMAX && t.c.getY() + MOVE < YMAX
				&& t.d.getY() + MOVE < YMAX) {
			int movea = MESH[(int) t.a.getX() / SIZE][((int) t.a.getY() / SIZE) + 1];
			int moveb = MESH[(int) t.b.getX() / SIZE][((int) t.b.getY() / SIZE) + 1];
			int movec = MESH[(int) t.c.getX() / SIZE][((int) t.c.getY() / SIZE) + 1];
			int moved = MESH[(int) t.d.getX() / SIZE][((int) t.d.getY() / SIZE) + 1];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setY(t.a.getY() + MOVE);
				t.b.setY(t.b.getY() + MOVE);
				t.c.setY(t.c.getY() + MOVE);
				t.d.setY(t.d.getY() + MOVE);
			}
		}
	}
	
	public static boolean moveA(Tetromino t) {
		return (MESH[(int) t.a.getX() / SIZE][((int) t.a.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveB(Tetromino t) {
		return (MESH[(int) t.b.getX() / SIZE][((int) t.b.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveC(Tetromino t) {
		return (MESH[(int) t.c.getX() / SIZE][((int) t.c.getY() / SIZE) + 1] == 1);
	}

	public static boolean moveD(Tetromino t) {
		return (MESH[(int) t.d.getX() / SIZE][((int) t.d.getY() / SIZE) + 1] == 1);
	}
	
	public static void MoveLeft(Tetromino t) {
		if (t.a.getX() - MOVE >= 0 && t.b.getX() - MOVE >= 0 && t.c.getX() - MOVE >= 0
				&& t.d.getX() - MOVE >= 0) {
			int movea = MESH[((int) t.a.getX() / SIZE) - 1][((int) t.a.getY() / SIZE)];
			int moveb = MESH[((int) t.b.getX() / SIZE) - 1][((int) t.b.getY() / SIZE)];
			int movec = MESH[((int) t.c.getX() / SIZE) - 1][((int) t.c.getY() / SIZE)];
			int moved = MESH[((int) t.d.getX() / SIZE) - 1][((int) t.d.getY() / SIZE)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setX(t.a.getX() - MOVE);
				t.b.setX(t.b.getX() - MOVE);
				t.c.setX(t.c.getX() - MOVE);
				t.d.setX(t.d.getX() - MOVE);
			}
		}
	}

	public static Tetromino makeRect() {
		int block = (int) (Math.random() * 100);
		String name;
		Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
				d = new Rectangle(SIZE-1, SIZE-1);
		if (block < 15) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "j";
		} else if (block < 30) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "l";
		} else if (block < 45) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
			name = "o";
		} else if (block < 60) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
			name = "s";
		} else if (block < 75) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			name = "t";
		} else if (block < 90) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 + SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE + SIZE);
			d.setY(SIZE);
			name = "z";
		} else { 
			a.setX(XMAX / 2 - SIZE - SIZE);
			b.setX(XMAX / 2 - SIZE);
			c.setX(XMAX / 2);
			d.setX(XMAX / 2 + SIZE);
			name = "i";
		}
		return new Tetromino(a, b, c, d, name);
	}
}