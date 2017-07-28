/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demetra.dstats;

import demetra.random.IRandomNumberGenerator;
import demetra.random.SystemRNG;
import java.util.Random;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Laurent Jadoul
 */
public class TTest {

    public static final double TOLERANCE = 0.05;

    public TTest() {
    }

    @Test
    public void testGetProbabilityT_1() {
        T t = new T(1);
        for (double i = -100; i < 100; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    public void testGetProbabilityT_2() {
        T t = new T(2);
        for (double i = -100; i < 100; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    public void testGetProbabilityT_3() {
        T t = new T(3);
        for (double i = -100; i < 100; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    public void testGetProbabilityT_10() {
        T t = new T(10);
        for (double i = -100; i < 100; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    // Bound before -9.89 and after 9.89 will reach 0 and 1 probability due to machine limitations
    public void testGetProbabilityT_100() {
        T t = new T(100);
        for (double i = -9.89; i < 9.89; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    // Bound before -9.89 and after 9.89 will reach 0 and 1 probability due to machine limitations
    public void testGetProbabilityT_200() {
        T t = new T(200);
        for (double i = -9; i < 9; i += .01) {
            assertThat(t.getProbability(i, ProbabilityType.Lower))
                    .as("Value i = %s", i)
                    .isNotNaN()
                    .isGreaterThan(0d)
                    .isLessThan(1d);
        }
    }

    @Test
    public void testGetProbabilityInverseT_1() {
        T t = new T(1);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

    @Test
    public void testGetProbabilityInverseT_2() {
        T t = new T(2);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

    @Test
    public void testGetProbabilityInverseT_3() {
        T t = new T(3);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

    @Test
    public void testGetProbabilityInverseT_10() {
        T t = new T(10);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

    @Test
    public void testGetProbabilityInverseT_100() {
        T t = new T(100);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

    @Test
    public void testGetProbabilityInverseT_200() {
        T t = new T(200);
        for (double i = 0.001; i < 1; i += 0.001) {
            double y = t.getProbabilityInverse(i, ProbabilityType.Lower);
            double z = t.getProbability(y, ProbabilityType.Lower);
            assertThat(i).isCloseTo(z, Assertions.within(z * TOLERANCE));
        }

        assertThatThrownBy(() -> t.getProbabilityInverse(IDistribution.EPS_P - 1, ProbabilityType.Lower))
                .as("p < EPS_P")
                .isInstanceOf(DStatException.class);

        assertThatThrownBy(() -> t.getProbabilityInverse((1 - IDistribution.EPS_P) - 1, ProbabilityType.Lower))
                .as("1 - p < EPS_P")
                .isInstanceOf(DStatException.class);
    }

//    @Test
//    public void testExpectationT_1() {
//        T t = new T(1);
//        int iterations = 100000;
//        double sum = 0;
//        double avg;
//        IRandomNumberGenerator rng = getRandomNumberGenerator();
//
//        for (int i = 0; i < iterations; i++) {
//            sum += t.random(rng);
//        }
//        avg = sum / iterations;
//        assertThat(avg).isCloseTo(t.getExpectation(), Assertions.within(0.1));
//    }
    @Test
    public void testVarianceT_1() {
        assertThatThrownBy(() -> new T(1).getVariance()).isInstanceOf(DStatException.class);
    }

    @Test
    public void testRandomExpectationT_2() {
        T t = new T(2);
        int iterations = 20000;
        double sum = 0;
        double avg;
        IRandomNumberGenerator rng = getRandomNumberGenerator();

        for (int i = 0; i < iterations; i++) {
            sum += t.random(rng);
        }
        avg = sum / iterations;
        assertThat(t.getExpectation()).isCloseTo(avg, Assertions.within(TOLERANCE));
    }

    @Test
    public void testRandomExpectationT_3() {
        T t = new T(3);
        int iterations = 10000;
        double sum = 0;
        double avg;
        IRandomNumberGenerator rng = getRandomNumberGenerator();

        for (int i = 0; i < iterations; i++) {
            sum += t.random(rng);
        }
        avg = sum / iterations;
        assertThat(t.getExpectation()).isCloseTo(avg, Assertions.within(TOLERANCE));
    }

    @Test
    public void testRandomExpectationT_10() {
        T t = new T(10);
        int iterations = 10000;
        double sum = 0;
        double avg;
        IRandomNumberGenerator rng = getRandomNumberGenerator();

        for (int i = 0; i < iterations; i++) {
            sum += t.random(rng);
        }
        avg = sum / iterations;
        assertThat(t.getExpectation()).isCloseTo(avg, Assertions.within(TOLERANCE));
    }

    @Test
    public void testRandomExpectationT_100() {
        T t = new T(100);
        int iterations = 10000;
        double sum = 0;
        double avg;
        IRandomNumberGenerator rng = getRandomNumberGenerator();

        for (int i = 0; i < iterations; i++) {
            sum += t.random(rng);
        }
        avg = sum / iterations;
        assertThat(t.getExpectation()).isCloseTo(avg, Assertions.within(TOLERANCE));
    }

    @Test
    public void testRandomExpectationT_200() {
        T t = new T(200);
        int iterations = 10000;
        double sum = 0;
        double avg;
        IRandomNumberGenerator rng = getRandomNumberGenerator();

        for (int i = 0; i < iterations; i++) {
            sum += t.random(rng);
        }
        avg = sum / iterations;
        assertThat(t.getExpectation()).isCloseTo(avg, Assertions.within(TOLERANCE));
    }

    @Test
    @Ignore
    public void testRandomVarianceT_2() {
        int iterations = 10000;
        T t = new T(2);
        IRandomNumberGenerator rng = getRandomNumberGenerator();
        double[] values = new double[iterations];
        double sum = 0, avg;

        for (int i = 0; i < iterations; i++) {
            values[i] = t.random(rng);
            sum += values[i];
        }
        avg = sum / iterations;

        double variance = 0;
        for (int i = 0; i < iterations; i++) {
            variance += Math.pow((values[i] - avg), 2);
        }
        variance = variance / iterations;
        //assertThat(t.getVariance()).isCloseTo(variance, Assertions.within(0.1));
        assertThat(t.getVariance()).isCloseTo(Double.POSITIVE_INFINITY, Assertions.within(0.1));
    }

    @Test
    public void testRandomVarianceT_5() {
        int iterations = 10000;
        T t = new T(5);
        IRandomNumberGenerator rng = getRandomNumberGenerator();
        double[] values = new double[iterations];
        double sum = 0, avg;

        for (int i = 0; i < iterations; i++) {
            values[i] = t.random(rng);
            sum += values[i];
        }
        avg = sum / iterations;

        double variance = 0;
        for (int i = 0; i < iterations; i++) {
            variance += Math.pow((values[i] - avg), 2);
        }
        variance = variance / iterations;
        assertThat(t.getVariance()).isCloseTo(variance, Assertions.within(t.getVariance() * TOLERANCE));
    }

    @Test
    public void testRandomVarianceT_10() {
        int iterations = 10000;
        T t = new T(10);
        IRandomNumberGenerator rng = getRandomNumberGenerator();
        double[] values = new double[iterations];
        double sum = 0, avg;

        for (int i = 0; i < iterations; i++) {
            values[i] = t.random(rng);
            sum += values[i];
        }
        avg = sum / iterations;

        double variance = 0;
        for (int i = 0; i < iterations; i++) {
            variance += Math.pow((values[i] - avg), 2);
        }
        variance = variance / iterations;
        assertThat(t.getVariance()).isCloseTo(variance, Assertions.within(t.getVariance() * TOLERANCE));
    }

    @Test
    public void testRandomVarianceT_100() {
        int iterations = 10000;
        T t = new T(100);
        IRandomNumberGenerator rng = getRandomNumberGenerator();
        double[] values = new double[iterations];
        double sum = 0, avg;

        for (int i = 0; i < iterations; i++) {
            values[i] = t.random(rng);
            sum += values[i];
        }
        avg = sum / iterations;

        double variance = 0;
        for (int i = 0; i < iterations; i++) {
            variance += Math.pow((values[i] - avg), 2);
        }
        variance = variance / iterations;
        assertThat(t.getVariance()).isCloseTo(variance, Assertions.within(t.getVariance() * TOLERANCE));
    }

    @Test
    public void testRandomVarianceT_200() {
        int iterations = 10000;
        T t = new T(200);
        IRandomNumberGenerator rng = getRandomNumberGenerator();
        double[] values = new double[iterations];
        double sum = 0, avg;

        for (int i = 0; i < iterations; i++) {
            values[i] = t.random(rng);
            sum += values[i];
        }
        avg = sum / iterations;

        double variance = 0;
        for (int i = 0; i < iterations; i++) {
            variance += Math.pow((values[i] - avg), 2);
        }
        variance = variance / iterations;
        assertThat(t.getVariance()).isCloseTo(variance, Assertions.within(t.getVariance() * TOLERANCE));
    }

    private IRandomNumberGenerator getRandomNumberGenerator() {
        return new SystemRNG(0);
//        return new IRandomNumberGenerator() {
//            @Override
//            public double nextDouble() {
//                return new Random().nextDouble();
//            }
//
//            @Override
//            public int nextInt() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public long nextLong() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public boolean nextBoolean() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public float nextFloat() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public int nextInt(int n) throws IllegalArgumentException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public IRandomNumberGenerator synchronize() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        };
    }

}
