/*

   Copyright 2001  The Apache Software Foundation 

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

import java.awt.Shape;

/**
 * Implements a clip operation.  This is similar to the mask operation
 * except it uses a '1 bit' mask (it's normally anti-aliased, but
 * shouldn't have any fluctions in side the outline of the shape.).
 *
 * @author <a href="mailto:Thomas.DeWeeese@Kodak.com">Thomas DeWeese</a>
 * @version $Id: ClipRable.java 7342 2012-09-05 08:57:06Z asashour $ */
public interface ClipRable extends Filter {

    /**
     * Set the default behaviour of anti-aliased clipping.
     * for this clip object.
     */
    public void setUseAntialiasedClip(boolean useAA);

    /**
     * Resturns true if the default behaviour should be to use
     * anti-aliased clipping.
     */
    public boolean getUseAntialiasedClip();


      /**
       * The source to be clipped by the outline of the clip node.
       * @param src The Image to be clipped.
       */
    public void setSource(Filter src);

      /**
       * This returns the current image being clipped by the clip node.
       * @return The image to clip
       */
    public Filter getSource();

    /**
     * Set the clip path to use.
     * The path will be filled with opaque white, to define the
     * the clipping mask.
     * @param clipPath The clip path to use
     */
    public void setClipPath(Shape clipPath);

      /**
       * Returns the Shape that the Clip will use to
       * define the clip path.
       * @return The shape that defines the clip path.
       */
    public Shape getClipPath();
}
