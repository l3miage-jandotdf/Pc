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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private JToolBar m_toolbar;
    private Shapes m_selected;
    private JPanel m_panel;
    private JLabel m_label;
    private ActionListener m_reusableActionListener = new ShapeActionListener();
    private List<Element> elements = new ArrayList<>();
    private ShapesList shapeList = new ShapesList();
    
    /**
     * Tracks buttons to manage the background.
     */
    private Map<Shapes, JButton> m_buttons = new HashMap<>();

    /**
     * Default constructor that populates the main window.
     * @param frameName 
    **/
    public JDrawingFrame(String frameName)
    {
        super(frameName);
        // Instantiates components
        m_toolbar = new JToolBar("Toolbar");
        m_panel = new JPanel();
        m_panel.setBackground(Color.WHITE);
        m_panel.setLayout(null);
        m_panel.setMinimumSize(new Dimension(400, 400));
        m_panel.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        m_panel.addMouseMotionListener(this);
        m_label = new JLabel(" ", JLabel.LEFT);
        
        // Fills the panel
        setLayout(new BorderLayout());
        add(m_toolbar, BorderLayout.NORTH);
        add(m_panel, BorderLayout.CENTER);
        add(m_label, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));

        setPreferredSize(new Dimension(400, 400));

        JButton exportButtonXML = new JButton("XML");
        JButton exportButtonJSON = new JButton("JSON");

        exportButtonXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportShapesToXML(); 
            }
        });

        exportButtonJSON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportShapesToJSON(); 
            }
        });

        m_toolbar.add(exportButtonXML);
        m_toolbar.add(exportButtonJSON);

        

    }


	/**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param name The name of the injected <tt>SimpleShape</tt>.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
    **/
    private void addShape(Shapes shape, ImageIcon icon)
    {
        JButton button = new JButton(icon);
		button.setBorderPainted(false);
        m_buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(m_reusableActionListener);

        if (m_selected == null)
        {
            button.doClick();
        }

        m_toolbar.add(button);
        m_toolbar.validate();
        repaint();
    }

    /**
     *  Use the factory to abstract shape creation
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
    **/
    public void mouseClicked(MouseEvent evt) {
        if (m_panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) m_panel.getGraphics();
            switch (m_selected) {
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
                    addCommandSquare.execute(g2); // dessine le carré
                    elements.add(square); //enregistre pour l'export
                    break;
                default:
                    System.out.println("No shape named " + m_selected);
            }
        }
        this.requestFocusInWindow(); //reprend le focus sur le clavier

    }
    


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) { //Ctrl + Z
            repaint(); //remet l'écran à blanc
            RemoveShape removeCommand = new RemoveShape(shapeList);
            removeCommand.execute(null); //enlève la dernière forme de la liste
            if (!elements.isEmpty()) { //enlève la dernière forme pour l'export
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
            Graphics2D g2 = (Graphics2D) m_panel.getGraphics();
            shape.draw(g2); //redessine les formes, sauf la dernière qui a été effacée
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
    	m_label.setText(" ");
    	m_label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mousePressed(MouseEvent evt)
    {
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mouseReleased(MouseEvent evt)
    {
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
    **/
    public void mouseDragged(MouseEvent evt)
    {
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
    	m_label.setText("(" + evt.getX() + "," + evt.getY() + ")");    	
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
        	Iterator<Shapes> keys = m_buttons.keySet().iterator();
        	while (keys.hasNext()) {
        		Shapes shape = keys.next();
				JButton btn = m_buttons.get(shape);
				if (evt.getActionCommand().equals(shape.toString())) {
					btn.setBorderPainted(true);
					m_selected = shape;
		        } else {
					btn.setBorderPainted(false);
				}
				btn.repaint();
			}
        }
    }


    /*Note : nous avons hésité à regrouper les deux fonctions d'export en une mais les petites variations
      les auraient rendues plus ilisibles...
     */
    public void exportShapesToJSON() {
        JSonVisitor jsonVisitor = new JSonVisitor();
        JFileChooser fileChooser = new JFileChooser();
    
        // Asks user where he wants to save the file
        int result = fileChooser.showSaveDialog(this); // "this" is the main window
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
    
            // checks that there is the ".json"
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(".json")) {
                filePath += ".json";
                selectedFile = new File(filePath);
            }
    
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                writer.write("{");
                writer.newLine();
                writer.write("  \"shapes\": [");
                writer.newLine();
                boolean firstShape = true;
                for (Element element : elements) {
                    element.accept(jsonVisitor);
                    String jsonRepresentation = jsonVisitor.getRepresentation();
                    if (!firstShape) {
                        writer.write(",");
                        writer.newLine();
                    }
                    writer.write("    ");
                    writer.write(jsonRepresentation);
                    firstShape = false;
                }
                writer.newLine();
                writer.write("  ]");
                writer.newLine();
                writer.write("}");
                writer.close();
                System.out.println("Exportation au format JSON réussie.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'exportation au format JSON.");
            }
        }
    }

    public void exportShapesToXML() {
    XMLVisitor xmlVisitor = new XMLVisitor();
    JFileChooser fileChooser = new JFileChooser();

    // Asks user where he wants to save the file
    int result = fileChooser.showSaveDialog(this); // "this" is the main window

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        // check that there is the ".xml"
        String filePath = selectedFile.getAbsolutePath();
        if (!filePath.endsWith(".xml")) {
            filePath += ".xml";
            selectedFile = new File(filePath);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
            for (Element element : elements) {
                element.accept(xmlVisitor);
                String xmlRepresentation = xmlVisitor.getRepresentation();
                writer.write(xmlRepresentation);
                writer.newLine();
            }
            writer.close();
            System.out.println("Exportation au format XML réussie.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'exportation au format XML.");
        }
    }
}


   


}