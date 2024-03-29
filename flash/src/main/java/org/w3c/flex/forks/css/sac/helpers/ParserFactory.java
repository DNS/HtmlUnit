/*
 * Copyright (c) 1999 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 * $Id: ParserFactory.java 7342 2012-09-05 08:57:06Z asashour $
 */
package org.w3c.flex.forks.css.sac.helpers;

import org.w3c.flex.forks.css.sac.Parser;

/**
 * @version $Revision: 7342 $
 * @author  Philippe Le Hegaret
 */
public class ParserFactory {
    
    /**
     * Create a parser with given selectors factory and conditions factory.
     */    
    public Parser makeParser()
	throws ClassNotFoundException,
	       IllegalAccessException, 
	       InstantiationException,
 	       NullPointerException,
	       ClassCastException {
	String className = System.getProperty("org.w3c.flex.forks.css.sac.parser");
	if (className == null) {
	    throw new NullPointerException("No value for sac.parser property");
	} else {
	    return (Parser)(Class.forName(className).newInstance());
	}
    }
}
