package model;

public class CoordinateModel {

	public int x, y, line, idSalle;

	public String name;

	public CoordinateModel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public CoordinateModel(int x, int y, int line, String name) {
		super();
		this.x = x;
		this.y = y;
		this.line = line;
		this.name = name;
	}

	public CoordinateModel(int x, int y, int line) {
		super();
		this.x = x;
		this.y = y;
		this.line = line;
	}

	
	public CoordinateModel(int x, int y, int line,  int idSalle) {
		super();
		this.x = x;
		this.y = y;
		this.line = line;
		this.idSalle = idSalle;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
}
