package fr.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import fr.persistence.JSonVisitor;
import fr.persistence.Visitor;
import fr.persistence.XMLVisitor;
import fr.shapes.Element;

public class Export extends JFrame {

    private transient List<Element> elements;
    private static final Logger log = Logger.getLogger(Export.class.getName());

    public Export(List<Element> elements) {
        this.elements = elements;
    }

    /** Écrit le contenu du dessin dans un fichier (.json ou .xml)
     * @param isJSON true si l'on veut un json, false pour un xml
     * @return selectedFile le fichier créé
     */
    
    public File exportShapes(boolean isJSON) {
        Visitor visitor = isJSON ? new JSonVisitor() : new XMLVisitor();
        String extension = isJSON ? "json" : "xml";
    
        File selectedFile = getSelectedFileWithExtension(extension);
        if (selectedFile == null) {
            return null;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            writeShapesToFile(writer, isJSON, visitor);
        } catch (IOException e) {
            log.severe("Une erreur s'est produite lors de l'exportation des formes : " + e.getMessage());
        }
        return selectedFile;
    }
    
    /**
     * Récupère le fichier sélectionné avec l'extension spécifiée.
     *
     * @param extension L'extension de fichier à utiliser (json ou xml)
     * @return le fichier sélectionné avec l'extension appropriée ou null si aucune sélection n'a été faite
     */
    private File getSelectedFileWithExtension(String extension) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
    
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
    
            if (!filePath.endsWith("." + extension)) {
                filePath += "." + extension;
                selectedFile = new File(filePath);
            }
            return selectedFile;
        }
        return null;
    }


    /**
     * Écrit les formes dans le fichier donné en utilisant le writer spécifié.
     *
     * @param writer   Le BufferedWriter pour écrire dans le fichier
     * @param isJSON   true si le fichier doit être au format JSON, false pour le format XML
     * @param visitor  Le visiteur utilisé pour obtenir la représentation des formes
     * @throws IOException si une erreur survient lors de l'écriture dans le fichier
     */
    private void writeShapesToFile(BufferedWriter writer, boolean isJSON, Visitor visitor) throws IOException {
        if (isJSON) {
            writer.write("{\n  \"shapes\": [\n");
        }
        else{
            writer.write("<root>");
            writer.write("<shapes>");
        }
    
        boolean firstShape = true;
        for (Element element : elements) {
            element.accept(visitor);
            String representation = visitor.getRepresentation();
    
            if (!firstShape) {
                writer.write(isJSON ? ",\n" : "\n");
            }
            writer.write(representation);
            firstShape = false;
        }
    
        if (isJSON) {
            writer.write("\n  ]\n}");
        }
        else{
            writer.write("</shapes>");
            writer.write("</root>");
        }
    }
}
