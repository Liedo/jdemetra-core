/*
 * Copyright 2016 National Bank of Belgium
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
package ec.tss.xml.regression;

import ec.tstoolkit.timeseries.regression.AdditiveOutlier;
import ec.tstoolkit.timeseries.simplets.TsFrequency;
import ec.tstoolkit.timeseries.simplets.TsPeriod;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jean Palate
 */
public class XmlVariableTest {
    
    public XmlVariableTest() {
        XmlVariable.register(new XmlGenericTradingDays.Adapter());
        XmlVariable.register(new XmlOutlier.Adapter());
    }

    @Test
    public void testMarshalling() throws Exception {
        AdditiveOutlier ao=new AdditiveOutlier(new TsPeriod(TsFrequency.Monthly, 1980, 2));
        XmlVariable xml = XmlVariable.marshal(ao);
    }
    
}
