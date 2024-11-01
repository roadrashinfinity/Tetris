package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tetromino {
	Rectangle a,b,c,d;
	Color color;
	private String name;
	public int count = 1;
	
	public Tetromino(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.name = name;

		switch (name) {
		case "j":
			color = Color.GRAY;
			break;
		case "l":
			color = Color.YELLOW;
			break;
		case "o":
			color = Color.RED;
			break;
		case "s":
			color = Color.GREEN;
			break;
		case "t":
			color = Color.BLUE;
			break;
		case "z":
			color = Color.PINK;
			break;
		case "i":
			color = Color.BROWN;
			break;

		}
		this.a.setFill(color);
		this.b.setFill(color);
		this.c.setFill(color);
		this.d.setFill(color);
	}
	
	public String getName() {
		return name;
	}
	
	public void changeForm() {
		if (count != 4) {
			count++;
		} else {
			count = 1;
		}
	}
}
