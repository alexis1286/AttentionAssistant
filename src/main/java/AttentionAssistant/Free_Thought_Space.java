package AttentionAssistant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Free_Thought_Space {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private String activeTool;
	private LineBorder line = new LineBorder(Color.gray, 2, true);
	private LineBorder lineSelected = new LineBorder(Color.white, 2, true);
	private Color selectedColor,primaryColor,secondaryColor;
	private boolean isFilled;
	private double opacity;
	
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
	private Stack<Layer> addedLayers;
	private Stack<Layer> removedLayers;
	private Stack<Layer> displayLayers; //maybe different data structure?
	private int mouseClickX,mouseClickY,mouseReleaseX,mouseReleaseY;
	private Color primary,secondary;	
	
	private JPanel drawingSpace() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createLineBorder(aa_grey));
		panel.setPreferredSize(new Dimension(250,250));
		
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
		
		//add line width adjustment
		JSlider lineWidth = new JSlider();
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
		//panel.setPreferredSize(new Dimension(dim.width-200,150));
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
		panel.setBackground(Color.black);
		panel.setForeground(Color.white);
		JButton custom = new JButton("new color");
		custom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addColor(db,userID);
			}
		});
		
		JButton primary = new JButton();
		JButton secondary = new JButton();
		primary.setBackground(primaryColor);
		primary.setBorder(line);
		primary.setPreferredSize(new Dimension(size,size));
		primary.setMaximumSize(new Dimension(size,size));
		secondary.setBackground(secondaryColor);
		secondary.setBorder(line);
		secondary.setMaximumSize(new Dimension(size,size));
		secondary.setMaximumSize(new Dimension(size,size));
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
		filled.setBackground(aa_grey);
		filled.setForeground(Color.white);
		filled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean filled = !shapeFilled();
				setFilled(filled);
			}
		});
		JSlider opacitySlider = new JSlider(JSlider.VERTICAL,0,100,0);
		opacitySlider.setMinorTickSpacing(5);
		opacitySlider.setMajorTickSpacing(10);
		opacitySlider.setPaintTicks(true);
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("transparent  "));
        labels.put(100, new JLabel("opaque"));
        opacitySlider.setLabelTable(labels);
        opacitySlider.setPaintLabels(true);
		opacitySlider.setBackground(aa_purple);
		opacitySlider.setForeground(Color.white);
		opacitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setOpacity(opacitySlider.getValue());
			}
		});
		
		JPanel coloring = new JPanel();
		coloring.setLayout(new GridLayout(2,2));
		coloring.setBackground(aa_grey);
		
        panel.add(Box.createRigidArea(new Dimension(15,0)));
        panel.add(custom);
        panel.add(Box.createRigidArea(new Dimension(15,0)));
        
        coloring.add(primary);
        coloring.add(Box.createRigidArea(new Dimension(5,0)));
        coloring.add(secondary);
        coloring.add(Box.createRigidArea(new Dimension(15,0)));
        coloring.add(filled);
        
        panel.add(coloring);
        panel.add(Box.createRigidArea(new Dimension(15,0)));
        panel.add(opacitySlider);
        panel.add(Box.createRigidArea(new Dimension(20,0)));
        
		
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
		panel.setPreferredSize(new Dimension(200,150));
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JPanel paletteSelection = new JPanel();
		
		String palettesAvailable[] = {"rainbow","greyscale","custom colors"};
		JComboBox<String> palettes = new JComboBox<String>(palettesAvailable);
		paletteSelection.add(palettes);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setPreferredSize(new Dimension(200,100));
		CardLayout cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
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
		
		panel.add(paletteSelection);
		panel.add(cardPanel);
		
		return panel;
	}
	
	private JPanel greyScale() {
		JPanel gPanel = new JPanel();
		gPanel.setPreferredSize(new Dimension(45,27));
		GridLayout grid = new GridLayout(3,10);
		gPanel.setLayout(grid);
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<Color> colors = new ArrayList<Color>();
		
		colors.add(new Color(255,255,255)); //white
		colors.add(new Color(235,235,235)); //grey 1
		colors.add(new Color(216,216,216)); //grey 2
		colors.add(new Color(198,198,198)); //grey 3
		colors.add(new Color(180,180,180)); //grey 4
		colors.add(new Color(162,162,162)); //grey 5
		colors.add(new Color(143,143,143)); //grey 6
		colors.add(new Color(125,125,125)); //grey 7
		colors.add(new Color(94,94,94)); //grey 8
		colors.add(new Color(93,93,93)); //grey 9
		colors.add(new Color(75,75,75)); //grey 10
		colors.add(new Color(57,57,57)); //grey 11
		colors.add(new Color(38,38,38)); //grey 12
		colors.add(new Color(20,20,20)); //grey 13
		colors.add(new Color(0,0,0)); //black
		
		//use colors to generate button for each color
		buttons = colorButtons(colors);
		for(int i=0;i<buttons.size();i++) {
			gPanel.add(buttons.get(i));
		}
		
		return gPanel;
	}
	
	
	private JPanel rainbow() {
		JPanel rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(45,27));
		GridLayout grid = new GridLayout(3,10);
		rPanel.setLayout(grid);
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		ArrayList<Color> colors = new ArrayList<Color>();
		
		//make colors as list
		colors.add(new Color(255,0,255)); //pink
		colors.add(new Color(255,0,127)); //pink-red
		colors.add(new Color(255,153,76)); //dark pink-red
		colors.add(new Color(255,0,0)); //red
		colors.add(new Color(255,128,0)); //orange
		colors.add(new Color(255,255,0)); //yellow
		colors.add(new Color(204,255,253)); //green-yellow
		colors.add(new Color(0,255,0)); //green
		colors.add(new Color(0,255,255)); //blue-green
		colors.add(new Color(0,0,255)); //blue
		colors.add(new Color(110,74,255)); //purple-blue
		colors.add(new Color(127,0,255)); //purple
		colors.add(new Color(102,51,0)); //brown
		colors.add(new Color(0,0,0)); //black
		colors.add(new Color(255,255,255)); //white
		
		//use colors to generate button for each color
		buttons = colorButtons(colors);
		for(int i=0;i<buttons.size();i++) {
			rPanel.add(buttons.get(i));
		}
		
		return rPanel;
	}
	
	private JPanel custom(DataBase db,int userID) {
		JPanel cPanel = new JPanel();
		cPanel.setPreferredSize(new Dimension(45,27));
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
		for(i=0;i<15;i++) {
			Color current = colors.get(i);
			JButton button = new JButton();
			button.setBackground(current);
			button.setBorder(line);
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