/*
 * Copyright 2016 National Bank ofInternal Belgium
 * 
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions ofInternal the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy ofInternal the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package demetra.linearsystem.internal;

import demetra.data.DataBlock;
import demetra.data.DataBlockIterator;
import demetra.data.accumulator.NeumaierAccumulator;
import demetra.design.BuilderPattern;
import demetra.maths.matrices.decomposition.IQRDecomposition;
import demetra.maths.matrices.Matrix;
import demetra.maths.matrices.MatrixException;
import demetra.data.DoubleCell;
import demetra.design.AlgorithmImplementation;
import demetra.design.Development;
import demetra.linearsystem.LinearSystemSolver;

/**
 *
 * @author Jean Palate
 */
@AlgorithmImplementation(algorithm = LinearSystemSolver.class)
@Development(status = Development.Status.Release)
public class QRLinearSystemSolver implements LinearSystemSolver {

    @BuilderPattern(QRLinearSystemSolver.class)
    public static class Builder {

        private final IQRDecomposition qr;
        private boolean improve, normalize;

        private Builder(IQRDecomposition qr) {
            this.qr = qr;
        }

        public Builder normalize(boolean normalize) {
            this.normalize = normalize;
            return this;
        }

        public Builder improve(boolean improve) {
            this.improve = improve;
            return this;
        }

        public QRLinearSystemSolver build() {
            return new QRLinearSystemSolver(qr, normalize, improve);
        }
    }

    public static Builder builder(IQRDecomposition qr) {
        return new Builder(qr);
    }
    private final IQRDecomposition qr;
    private final boolean improve, normalize;

    private QRLinearSystemSolver(IQRDecomposition qr, boolean normalize, boolean improve) {
        this.qr = qr;
        this.normalize = normalize;
        this.improve = improve;
    }

    @Override
    public void solve(Matrix A, DataBlock b) {
        if (!A.isSquare()) {
            throw new MatrixException(MatrixException.SQUARE);
        }
        if (A.getRowsCount() != b.length()) {
            throw new MatrixException(MatrixException.DIM);
        }
        // we normalize b
        Matrix An;
        if (normalize) {
            An = A.deepClone();
            DataBlockIterator rows = An.rowsIterator();
            DoubleCell cells = b.cells();
            while (rows.hasNext()) {
                DataBlock row = rows.next();
                double norm = row.norm2();
                row.div(norm);
                cells.applyAndNext(x -> x / norm);
            }
        } else {
            An = A;
        }
        qr.decompose(An);
//        if (!qr.isFullRank()) {
//            throw new MatrixException(MatrixException.SINGULAR);
//        }

        DataBlock b0 = improve ? DataBlock.of(b) : null;
        qr.leastSquares(b, b, null);
        if (!improve) {
            return;
        }
        DataBlock db = DataBlock.make(b.length());
        db.robustProduct(An.rowsIterator(), b, new NeumaierAccumulator());
        db.sub(b0);
        qr.leastSquares(db, db, null);
        b.sub(db);
    }

    @Override
    public void solve(Matrix A, Matrix B) {
        if (!A.isSquare()) {
            throw new MatrixException(MatrixException.SQUARE);
        }
        if (A.getRowsCount() != B.getRowsCount()) {
            throw new MatrixException(MatrixException.DIM);
        }
        // we normalize b
        Matrix An;
        if (normalize) {
            An = A.deepClone();
            DataBlockIterator rows = An.rowsIterator();
            DataBlockIterator brows = B.rowsIterator();
            while (rows.hasNext()) {
                DataBlock row = rows.next();
                double norm = row.norm2();
                row.div(norm);
                brows.next().div(norm);
            }
        } else {
            An = A;
        }
        qr.decompose(An);
        if (!qr.isFullRank()) {
            throw new MatrixException(MatrixException.SINGULAR);
        }
        Matrix B0 = improve ? B.deepClone() : null;
        B.applyByColumns(col -> qr.leastSquares(col, col, null));
        if (!improve) {
            return;
        }
        // improve the result
        Matrix DB = Matrix.make(B.getRowsCount(), B.getColumnsCount());
        DB.robustProduct(An, B, new NeumaierAccumulator());
        DB.sub(B0);
        DB.applyByColumns(col -> qr.leastSquares(col, col, null));
        B.sub(DB);

    }

}