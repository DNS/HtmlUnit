/*

   Copyright 2001,2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.flex.forks.batik.svggen;

import java.util.Vector;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This utility class converts a standard SVG document that uses
 * attribute into one that uses the CSS style attribute instead
 *
 * @author <a href="mailto:vincent.hardy@eng.sun.com">Vincent Hardy</a>
 * @version $Id: SVGCSSStyler.java 7342 2012-09-05 08:57:06Z asashour $
 */
public class SVGCSSStyler implements SVGSyntax{
    static private String CSS_PROPERTY_VALUE_SEPARATOR = ":";
    static private String CSS_RULE_SEPARATOR = ";";
    static private String SPACE = " ";

    /**
     * Invoking this method removes all the styling attributes
     * (such as 'fill' or 'fill-opacity') from the input element
     * and its descendant and replaces them with their CSS2
     * property counterparts.
     * @param node SVG Node to be converted to use style
     *
     */
    public static void style(Node node){
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null){
            // Has to be an Element, as it has attributes
            // According to spec.
            Element element = (Element)node;
            StringBuffer styleAttrBuffer = new StringBuffer();
            int nAttr = attributes.getLength();
            Vector toBeRemoved = new Vector();
            for(int i=0; i<nAttr; i++){
                Attr attr = (Attr)attributes.item(i);
                if(SVGStylingAttributes.set.contains(attr.getName())){
                    // System.out.println("Found new style attribute");
                    styleAttrBuffer.append(attr.getName());
                    styleAttrBuffer.append(CSS_PROPERTY_VALUE_SEPARATOR);
                    styleAttrBuffer.append(attr.getValue());
                    styleAttrBuffer.append(CSS_RULE_SEPARATOR);
                    styleAttrBuffer.append(SPACE);
                    toBeRemoved.addElement(attr.getName());
                }
            }

            if(styleAttrBuffer.length() > 0){
                                // System.out.println("Setting style attribute on node: " + styleAttrBuffer.toString().trim());
                                // There were some styling attributes
                element.setAttributeNS(null,
                                       SVG_STYLE_ATTRIBUTE,
                                       styleAttrBuffer.toString().trim());

                int n = toBeRemoved.size();
                for(int i=0; i<n; i++)
                    element.removeAttribute((String)toBeRemoved.elementAt(i));
            }
            // else
            // System.out.println("NO STYLE PROPERTIES");
        }

        // Now, process child elements
        NodeList children = node.getChildNodes();
        int nChildren = children.getLength();
        for(int i=0; i<nChildren; i++){
            Node child = children.item(i);
            style(child);
        }

    }
}
