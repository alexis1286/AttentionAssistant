package AttentionAssistant;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

public class Free_Thought_Space {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private String activeTool;
	private LineBorder line = new LineBorder(Color.gray, 2, true);
	private LineBorder lineSelected = new LineBorder(Color.white, 2, true);
	private Color selectedColor,colorToChange,primaryColor,secondaryColor;
	private boolean isFilled;
	private double opacity;
	private Stack<Layer> addedLayers;
	private Stack<Layer> removedLayers;
	private Stack<Layer> displayLayers; //maybe different data structure?
	private int mouseClickX,mouseClickY,mouseReleaseX,mouseReleaseY;
	private Color primary,secondary;	
	
	public Free_Thought_Space() {
		this.primaryColor = Color.black;
		this.secondaryColor = aa_purple;
	}
	
	public void runFts(Free_Thought_Space fts,DataBase db,int userID) {
		JFrame frame = new JFrame("Free Thought Space");
		frame.setBackground(Color.BLACK);
		frame.setSize(new Dimension(dim.width-200,dim.height-200));
		frame.setPreferredSize(new Dimension(dim.width-200,dim.height-200));
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		
		
		//add title bar- needs maximize/shrink & minimize buttons
		
		//add menu
		JMenuBar menu = menu();
		frame.setJMenuBar(menu);
		//add toolbar
		frame.getContentPane().add(toolBar(),BorderLayout.WEST);
		//add draw space
		frame.getContentPane().add(drawingSpace(),BorderLayout.CENTER);
		//add colorbar
		frame.getContentPane().add(createColorBar(db,userID),BorderLayout.PAGE_END);
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//*************************************************************************************drawing panel
	
	private JPanel drawingSpace() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(aa_grey));
		
		//on click/drag
		//create layer based on selections in toolbar and colorbar and mouse location(s)
		//create graphic based on layer (functionality for each tool)
		//make layer visible in drawing space
		//add shape to addedLayers and displayLayers
		
		//figure out undo/redo functionality
		
		return panel;
	}
	
	//pencil tool functionality
	private void doPencil() {
		
	}
	//spray tool functionality
	private void  doSpray() {
		
	}
	//line tool functionality
	private void doLine() {
		
	}
	//rectangle tool functionality
	private void doRectangle() {
		
	}
	//oval tool functionality
	private void doOval() {
		
	}
	//text tool functionality
	private void doText() {
		
	}
	//erase tool functionality
	private void doEraser() {
		
	}
	//undo tool functionality
	private void doUndo() {
		
	}
	//redo tool functionality
	private void doRedo() {
		
	}
	
	//*************************************************************************************tool bar
	private JPanel toolBar() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(90,500));
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBackground(Color.black);
		
		ArrayList<JButton> tools = toolButtons();
		for(int i=0;i<tools.size();i++) {
			panel.add(tools.get(i));
		}
		
		JLabel lw = new JLabel("line width:");
		lw.setForeground(aa_purple);
		
		//add line width adjustment
		JSlider lineWidth = new JSlider();
		lineWidth.setBackground(aa_grey);
		
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(lw);
		panel.add(lineWidth);
		
		return panel;
	}
	
	private ArrayList<JButton> toolButtons(){
		ArrayList<JButton> tools = new ArrayList<JButton>();
		BufferedImage pencilImg = null;
		BufferedImage sprayImg = null;
		BufferedImage lineImg = null;
		BufferedImage rectangleImg = null;
		BufferedImage ovalImg = null;
		BufferedImage textImg = null;
		BufferedImage eraseImg = null;
		BufferedImage clearImg = null;
		BufferedImage undoImg = null;
		BufferedImage redoImg = null;
		try {
			pencilImg = ImageIO.read(new File("images/pencil.png"));
			sprayImg = ImageIO.read(new File("images/paint-spray.png"));
			lineImg = ImageIO.read(new File("images/line.png"));
			rectangleImg = ImageIO.read(new File("images/rectangle.png"));
			ovalImg = ImageIO.read(new File("images/oval.png"));
			textImg = ImageIO.read(new File("images/add-text.png"));
			eraseImg = ImageIO.read(new File("images/eraser.png"));
			//make clear image for icon
			clearImg = ImageIO.read(new File("images/eraser.png"));
			undoImg = ImageIO.read(new File("images/undo.png"));
			redoImg = ImageIO.read(new File("images/redo.png"));
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		int right = 20;
		int left = 10;
		JButton pencil = new JButton();
		pencil.setIcon(new ImageIcon(pencilImg));
		pencil.setContentAreaFilled(false);
		pencil.setFocusPainted(false);
		pencil.setBorderPainted(false);
		pencil.setMargin(new Insets(0,left,0,right));
		pencil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "pencil";
			}
		});
		
		JButton spray = new JButton();
		spray.setIcon(new ImageIcon(sprayImg));
		spray.setContentAreaFilled(false);
		spray.setFocusPainted(false);
		spray.setBorderPainted(false);
		spray.setMargin(new Insets(0,left,0,right));
		spray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "spray";
			}
		});
		
		JButton line = new JButton();
		line.setIcon(new ImageIcon(lineImg));
		line.setContentAreaFilled(false);
		line.setFocusPainted(false);
		line.setBorderPainted(false);
		line.setMargin(new Insets(0,left,0,right));
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "line";
			}
		});
		
		JButton rectangle = new JButton();
		rectangle.setIcon(new ImageIcon(rectangleImg));
		rectangle.setContentAreaFilled(false);
		rectangle.setFocusPainted(false);
		rectangle.setBorderPainted(false);
		rectangle.setMargin(new Insets(0,left,0,right));
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "rectangle";
			}
		});
		
		JButton oval = new JButton();
		oval.setIcon(new ImageIcon(ovalImg));
		oval.setContentAreaFilled(false);
		oval.setFocusPainted(false);
		oval.setBorderPainted(false);
		oval.setMargin(new Insets(0,left,0,right));
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "oval";
			}
		});
		
		JButton text = new JButton();
		text.setIcon(new ImageIcon(textImg));
		text.setContentAreaFilled(false);
		text.setFocusPainted(false);
		text.setBorderPainted(false);
		text.setMargin(new Insets(0,left,0,right));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "text";
			}
		});
		
		JButton erase = new JButton();
		erase.setIcon(new ImageIcon(eraseImg));
		erase.setContentAreaFilled(false);
		erase.setFocusPainted(false);
		erase.setBorderPainted(false);
		erase.setMargin(new Insets(0,left,0,right));
		erase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "erase";
			}
		});
		
		JButton clear = new JButton();
		clear.setIcon(new ImageIcon(clearImg));
		clear.setContentAreaFilled(false);
		clear.setFocusPainted(false);
		clear.setBorderPainted(false);
		clear.setMargin(new Insets(0,left,0,right));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "clear";
			}
		});
		
		JButton undo = new JButton();
		undo.setIcon(new ImageIcon(undoImg));
		undo.setContentAreaFilled(false);
		undo.setFocusPainted(false);
		undo.setBorderPainted(false);
		undo.setMargin(new Insets(0,left,0,right));
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "undo";
			}
		});
		
		JButton redo = new JButton();
		redo.setIcon(new ImageIcon(redoImg));
		redo.setContentAreaFilled(false);
		redo.setFocusPainted(false);
		redo.setBorderPainted(false);
		redo.setMargin(new Insets(0,left,0,right));
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeTool = "redo";
			}
		});
		
		tools.add(pencil);
		tools.add(spray);
		tools.add(line);
		tools.add(rectangle);
		tools.add(oval);
		tools.add(text);
		tools.add(erase);
		tools.add(clear);
		tools.add(undo);
		tools.add(redo);
		return tools;
	}
	
	//*************************************************************************************color bar
	int size=25;
	public JPanel createColorBar(DataBase db, int userID) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		JPanel palette = colorPalette(db,userID);
		JPanel selection = colorSelection(db,userID);
		
		panel.add(palette);
		panel.add(selection);
		return panel;
	}
	
	private JPanel colorSelection(DataBase db,int userID) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(500,150));
		panel.setBackground(Color.black);
		panel.setForeground(Color.white);
		
		BufferedImage c1 = null;
		BufferedImage c2 = null;
		try {
			c1 = ImageIO.read(new File("images/square.png"));
			c2 = ImageIO.read(new File("images/square.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		colorIcon(c1,primaryColor);
		colorIcon(c2,secondaryColor);
		
		JButton primary = new JButton();
		JButton secondary = new JButton();
		primary.setIcon(new ImageIcon(c1));
		primary.setContentAreaFilled(false);
		primary.setBorder(line);
		secondary.setIcon(new ImageIcon(c2));
		secondary.setContentAreaFilled(false);
		secondary.setBorder(line);
		primary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedColor != null) {
					primaryColor = selectedColor;
				}
			}
		});
		secondary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedColor != null) {
					secondaryColor = selectedColor;
				}
			}
		});
		
		JCheckBox filled = new JCheckBox("filled");
		filled.setBackground(Color.black);
		filled.setForeground(Color.white);
		filled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean filled = !shapeFilled();
				setFilled(filled);
			}
		});
		JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
		opacitySlider.setPreferredSize(new Dimension(150,75));
		opacitySlider.setMinorTickSpacing(5);
		opacitySlider.setMajorTickSpacing(10);
		opacitySlider.setPaintTicks(true);
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("  transparent"));
        labels.put(100, new JLabel("opaque  "));
        opacitySlider.setLabelTable(labels);
        opacitySlider.setPaintLabels(true);
        
		opacitySlider.setBackground(Color.black);
		opacitySlider.setForeground(Color.white);
		opacitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setOpacity(opacitySlider.getValue());
			}
		});
		
		JLabel p = new JLabel("primary: ");
		JLabel s = new JLabel("secondary: ");
		p.setForeground(Color.white);
		s.setForeground(Color.white);
		panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(p);
        panel.add(primary);
        panel.add(Box.createRigidArea(new Dimension(15,0)));
        panel.add(s);
        panel.add(secondary);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(filled);
        
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(opacitySlider);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        
		
		return panel;
	}
	
	private void setOpacity(int sliderValue) {
		opacity = (double) sliderValue/100;
	}
	public double getOpacity() {
		return this.opacity;
	}
	
	private void addColor(DataBase db,int userID) {
		if(selectedColor == null) {
			selectedColor = Color.white;
		}
		Color initialcolor = selectedColor;
		Color customColor = JColorChooser.showDialog(null,"select color", initialcolor);
		db.AddFTS_Color(customColor, userID);
	}
	
	//*************************************************************************************palettes
	private JPanel colorPalette(DataBase db,int userID) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.setBackground(Color.black);
		panel.setMaximumSize(new Dimension(110,150));
		
		JPanel paletteSelection = new JPanel();
		JPanel newColor = new JPanel();
		paletteSelection.setBackground(aa_grey);
		paletteSelection.setForeground(Color.white);
		newColor.setBackground(aa_grey);
		newColor.setForeground(Color.white);
		
		String palettesAvailable[] = {"rainbow","greyscale","custom colors"};
		JComboBox<String> palettes = new JComboBox<String>(palettesAvailable);
		palettes.setMaximumSize(new Dimension(100,20));
		
		JButton custom = new JButton("new color");
		custom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addColor(db,userID);
			}
		});
		paletteSelection.setLayout(new FlowLayout(FlowLayout.CENTER));
		paletteSelection.add(palettes);
		newColor.setLayout(new FlowLayout(FlowLayout.CENTER));
		newColor.add(custom);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(aa_grey);
		CardLayout cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.setMaximumSize(new Dimension(300,150));//******************************for color button spacing, kind of
		
		JPanel greyPanel = greyScale();
		JPanel rainbowPanel = rainbow();
		JPanel customPanel = custom(db,userID);
		
		cardPanel.add("rainbow",rainbowPanel);
		cardPanel.add("grey",greyPanel);
		cardPanel.add("custom",customPanel);
		
		palettes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(palettes.getItemAt(palettes.getSelectedIndex()) == "greyscale") {
					cardLayout.show(cardPanel,"grey");
				}else if(palettes.getItemAt(palettes.getSelectedIndex()) == "custom colors") {
					cardLayout.show(cardPanel,"custom");
				}else {
					cardLayout.show(cardPanel,"rainbow");
				}
			}
		});
		
		JPanel jp = new JPanel();
		jp.setBackground(aa_grey);
		jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
		jp.add(Box.createRigidArea(new Dimension(0,20)));
		jp.add(paletteSelection);
		jp.add(newColor);
		
		panel.add(Box.createRigidArea(new Dimension(90,10)));
		panel.add(cardPanel);
		panel.add(jp);
		
		return panel;
	}
	
	private JPanel greyScale() {
		JPanel gPanel = new JPanel();
		gPanel.setBackground(aa_grey);
		gPanel.setLayout(new BoxLayout(gPanel,BoxLayout.Y_AXIS));
		gPanel.setBackground(aa_grey);
		
		JPanel r1 = new JPanel();
		r1.setBackground(aa_grey);
		JPanel r2 = new JPanel();
		r2.setBackground(aa_grey);
		JPanel r3 = new JPanel();
		r3.setBackground(aa_grey);
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<Color> colors = new ArrayList<Color>();
		
		colors.add(new Color(255,255,255)); //white
		colors.add(new Color(247,247,247)); //grey 1
		colors.add(new Color(239,239,239)); //grey 2
		colors.add(new Color(231,231,231)); //grey 3
		colors.add(new Color(223,223,223)); //grey 4
		colors.add(new Color(215,215,215)); //grey 5
		colors.add(new Color(207,207,207)); //grey 6
		colors.add(new Color(199,199,199)); //grey 7
		colors.add(new Color(191,191,191)); //grey 8
		colors.add(new Color(183,183,183)); //grey 9
		colors.add(new Color(175,175,175)); //grey 10
		colors.add(new Color(167,167,167)); //grey 11
		colors.add(new Color(159,159,159)); //grey 12
		colors.add(new Color(151,151,151)); //grey 13
		colors.add(new Color(143,143,143)); //grey 14
		colors.add(new Color(135,135,135)); //grey 15
		colors.add(new Color(127,127,127)); //grey 16
		colors.add(new Color(119,119,119)); //grey 17
		colors.add(new Color(111,111,111)); //grey 18
		colors.add(new Color(103,103,103)); //grey 19
		colors.add(new Color(95,95,95)); //grey 20
		colors.add(new Color(87,87,87)); //grey 21
		colors.add(new Color(79,79,79)); //grey 22
		colors.add(new Color(71,71,71)); //grey 23
		colors.add(new Color(63,63,63)); //grey 24
		colors.add(new Color(55,55,55)); //grey 25
		colors.add(new Color(47,47,47)); //grey 26
		colors.add(new Color(39,39,39)); //grey 27
		colors.add(new Color(31,31,31)); //grey 28
		colors.add(new Color(0,0,0)); //black
		
		//use colors to generate button for each color
		buttons = colorButtons(colors);
		int third = buttons.size()/3;
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).setContentAreaFilled(false);
			if(i<third) {
				r1.add(buttons.get(i));
			}else if(i<third*2) {
				r2.add(buttons.get(i));
			}else {
				r3.add(buttons.get(i));
			}
		}
		gPanel.add(r1);
		gPanel.add(r2);
		gPanel.add(r3);
		
		return gPanel;
	}
	
	
	private JPanel rainbow() {
		JPanel rPanel = new JPanel();
		rPanel.setBackground(aa_grey);
		rPanel.setLayout(new BoxLayout(rPanel,BoxLayout.Y_AXIS));
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<Color> colors = new ArrayList<Color>();
		
		//make colors as list
		colors.add(new Color(102,0,0)); //dark red
		colors.add(new Color(102,51,0)); //dark orange
		colors.add(new Color(102,102,0)); //dark yellow
		colors.add(new Color(0,102,0)); //dark green
		colors.add(new Color(0,102,102)); //dark aqua
		colors.add(new Color(0,51,102)); //dark teal
		colors.add(new Color(0,0,102)); //dark blue
		colors.add(new Color(51,0,102)); //dark purple
		colors.add(new Color(102,0,102)); //dark fusia
		colors.add(new Color(102,0,51)); //dark raspberry
		
		colors.add(new Color(255,0,0)); //red
		colors.add(new Color(255,128,0)); //orange
		colors.add(new Color(255,255,0)); //yellow
		colors.add(new Color(0,255,0)); //green
		colors.add(new Color(0,255,255)); //aqua
		colors.add(new Color(0,128,255)); //teal
		colors.add(new Color(0,0,255)); //blue
		colors.add(new Color(127,0,255)); //purple
		colors.add(new Color(255,0,255)); //fuscia
		colors.add(new Color(255,0,127)); //raspberry
		
		colors.add(new Color(255,153,153)); //light red
		colors.add(new Color(255,204,153)); //light orange
		colors.add(new Color(255,255,153)); //light yellow
		colors.add(new Color(204,255,153)); //light green
		colors.add(new Color(153,255,255)); //light aqua
		colors.add(new Color(153,204,255)); //light teal
		colors.add(new Color(153,153,255)); //light blue
		colors.add(new Color(204,153,255)); //light purple
		colors.add(new Color(255,153,255)); //light fuscia
		colors.add(new Color(255,153,204)); //light raspberry
		
		JPanel r1 = new JPanel();
		r1.setBackground(aa_grey);
		JPanel r2 = new JPanel();
		r2.setBackground(aa_grey);
		JPanel r3 = new JPanel();
		r3.setBackground(aa_grey);
		
		//use colors to generate button for each color
		buttons = colorButtons(colors);
		int third = buttons.size()/3;
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).setContentAreaFilled(false);
			if(i<third) {
				r1.add(buttons.get(i));
			}else if(i<third*2) {
				r2.add(buttons.get(i));
			}else {
				r3.add(buttons.get(i));
			}
		}
		
		rPanel.add(r1);
		rPanel.add(r2);
		rPanel.add(r3);
		
		return rPanel;
	}
	
	private JPanel custom(DataBase db,int userID) {
		JPanel cPanel = new JPanel();
		cPanel.setBackground(aa_grey);
		//get colors from database, make buttons with
		ArrayList<Color> colors = db.SelectAllFTS_Color(userID);
		if(colors.size() > 0) {
			ArrayList<JButton> buttons = colorButtons(colors);
			for(int i=0;i<buttons.size();i++) {
				cPanel.add(buttons.get(i));
			}
		}
		
		return cPanel;
	}
	
	private ArrayList<JButton> colorButtons(ArrayList<Color> colors){
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		int i;
		for(i=0;i<colors.size();i++) {
			Color current = colors.get(i);
			
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("images/square.png"));
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			colorIcon(img,current);
			JButton button = new JButton();
			button.setIcon(new ImageIcon(img));
			button.setBorder(line);
			button.setContentAreaFilled(false);
			button.setMargin(new Insets(0,0,0,0));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedColor = current;
					button.setBorder(lineSelected);
				}
			});
			buttons.add(button);
		}
		return buttons;
	}
	
	public BufferedImage colorIcon(BufferedImage image,Color current) {
		//get new red, green, blue values from color
		int red = current.getRed();
		int green = current.getGreen();
		int blue = current.getBlue();
		//get height and width of image to be altered
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    //recolors image to new rgb values
	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        pixels[0] = red;
	        pixels[1] = green;
	        pixels[2] = blue;
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    return image;
	  }
	
	private Color getPrimary() {
		return this.primaryColor;
	}
	private Color getSecondary() {
		return this.secondaryColor;
	}
	private boolean shapeFilled() {
		return isFilled;
	}
	private void setFilled(boolean filled) {
		this.isFilled = filled;
	}
	
	
	private JMenuBar menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu();
		JMenu help = new JMenu();
		
		JMenuItem newFile = new JMenuItem("new");
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear drawingSpace
			}
		});
		
		JMenuItem open = new JMenuItem("open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open jpeg or png
			}
		});
		
		JMenuItem save = new JMenuItem("save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save as pdf
			}
		});
		
		JMenuItem guide = new JMenuItem("guide");
		guide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open guide
			}
		});
		
		file.add(newFile);
		file.add(open);
		file.add(save);
		help.add(guide);
		menuBar.add(file);
		menuBar.add(help);
		
		return menuBar;
	}
}