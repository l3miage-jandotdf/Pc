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
import java.awt.geom.Rectangle2D;
import fr.persistence.Visitor;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Square extends AbstractShape {


    public Square(int x, int y) {
        this.x = x - 25;
        this.y = y - 25;
    }

    /**
     * Implemente la méthode <tt>SimpleShape.draw()</tt> pour dessiner
     * la forme.
     * @param g2 l'objet graphique utilisé pour le dessin.
     */
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + (float)50, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(x, y, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(x, y, 50, 50));
    }


   /**
    * Accepte un visiteur pour cet objet Square.
    * Cette méthode est utilisée pour permettre aux visiteurs externes d'interagir avec l'objet Square
    * et d'effectuer des opérations spécifiques sur celui-ci.
    *
    * @param visitor L'objet Visitor qui visitera et effectuera des actions sur cet objet Square.
    */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
