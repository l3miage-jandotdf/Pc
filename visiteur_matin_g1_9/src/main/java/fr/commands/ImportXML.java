package fr.commands;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JPanel;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.shapes.Circle;
import fr.shapes.Element;
import fr.shapes.ShapesList;
import fr.shapes.Square;
import fr.shapes.Triangle;

public class ImportXML {

    public static void importXMLFile(JPanel panel, List<Element> elements, ShapesList shapeList) throws IOException {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file == null) {
                return;
            }
    
            try {
                // Créer une instance de DocumentBuilderFactory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
    
                // Analyser le fichier XML pour créer un Document
                Document doc = builder.parse(file);
    
                // Obtenir la racine du document
                org.w3c.dom.Element root = doc.getDocumentElement();
    
                // Récupérer la liste des formes à partir de la balise 'shapes'
                NodeList shapesList = root.getElementsByTagName("shape");
                for (int i = 0; i < shapesList.getLength(); i++) {
                    Node shapeNode = shapesList.item(i);
                    if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
                        org.w3c.dom.Element shapeElement = (org.w3c.dom.Element) shapeNode;
                        String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
                        int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
                        int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());
    
                        switch (type) {
                            case "circle":
                                Circle circle = new Circle(x+25, y+25);
                                AddShape addCommandCircle = new AddShape(shapeList, circle);
                                addCommandCircle.execute(g2); // dessine le cercle
                                elements.add(circle); //enregistre pour l'export
                                break;

                            case "triangle":
                                Triangle triangle = new Triangle(x+25, y+25);
                                AddShape addCommandTriangle = new AddShape(shapeList, triangle);
                                addCommandTriangle.execute(g2); // dessine le triangle
                                elements.add(triangle); //enregistre pour l'export
                                break;

                            case "square":
                                Square square = new Square(x+25, y+25);
                                AddShape addCommandSquare = new AddShape(shapeList, square);
                                addCommandSquare.execute(g2); // dessine le carrÃ©
                                elements.add(square); //enregistre pour l'export
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
