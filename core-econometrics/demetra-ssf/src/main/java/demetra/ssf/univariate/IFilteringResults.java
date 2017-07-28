/*
* Copyright 2015 National Bank of Belgium
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
package demetra.ssf.univariate;

import demetra.ssf.UpdateInformation;
import demetra.data.DataBlock;
import demetra.design.Development;
import demetra.maths.matrices.Matrix;
import demetra.ssf.IStateResults;
import demetra.data.DoubleSequence;

/**
 *
 * @author Jean Palate
 */
@Development(status = Development.Status.Alpha)
public interface IFilteringResults extends IStateResults {

    /**
     *
     * @param t
     * @param pe
     */
    void save(int t, UpdateInformation pe);

    /**
     *
     */
    void clear();

    default double error(int pos) {
        return Double.NaN;
    }

    default double errorVariance(int pos) {
        return Double.NaN;
    }

    /**
     *
     * @param normalized
     * @param clean
     * @return
     */
    default DoubleSequence errors(boolean normalized, boolean clean) {
        return null;
    }

    default DataBlock a(int pos) {
        return null;
    }

    default DataBlock M(int pos) {
        return null;
    }

    default Matrix P(int pos) {
        return null;
    }

}
