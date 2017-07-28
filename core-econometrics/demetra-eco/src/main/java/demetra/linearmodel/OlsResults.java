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
package demetra.linearmodel;

import demetra.design.Immutable;
import demetra.dstats.F;
import demetra.dstats.T;
import demetra.likelihood.ConcentratedLikelihood;
import demetra.maths.matrices.Matrix;
import demetra.stats.tests.StatisticalTest;
import demetra.stats.tests.TestType;
import demetra.data.DoubleSequence;
import demetra.data.Doubles;

/**
 *
 * @author Jean Palate
 */
@Immutable
public class OlsResults {

    public OlsResults(DoubleSequence Y, Matrix X, boolean mean, DoubleSequence coefficients, Matrix unscaledCov, double ssq) {
        this.y = Y;
        this.X = X;
        this.mean = mean;
        this.coefficients = coefficients;
        this.ssq = ssq;
        this.n = y.length();
        this.ucov = unscaledCov;
        this.nx = ucov.diagonal().count(x -> x != 0);
        // compute auxiliaries
        y2 = Doubles.ssq(y);
        ym = Doubles.average(y);
        bxy = y2 - ssq;
    }

    private final DoubleSequence y;
    private final Matrix X;
    private final boolean mean;
    private final int n, nx;
    private final DoubleSequence coefficients;
    private final double ssq;
    private final Matrix ucov;
    // auxiliary results
    private final double y2, ym, bxy;

    /**
     * @return the coefficients
     */
    public DoubleSequence getCoefficients() {
        return coefficients;
    }

    /**
     * SSe
     *
     * @return
     */
    public double getResidualSumOfSquares() {
        return ssq;
    }

    /**
     * MSe = SSe/(n-p)
     *
     * @return
     */
    public double getResidualMeanSquare() {
        return ssq / (n - nx);
    }

    /**
     * s
     *
     * @return the ser
     */
    public double getResidualStandardDeviation() {
        return Math.sqrt(ssq / (n - nx));
    }

    /**
     *
     * @return the r2
     */
    public double getR2() {
        if (mean) {
            return 1 - getResidualSumOfSquares() / (y2 - n * ym * ym);
        } else {
            return 1 - getResidualSumOfSquares() / y2;
        }
    }

    /**
     * @return the adjustedR2
     */
    public double getAdjustedR2() {
        return 1 - (n - 1) * (1 - getR2()) / (n - nx);
    }

    public double getRegressionSumOfSquares() {
        if (mean) {
            return bxy - n * ym * ym;
        } else {
            return bxy;
        }
    }

    public double getRegressionMeanSquare() {
        if (mean) {
            return (bxy - n * ym * ym) / (nx - 1);
        } else {
            return bxy / nx;
        }
    }

    private int degreesOfFreedom() {
        return mean ? nx - 1 : nx;
    }

    public StatisticalTest Ftest() {
        F f = new F(degreesOfFreedom(), n - nx);
        return new StatisticalTest(f, getRegressionMeanSquare() / getResidualMeanSquare(), TestType.Upper, false);
    }

    /**
     * @return the unscaledCovariance
     */
    public Matrix covariance() {
        return ucov.times(ssq / (n - nx));
    }

    public double standardDeviation(int idx) {
        double v = ucov.get(idx, idx);
        return Math.sqrt(ssq / (n - nx) * v);
    }

    public double T(int idx) {
        double e = ucov.get(idx, idx);
        if (e == 0) {
            return Double.NaN;
        }
        double b = coefficients.get(idx);
        if (b == 0) {
            return 0;
        }
        return b / Math.sqrt(e * ssq / (n - nx));
    }

    public StatisticalTest Ttest(int idx) {
        double t = T(idx);
        if (!Double.isFinite(t)) {
            return null;
        } else {
            return new StatisticalTest(new T(n - nx), t, TestType.TwoSided, false);
        }
    }

    /**
     * @return the likelihood
     */
    public ConcentratedLikelihood getLikelihood() {
        return ConcentratedLikelihood.likelihood(n)
                .coefficients(coefficients)
                .unscaledCovariance(ucov)
                .ssqErr(ssq)
                .build();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("R2=").append(getR2());
        builder.append(System.lineSeparator());
        builder.append("Adjusted R2=").append(getAdjustedR2());
        builder.append(System.lineSeparator());
        builder.append("Residual standard deviation=").append(getResidualStandardDeviation());
        builder.append(System.lineSeparator());
        builder.append("F=").append(Ftest().getValue());
        builder.append(System.lineSeparator());
        int idx = 0;
        if (mean) {
            builder.append(System.lineSeparator());
            builder.append("mean").append('\t').append(coefficients.get(0))
                    .append('\t').append(standardDeviation(idx));
            idx++;
        }
        for (int j = 1; idx < coefficients.length(); ++idx, ++j) {
            double c = coefficients.get(idx);
            if (c != 0) {
                builder.append(System.lineSeparator());
                builder.append("x").append(j).append('\t').append(c)
                        .append('\t').append(standardDeviation(idx));
            }
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

}
