/*
* Copyright 2013 National Bank of Belgium
*
* Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
* by the European Commission - subsequent versions of the EUPL (the "Licence");
* You may not use this work except in compliance with the Licence.
* You may obtain a copy of the Licence at:
*
* http://ec.europa.eu/idabc/eupl
*
* Unless required by applicable law or agreed to in writing, software 
* distributed under the Licence is distributed on an "AS IS" basis,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the Licence for the specific language governing permissions and 
* limitations under the Licence.
*/

package demetra.xml;

/**
 * 
 * @param <T>
 * @author Jean Palate
 */
public interface IXmlConverter<T> extends IXmlInfoConverter<T> {
    // / <summary>
    // / Creates a new instance of the corresponding implementation class
    // / </summary>
    // / <returns>The new object, as described by the current xml
    // object</returns>

    /**
     *
     * @return
     */
    T create();
}
