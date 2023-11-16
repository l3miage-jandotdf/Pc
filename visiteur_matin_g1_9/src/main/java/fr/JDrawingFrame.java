package fr;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import fr.commands.AddShape;
import fr.commands.RemoveShape;
import fr.persistence.JSonVisitor;
import fr.persistence.Visitor;
import fr.persistence.XMLVisitor;
import fr.shapes.Circle;
import fr.shapes.Element;
import fr.shapes.ShapesList;
import fr.shapes.SimpleShape;
import fr.shapes.Square;
import fr.shapes.Triangle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame
    implements MouseListener, MouseMotionListener, KeyListener
{
	private enum Shapes {SQUARE, TRIANGLE, CIRCLE};
    private static final long serialVersionUID = 1L;
    private JToolBar toolbar;
    private Shapes selected;
    private JPanel panel;
    private JLabel label;
    private ActionListener reusableActionListener = new ShapeActionListener();
    private List<Element> elements = new ArrayList<>();
    private boolean isDragging = false;
    private SimpleShape selectedShape;

    private ShapesList shapeList = new ShapesList();

    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> buttons = new HashMap<>();

    /**
     * Default constructor that populates the main window.
     * @param frameName 
    **/
    public JDrawingFrame(String frameName)
    {
        super(frameName);
        // Instantiates components
        toolbar = new JToolBar("Toolbar");
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(400, 400));
        panel.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        panel.addMouseMotionListener(this);
        label = new JLabel(" ", JLabel.LEFT);
        
        // Fills the panel
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));

        setPreferredSize(new Dimension(400, 400));

        JButton selectButton = new JButton("Select");
        JButton exportButtonXML = new JButton("XML");
        JButton exportButtonJSON = new JButton("JSON");

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = null;
            }
        }); 

        exportButtonXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportShapes(false); 
            }
        });        

        exportButtonJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportShapes(true); 
            }
        });

        toolbar.add(selectButton);
        toolbar.add(exportButtonXML);
        toolbar.add(exportButtonJSON);
        

    }


	/**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
    **/
    public void addShape(Shapes shape, ImageIcon icon)
    {
        JButton button = new JButton(icon);
		button.setBorderPainted(false);
        buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(reusableActionListener);

        if (selected == null)
        {
            button.doClick();
        }

        toolbar.add(button);
        toolbar.validate();
        repaint();
    }

    /**
     * SÃ©lectionne la forme courante
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
    **/    
    private SimpleShape getSelectedShape(int mouseX, int mouseY) {
        for (SimpleShape shape : shapeList.getAllShapes()) {
            if (isMouseInsideShape(shape, mouseX, mouseY)) {
                return shape; 
            }
        }
        return null; // Aucune forme sÃ©lectionnÃ©e
    }

    private boolean isMouseInsideShape(SimpleShape shape, int mouseX, int mouseY) {
        int x = shape.getX(); 
        int y = shape.getY();
        int width = 50;
        int height = 50; 
    
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    /**
     *  Use the factory to abstract shape creation
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
    **/
    public void mouseClicked(MouseEvent evt) {
        if (panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            if (selected != null){
                switch (selected) {
                    case CIRCLE:
                        Circle circle = new Circle(evt.getX(), evt.getY());
                        AddShape addCommandCircle = new AddShape(shapeList, circle);
                        addCommandCircle.execute(g2); // dessine le cercle
                        elements.add(circle); //enregistre pour l'export
                        break;

                    case TRIANGLE:
                        Triangle triangle = new Triangle(evt.getX(), evt.getY());
                        AddShape addCommandTriangle = new AddShape(shapeList, triangle);
                        addCommandTriangle.execute(g2); // dessine le triangle
                        elements.add(triangle); //enregistre pour l'export
                        break;

                    case SQUARE:
                        Square square = new Square(evt.getX(), evt.getY());
                        AddShape addCommandSquare = new AddShape(shapeList, square);
                        addCommandSquare.execute(g2); // dessine le carrÃ©
                        elements.add(square); //enregistre pour l'export
                        break;
                    default:
                        break;

                }
            }
        }
        this.requestFocusInWindow(); //reprend le focus sur le clavier

    }
    


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) { //Ctrl + Z
            repaint(); //remet l'Ã©cran Ã  blanc
            RemoveShape removeCommand = new RemoveShape(shapeList);
            removeCommand.execute(null); //enlÃ¨ve la derniÃ¨re forme de la liste
            if (!elements.isEmpty()) { //enlÃ¨ve la derniÃ¨re forme pour l'export
                elements.remove(elements.size() - 1);
            }

        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (SimpleShape shape : shapeList.getAllShapes()) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            shape.draw(g2); //redessine les formes, sauf la derniÃ¨re qui a Ã©tÃ© effacÃ©e
        }
    }
    

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseEntered(MouseEvent evt)
    {
    	
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseExited(MouseEvent evt)
    {
    	label.setText(" ");
    	label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mousePressed(MouseEvent e) {
        
    }

    public void refresh(){
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        for (SimpleShape shape : shapeList.getAllShapes()) {
            shape.draw(g2); //redessine les formes
            System.out.println("euh ?");
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mouseReleased(MouseEvent evt)
    {

        if (isDragging == true){
            isDragging = false;
            System.out.println("yoooooooo");
            if (selectedShape != null) {
                
                System.out.println("HEY");
                selectedShape.setX(evt.getX());
                selectedShape.setY(evt.getY());
                refresh();
            }

        }
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
    **/
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("hep");
        System.out.println(isDragging);

        if (isDragging == false){
            System.out.println("ALLO");
            selectedShape = getSelectedShape(e.getX(), e.getY());
            repaint();
            System.out.println("selectedShape");

        }
        isDragging = true;
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
    **/
    public void mouseMoved(MouseEvent evt)
    {
    	modifyLabel(evt);
    }
    
    private void modifyLabel(MouseEvent evt) {
    	label.setText("(" + evt.getX() + "," + evt.getY() + ")");    	
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
    **/
    private class ShapeActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
        	// Itère sur tous les boutons
        	Iterator<Shapes> keys = buttons.keySet().iterator();
        	while (keys.hasNext()) {
        		Shapes shape = keys.next();
				JButton btn = buttons.get(shape);
				if (evt.getActionCommand().equals(shape.toString())) {
					btn.setBorderPainted(true);
					selected = shape;
		        } else {
					btn.setBorderPainted(false);
				}
				btn.repaint();
			}
        }
    }


    /** Écris le contenu du dessin dans un fichier (.json ou .xml)
     * @param isJSON true si l'on veut un json, false pour un xml
     * @return selectedFile le fichier créé
     */
    public File exportShapes(boolean isJSON) {
        Visitor visitor = isJSON ? new JSonVisitor() : new XMLVisitor();
        String extension = isJSON ? "json" : "xml";
    
        JFileChooser fileChooser = new JFileChooser();
    
        // Asks the user where they want to save the file
        int result = fileChooser.showSaveDialog(this); // "this" is the main window
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
    
            // Check that the file has the correct extension
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith("." + extension)) {
                filePath += "." + extension;
                selectedFile = new File(filePath);
            }
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(isJSON ? "{" : "");
                writer.newLine();
                writer.write(isJSON ? "  \"shapes\": [" : "");
                writer.newLine();
                boolean firstShape = true;
                for (Element element : elements) {
                    element.accept(visitor);
                    String representation = visitor.getRepresentation();
                    if (!firstShape) {
                        writer.write(isJSON ? "," : "");
                        writer.newLine();
                    }
                    writer.write(representation);
                    firstShape = false;
                }
                writer.newLine();
                writer.write(isJSON ? "  ]" : "");
                writer.newLine();
                writer.write(isJSON ? "}" : "");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return selectedFile;
        }
        return null;
    }

    /** Lit le fichier créé --> Sert pour le test <--
     * @param file fichier json ou xml
     * @return String le contenu du fichier
     */
    public String readFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Utilisée pour le test, simulation de la pose de formes par la souris 
     * @param shape une forme
     */
    public void addShape(SimpleShape shape) {
        shapeList.addShape(shape);
        elements.add((Element)shape);
    }
    
    public ShapesList getShapeList() {
        return shapeList;
    }
    public List<Element> getElements() {
        return elements;
    }
    



   


}