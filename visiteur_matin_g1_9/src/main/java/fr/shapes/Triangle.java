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
package fr.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import fr.persistence.Visitable;
import fr.persistence.Visitor;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle implements SimpleShape, Visitable, Element {

    int x;

    int y;

    public Triangle(int x, int y) {
        this.x = x - 25;
        this.y = y - 25;
    }

    /**
     * Implémente la méthode <tt>SimpleShape.draw()</tt> pour dessiner
     * la forme.
     * @param g2 L'objet graphique utilisé pour le dessin.
    */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + (float)50, y, Color.WHITE);
        g2.setPaint(gradient);
        int[] xcoords = { x + 25, x, x + 50 };
        int[] ycoords = { y, y + 50, y + 50 };
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(x + (float)25, y);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
    }


    /**
    * Accepte un visiteur pour cet objet Triangle.
    * Cette méthode est utilisée pour permettre aux visiteurs externes d'interagir avec l'objet Triangle
    * et d'effectuer des opérations spécifiques sur celui-ci.
    *
    * @param visitor L'objet Visitor qui visitera et effectuera des actions sur cet objet Triangle.
    */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Obtient la coordonnée x de l'objet Triangle.
     *
     * @return La coordonnée x de l'objet Triangle.
    */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée y de l'objet Triangle.
     *
     * @return La coordonnée y de l'objet Triangle.
    */
    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
