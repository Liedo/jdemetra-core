/*
 * Copyright 2017 National Bank of Belgium
 * 
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package demetra.ssf;

import demetra.data.DataBlock;
import demetra.linearsystem.ILinearSystemSolver;
import demetra.maths.matrices.Matrix;
import demetra.maths.matrices.SymmetricMatrix;
import demetra.maths.matrices.internal.Householder;

/**
 *
 * @author Jean Palate
 */
@lombok.experimental.UtilityClass
public class StationaryInitialization {

    public Matrix of(ISsfDynamics dynamics, int dim) {
        if (!dynamics.isTimeInvariant()) {
            return null;
        }
        Matrix cov = Matrix.square(dim);
//            // We have to solve the steady state equation:
//            // V = T V T' + Q

        Matrix T = Matrix.square(dim);
        Matrix Q = Matrix.square(dim);
        dynamics.T(0, T);
        dynamics.V(0, Q);
        int np = (dim * (dim + 1)) / 2;
        Matrix M = Matrix.square(np);
        double[] b = new double[np];
        for (int c = 0, i = 0; c < dim; ++c) {
            for (int r = c; r < dim; ++r, ++i) {
                b[i] = Q.get(r, c);
                M.set(i, i, 1);
                for (int k = 0; k < dim; ++k) {
                    for (int l = 0; l < dim; ++l) {
                        double zr = 0, zc = 0;
                        zr = T.get(r, l);
                        zc = T.get(c, k);
                        double z = zr * zc;
                        if (z != 0) {
                            int p = l <= k ? pos(k, l, dim) : pos(l, k, dim);
                            M.add(i, p, -z);
                        }
                    }
                }
            }
        }
        ILinearSystemSolver.fastSolver().solve(M, DataBlock.ofInternal(b));
//        Householder hous = new Householder();
//        hous.decompose(M);
//        boolean ok = hous.solve(DataBlock.ofInternal(b));
        for (int i = 0, j = 0; i < dim; i++) {
            cov.column(i).drop(i, 0).copyFrom(b, j);
            j += dim - i;
        }
        SymmetricMatrix.fromLower(cov);
        return cov;
    }

    private static int pos(int r, int c, int n) {
        return r + c * (2 * n - c - 1) / 2;
    }
}
