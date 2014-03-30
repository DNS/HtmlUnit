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
package org.apache.flex.forks.batik.ext.awt.image.renderable;

import java.util.List;

import org.apache.flex.forks.batik.ext.awt.image.CompositeRule;

/**
 * Composites a list of images according to a single composite rule.
 * the image are applied in the order they are in the List given.
 *
 * @author <a href="mailto:Thomas.DeWeeese@Kodak.com">Thomas DeWeese</a>
 * @version $Id: CompositeRable.java 7342 2012-09-05 08:57:06Z asashour $
 */
public interface CompositeRable extends FilterColorInterpolation {

    /**
     * The sources to be composited togeather.
     * @param srcs The list of images to be composited by the composite rule.
     */
    public void setSources(List srcs);

    /**
     * Set the composite rule to use for combining the sources.
     * @param cr Composite rule to use.
     */
    public void setCompositeRule(CompositeRule cr);

    /**
     * Get the composite rule in use for combining the sources.
     * @return Composite rule currently in use.
     */
    public CompositeRule getCompositeRule();
}
