/*
 * Copyright (c) 2002-2014 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript;

import static com.gargoylesoftware.htmlunit.BrowserRunner.Browser.CHROME;
import static com.gargoylesoftware.htmlunit.BrowserRunner.Browser.FF;
import static com.gargoylesoftware.htmlunit.BrowserRunner.Browser.FF24;
import static com.gargoylesoftware.htmlunit.BrowserRunner.Browser.IE11;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;
import com.gargoylesoftware.htmlunit.BrowserRunner.Browsers;
import com.gargoylesoftware.htmlunit.BrowserRunner.NotYetImplemented;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.gargoylesoftware.htmlunit.html.HtmlPageTest;

/**
 * Tests for {@link SimpleScriptable}.
 *
 * @version $Revision: 9074 $
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author <a href="mailto:BarnabyCourt@users.sourceforge.net">Barnaby Court</a>
 * @author David K. Taylor
 * @author <a href="mailto:bcurren@esomnie.com">Ben Curren</a>
 * @author Marc Guillemot
 * @author Chris Erskine
 * @author Ahmed Ashour
 * @author Sudhan Moghe
 * @author <a href="mailto:mike@10gen.com">Mike Dirolf</a>
 * @author Frank Danek
 */
@RunWith(BrowserRunner.class)
public class SimpleScriptable2Test extends WebDriverTestCase {

    /**
     * This test fails on IE and FF but not by HtmlUnit because according to Ecma standard,
     * attempts to set read only properties should be silently ignored.
     * Furthermore document.body = document.body will work on FF but not on IE
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("exception")
    public void setNonWritableProperty() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    try {\n"
            + "     document.body = 123456;\n"
            + "    } catch (e) { alert('exception'); }\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "[object Arguments]",
            IE8 = "[object Object]")
    public void arguments_toString() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    alert(arguments);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("3")
    public void stringWithExclamationMark() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var x = '<!>';\n"
            + "    alert(x.length);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Test the host class names match the Firefox (w3c names).
     * @see <a
     *   href="http://java.sun.com/j2se/1.5.0/docs/guide/plugin/dom/org/w3c/dom/html/package-summary.html">DOM API</a>
     * @throws Exception if the test fails
     */
    @Test
    @Browsers({ CHROME, FF, IE11 })
    @Alerts(DEFAULT = "[object HTMLAnchorElement]",
            CHROME = "function HTMLAnchorElement() { [native code] }",
            FF24 = "function HTMLAnchorElement() {\n    [native code]\n}",
            IE8 = "[object]")
    @NotYetImplemented({ CHROME, FF24 })
    public void hostClassNames() throws Exception {
        testHostClassNames("HTMLAnchorElement");
    }

    private void testHostClassNames(final String className) throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    alert(" + className + ");\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Blocked by Rhino bug 419090 (https://bugzilla.mozilla.org/show_bug.cgi?id=419090).
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({ "x1", "x2", "x3", "x4", "x5" })
    public void arrayedMap() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var map = {};\n"
            + "    map['x1'] = 'y1';\n"
            + "    map['x2'] = 'y2';\n"
            + "    map['x3'] = 'y3';\n"
            + "    map['x4'] = 'y4';\n"
            + "    map['x5'] = 'y5';\n"
            + "    for (var i in map) {\n"
            + "      alert(i);\n"
            + "    }"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Browsers({ CHROME, FF, IE11 })
    @Alerts(CHROME = { "Node>Element: true", "Document>XMLDocument: true", "Node>XPathResult: false",
                "Element>HTMLElement: true", "HTMLElement>HTMLHtmlElement: true",
                "CSSStyleDeclaration>ComputedCSSStyleDeclaration: exception", "Image>HTMLImageElement: false",
                "HTMLImageElement>Image: true" },
            FF17 = { "Node>Element: true", "Document>XMLDocument: true", "Node>XPathResult: false",
                "Element>HTMLElement: true", "HTMLElement>HTMLHtmlElement: true",
                "CSSStyleDeclaration>ComputedCSSStyleDeclaration: exception", "Image>HTMLImageElement: false",
                "HTMLImageElement>Image: false" },
            FF24 = { "Node>Element: true", "Document>XMLDocument: true", "Node>XPathResult: false",
                "Element>HTMLElement: true", "HTMLElement>HTMLHtmlElement: true",
                "CSSStyleDeclaration>ComputedCSSStyleDeclaration: exception", "Image>HTMLImageElement: true",
                "HTMLImageElement>Image: true" },
            IE11 = { "Node>Element: true", "Document>XMLDocument: true", "Node>XPathResult: exception",
                "Element>HTMLElement: true", "HTMLElement>HTMLHtmlElement: true",
                "CSSStyleDeclaration>ComputedCSSStyleDeclaration: exception", "Image>HTMLImageElement: true",
                "HTMLImageElement>Image: true" })
    @NotYetImplemented({ CHROME, FF, IE11 })
    // TODO Class ComputedCSSStyleDeclaration is unknown in all real browsers
    public void isParentOf() throws Exception {
        final String[] expectedAlerts = getExpectedAlerts();

        isParentOf("Node", "Element", expectedAlerts[0]);
        isParentOf("Document", "XMLDocument", expectedAlerts[1]);
        isParentOf("Node", "XPathResult", expectedAlerts[2]);
        isParentOf("Element", "HTMLElement", expectedAlerts[3]);
        isParentOf("HTMLElement", "HTMLHtmlElement", expectedAlerts[4]);
        isParentOf("CSSStyleDeclaration", "ComputedCSSStyleDeclaration", expectedAlerts[5]);

        //although Image != HTMLImageElement, they seem to be synonyms!!!
        isParentOf("Image", "HTMLImageElement", expectedAlerts[6]);
        isParentOf("HTMLImageElement", "Image", expectedAlerts[7]);
    }

    private void isParentOf(final String object1, final String object2, final String status) throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    try {\n"
            + "      alert('" + object1 + ">" + object2 + ": ' + isParentOf(" + object1 + ", " + object2 + "));\n"
            + "    } catch(e) { alert('" + object1 + ">" + object2 + ": exception'); }\n"
            + "  }\n"
            + "  /**\n"
            + "   * Returns true if o1 prototype is parent/grandparent of o2 prototype\n"
            + "   */\n"
            + "  function isParentOf(o1, o2) {\n"
            + "    o1.prototype.myCustomFunction = function() {};\n"
            + "    return o2.prototype.myCustomFunction != undefined;\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        setExpectedAlerts(status);
        loadPageWithAlerts2(html);
    }

    /**
     * This is related to HtmlUnitContextFactory.hasFeature(Context.FEATURE_PARENT_PROTO_PROPERTIES).
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "true",
            IE8 = "false")
    public void parentProtoFeature() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>First</title><script>\n"
            + "  function test() {\n"
            + "    alert(document.createElement('div').__proto__ != undefined);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Test for http://sourceforge.net/p/htmlunit/bugs/587/.
     * See also http://groups.google.com/group/mozilla.dev.tech.js-engine.rhino/browse_thread/thread/1f1c24f58f662c58.
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("1")
    public void passFunctionAsParameter() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>First</title><script>\n"
            + "  function run(fun) {\n"
            + "    fun('alert(1)');\n"
            + "  }\n"
            + "\n"
            + "  function test() {\n"
            + "    run(eval);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * Test JavaScript: 'new Date().getTimezoneOffset()' compared to java.text.SimpleDateFormat.format().
     *
     * @throws Exception if the test fails
     */
    @Test
    public void dateGetTimezoneOffset() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "  function test() {\n"
            + "    var offset = Math.abs(new Date().getTimezoneOffset());\n"
            + "    var timezone = '' + (offset/60);\n"
            + "    if (timezone.length == 1)\n"
            + "      timezone = '0' + timezone;\n"
            + "    alert(timezone);\n"
            + "  }\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";
        final String timeZone = new SimpleDateFormat("Z").format(Calendar.getInstance().getTime());
        final String hour = timeZone.substring(1, 3);
        String strMinutes = timeZone.substring(3, 5);
        final int minutes = Integer.parseInt(strMinutes);
        final StringBuilder sb = new StringBuilder();
        if (minutes != 0) {
            sb.append(hour.substring(1));
            strMinutes = String.valueOf((double) minutes / 60);
            strMinutes = strMinutes.substring(1);
            sb.append(strMinutes);
        }
        else {
            sb.append(hour);
        }
        setExpectedAlerts(sb.toString());
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts({ "true", "function", "function" })
    public void callee() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><title>foo</title><script>\n"
            + "function test() {\n"
            + "  var fun = arguments.callee.toString();\n"
            + "  alert(fun.indexOf('test()') != -1);\n"
            + "  alert(typeof arguments.callee);\n"
            + "  alert(typeof arguments.callee.caller);\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "[object HTMLDivElement]",
            IE8 = "[object]")
    public void getDefaultValue() throws Exception {
        getDefaultValue(false);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("[object HTMLDivElement]")
    public void getDefaultValue_xhtml() throws Exception {
        getDefaultValue(true);
    }

    private void getDefaultValue(final boolean xhtml) throws Exception {
        final String header = xhtml ? "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" : "";
        final String html = header
            + "<html><head><title>First</title><script>\n"
            + "function test() {\n"
            + "    alert(document.createElement('div'));\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";
        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("exception")
    public void set_ReadOnly_document_body() throws Exception {
        set_ReadOnly("document.body");
    }

    /**
     * Setting is actually ignored in FF.
     *
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "false",
            IE8 = "exception")
    public void set_ReadOnly_window_closed() throws Exception {
        set_ReadOnly("window.closed");
    }

    /**
     * Setting the property works in FF.
     *
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "window.length was set",
            IE8 = "exception")
    public void set_ReadOnly_window_length() throws Exception {
        set_ReadOnly("window.length");
    }

    /**
     * All functions seem to be able to be set.
     *
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("document.getElementById was set")
    public void set_ReadOnly_window_document() throws Exception {
        set_ReadOnly("document.getElementById");
    }

    private void set_ReadOnly(final String expression) throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><script>\n"
            + "function test() {\n"
            + "  try {\n"
            + "    " + expression + " = '" + expression + " was set" + "';\n"
            + "    alert(" + expression + ");\n"
            + "  } catch(e) {alert('exception')}\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts(DEFAULT = "function",
            CHROME = "undefined",
            IE8 = "exception")
    public void lookupGetter() throws Exception {
        final String html = HtmlPageTest.STANDARDS_MODE_PREFIX_
            + "<html><head><script>\n"
            + "function test() {\n"
            + "  try {\n"
            + "    alert(typeof window.__lookupGetter__('length'));\n"
            + "  } catch(e) {alert('exception')}\n"
            + "}\n"
            + "</script></head><body onload='test()'>\n"
            + "</body></html>";

        loadPageWithAlerts2(html);
    }
}
