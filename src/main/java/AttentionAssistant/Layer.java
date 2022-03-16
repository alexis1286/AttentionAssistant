package AttentionAssistant;

import java.awt.*;

public class Layer {
	private int mouseClickX,mouseClickY,mouseReleaseX,mouseReleaseY;
	private Color primaryColor,secondaryColor;
	private BasicStroke stroke;
	private String text;
	private double opacity;
	private String tool;
	private Font font;
	
	//single click tool
	public Layer(int mouseClickX,int mouseClickY,Color primaryColor,Color secondaryColor,BasicStroke stroke,double opacity,String tool) {
		this.mouseClickX = mouseClickX;
		this.mouseClickY = mouseClickY;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.stroke = stroke;
		this.opacity = opacity;
		this.tool = tool;
	}
	
	//click and drag tool
	public Layer(int mouseClickX,int mouseClickY,int mouseReleaseX,int mouseReleaseY,Color primaryColor,Color secondaryColor,BasicStroke stroke,double opacity,String tool) {
		this.mouseClickX = mouseClickX;
		this.mouseClickY = mouseClickY;
		this.mouseReleaseX = mouseReleaseX;
		this.mouseReleaseY = mouseReleaseY;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.stroke = stroke;
		this.opacity = opacity;
		this.tool = tool;
	}
	
	//text tool
	public Layer(int mouseClickX,int mouseClickY,Color primaryColor,Color secondaryColor,String text,double opacity,String tool) {
		this.mouseClickX = mouseClickX;
		this.mouseClickY = mouseClickY;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.text = text;
		this.opacity = opacity;
		this.tool = tool;
	}
	
	public int getMouseClickX() {
		return this.mouseClickX;
	}
	
	public int getMouseClickY() {
		return this.mouseClickY;
	}
	
	public int getMouseReleaseX() {
		return this.mouseReleaseX;
	}
	
	public int getMouseReleaseY() {
		return this.mouseReleaseY;
	}
	
	public Color getPrimary() {
		return this.primaryColor;
	}
	
	public Color getSecondary() {
		return this.secondaryColor;
	}
	public String getText() {
		return this.text;
	}
	public double getOpacity() {
		return this.opacity;
	}
	public String getTool() {
		return this.tool;
	}
}
