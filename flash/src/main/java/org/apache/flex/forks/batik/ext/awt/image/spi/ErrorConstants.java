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
package org.apache.flex.forks.batik.ext.awt.image.spi;

/**
 * The built in error codes.
 *
 * @author <a href="mailto:thomas.deweese@kodak.com">Thomas DeWeese</a>
 * @version $Id: ErrorConstants.java 7342 2012-09-05 08:57:06Z asashour $
 */
public interface ErrorConstants {

    /**
     * The error messages bundle class name.
     */
    public final static String RESOURCES =
        "org.apache.flex.forks.batik.ext.awt.image.spi.resources.Messages";


    /**
     * The error code when a stream is unreadable (corrupt or unsupported).
     */
    public static final String ERR_STREAM_UNREADABLE
        = "stream.unreadable";

    /**
     * The error code when a url of a particular format is unreadable 
     * (corrupt).
     * <pre>
     * {0} = the format that couldn't be read.
     * </pre>
     */
    public static final String ERR_STREAM_FORMAT_UNREADABLE
        = "stream.format.unreadable";

    /**
     * The error code when the data in the  url is uninterpretable by this
     * software (meaning it's corrupt or an unsupported format of some sort).
     * <pre>
     * {0} = the ParsedURL that couldn't be read.
     * </pre>
     */
    public static final String ERR_URL_UNINTERPRETABLE
        = "url.uninterpretable";

    /**
     * The error code when a url is unreachable (ussually bad URL,
     * or server is down).
     * <pre>
     * {0} = the ParsedURL that couldn't be read.
     * </pre>
     */
    public static final String ERR_URL_UNREACHABLE
        = "url.unreachable";


    /**
     * The error code when a url of a particular format is unreadable 
     * (corrupt).
     * <pre>
     * {0} = the format that couldn't be read.
     * {1} = the ParsedURL for file.
     * </pre>
     */
    public static final String ERR_URL_FORMAT_UNREADABLE
        = "url.format.unreadable";

}
