package fr.shapes;
import java.util.ArrayList;
import java.util.List;


public class ShapesList {

    private List<SimpleShape> shapes;
    //private List<List<SimpleShape>> groupOfShapes;  //La liste des formes d'un groupe
    //private boolean groupMode = false;              // Permet de savoir si création de groupe en cours
    //private ShapeGroup currentGroup;                //Le groupe de formes en cours de création


    public ShapesList() {
        shapes = new ArrayList<>();
        //groupOfShapes = new ArrayList<>();
    }

    /**
     * Ajoute une forme simple à la liste.
     *
     * @param shape La forme simple à ajouter.
    */
    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    /**
     * Supprime une forme simple de la liste.
     *
     * @param shape La forme simple à supprimer.
    */
    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    /**
     * Obtient la liste de toutes les formes simples.
     *
     * @return La liste de formes simples.
    */
    public List<SimpleShape> getAllShapes() {
        return shapes;
    }

    /**
     * Supprime la dernière forme de la liste, si la liste n'est pas vide.
    */
    public void removeLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
        }
    }

    
    /**
     * Vérifie si une forme est déjà dans un groupe de formes.
     *
     * @param shape La forme à vérifer .
     * @return vrai si la forme est dans un autre, faux sinon.
     */
    /*public boolean isShapeInGroup(SimpleShape shape) {
        for (List<SimpleShape> group : groupOfShapes) {
            if (group.contains(shape)) {
                return true;
            }
        }
        return false;
    }

     /**
     * Crée un nouveau groupe et ajoute des formes à celui-ci.
     *
     * @param shapes les formes à ajouter au groupe de formes.
     */
    /*public void createGroup(List<SimpleShape> shapes) {
        groupOfShapes.add(shapes);
    }


    /**
     * Renvoie le groupe qui contient une forme
     * @param shape la forme contenu dans le groupe
     * @return
     */
    /*public List<SimpleShape> getGroup(SimpleShape shape) {
        List<SimpleShape> result = null;
        for (List<SimpleShape> group : groupOfShapes) {
            if (group.contains(shape)) {
                result = group;
            }
        }
        return result;
    }


    /**
     * Obtains the list of all groups in the ShapesList.
     *
     * @return The list of groups.
     */
    /*public List<List<SimpleShape>> getGroups() {
        return this.groupOfShapes;
    }


   /**
     * Vérifie si un groupe est en cours de création.
     *
     * @return true si groupe en cours de création, sinon false.
     */
    /*public boolean isGroupMode() {
        return groupMode;
    }

    /**
     * Définit le mode 'Création de groupe' de l'application.
     *
     * @param groupMode true pour activer le mode 'Création de groupe', false pour le désactiver.
     */
    /*public void setGroupMode(boolean groupMode) {
        this.groupMode = groupMode;
    }

    /**
     * Obtient le groupe en cours de création 
     *
     * @return Le groupe actuel.
     */
    /*public ShapeGroup getCurrentGroup() {
        return currentGroup;
    }

    /**
     * Définit le groupe en cours de création lorsqu'en mode groupe.
     *
     * @param currentGroup Le groupe à définir comme groupe actuel.
     */
    /*public void setCurrentGroup(ShapeGroup currentGroup) {
        this.currentGroup = currentGroup;
    } */
}
