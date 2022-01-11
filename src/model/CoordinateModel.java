package model;

public class CoordinateModel {

	public int x, y, order;
	
	public String name;
	
	public CoordinateModel(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public CoordinateModel(int x, int y, int order, String name) {
		super();
		this.x = x;
		this.y = y;
		this.order = order;
		this.name = name;
	}
	
	public CoordinateModel(int x, int y, int order) {
		super();
		this.x = x;
		this.y = y;
		this.order = order;
	}


	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
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
	
	
}
