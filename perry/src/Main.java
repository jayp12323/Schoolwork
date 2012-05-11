
/*
 * Implementation of the quadratic sieve. Attempts to factor a large composite integer N = p * q.
 * Primary implementation references include:
 *      "Introduction to Cryptography with Coding Thoery, 2nd Edition" (Trappe and Washington, 2006),
 *      "The Quadratic Sieve Factoring Algorithm" (Landquist, 2001),
 *      Wikipedia article on Gaussian elimination
 *
 * Brandon Morton
 * December 2010
 */


import java.math.BigInteger;
import java.util.ArrayList;


public class Main
{
    static ArrayList<Integer> smallPrimes;

    /**
     * Application entry point.
     * @param args The command line arguments. Expects 1 composite integer.
     */
    public static void main(String[] args)
    {
//        if (args.length != 1)
//        {
//            System.out.println("Attempts to factor a composite integer N = pq, where p and q are primes, using the quadratic sieve.");
//            return;
//        }

        smallPrimes = SieveOfEratosthenes((int) Math.pow(10, 6.5)); // almost 80k primes
System.out.println(smallPrimes.size());
        BigInteger N = new BigInteger("1000020");
        System.out.println("N = " + N);

        System.out.println("Factoring...");
//        ArrayList<BigInteger> factors = QuadraticSieve(N);
//        if (factors.size() < 2)
//        {
//            System.out.println("No factors discovered");
//        }
//        else
//        {
//            System.out.println("Two factors of N are:");
//            for (int i = 0; i < factors.size(); ++i)
//                System.out.println(factors.get(i));
//        }
    }

    /**
     * For small offsets, returns values that if squared, will be slightly larger than N.
     * @param offset Should be near zero, but probably >= 1.
     */
    private static BigInteger GetBigRoot(int offset, BigInteger N)
    {
        BigInteger root = FloorRoot(N).add(BigInteger.valueOf(offset));
        return root;
    }

    /**
     * Attempts to factor N.
     * @param N The number to be factored, where it is assumed there are two factors: N = p * q, where p and q are prime.
     * @return The two factors of N.
     */
    private static ArrayList<BigInteger> QuadraticSieve(BigInteger N)
    {
        ArrayList<Integer> factorBase = GetFactorBase(N);
        ArrayList<BigInteger> interestingBigRoots = new ArrayList<BigInteger>();
        ArrayList<ArrayList<Integer>> interestingPrimePowers = new ArrayList<ArrayList<Integer>>();

        // TODO: Fill arrays using more efficient iteration (based on Shanks-Tonelli Algorithm).
        // Note: This implementation reduces memory use compared to the Shanks-Tonelli, since it proceeds row-ordered instead of column-ordered (allowing unsmooth values to be ignored immediately).
        //       However, on a machine with ample memory (much much more than I have), it may be better to use Shanks-Tonelli.
        //       Combining the best of the two may yet be possible.
        int maxOffset = EfficientSievingInterval(factorBase.size());
        int extraRows = 5; // with n more rows than columns in the matrix, there is a 1 - 1/2^n chance of finding a factor (1 - 1/2^5 = 96.9%)
        int goalRows = factorBase.size() + extraRows;
        double percentComplete = 0;
        double nextPercentCompleteNotice = 0;
        double deltaPercentCompleteNotice = 0.10;
        for (int offset = 1; offset <= maxOffset && interestingBigRoots.size() < goalRows; ++offset)
        {
            double percentIterations = ((double)offset) / maxOffset;
            double percentSmoothNumbers = ((double)interestingBigRoots.size()) / goalRows;
            percentComplete = Math.max(percentIterations, percentSmoothNumbers);
            if (percentComplete >= nextPercentCompleteNotice)
            {
                System.out.printf("%.1f", percentComplete * 100);
                System.out.println("% complete");
                while (nextPercentCompleteNotice <= percentComplete)
                    nextPercentCompleteNotice += deltaPercentCompleteNotice;
            }

            BigInteger bigRoot = GetBigRoot(offset, N);
            BigInteger squareModN = bigRoot.modPow(BigInteger.valueOf(2), N);
            BigInteger quotient = squareModN;

            // decompose the square
            ArrayList<Integer> primePowers = new ArrayList<Integer>();
            for (int p = 0; p < factorBase.size(); ++p)
            {
                int power = 0;
                BigInteger possibleFactor = BigInteger.valueOf(factorBase.get(p));
                while (IsFactor(quotient, possibleFactor))
                {
                    ++power;
                    quotient = quotient.divide(possibleFactor);
                }
                primePowers.add(power);
            }

            // keep values if the square factored completely over the factor base (if it was smooth)
            if (quotient.equals(BigInteger.ONE))
            {
                interestingBigRoots.add(bigRoot);
                interestingPrimePowers.add(primePowers);
            }
        }

        // Print the likelihood of finding a factor at this point
        int actualExtraRows = interestingBigRoots.size() - factorBase.size();
        double chanceFindingFactor = 1.0 - Math.pow(0.5, actualExtraRows);
        chanceFindingFactor = Math.max(0, chanceFindingFactor);
        System.out.println("A factor should be found with better than " + chanceFindingFactor * 100 + "% likelihood.");

        // Attempt to find a factor of N
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
        if (interestingBigRoots.isEmpty())
        {
            factors.add(BigInteger.ONE);
            factors.add(N);
        }
        else
        {
            BigInteger factor = FindFactorQuick(N, factorBase, interestingBigRoots, interestingPrimePowers);
            BigInteger otherFactor = N.divide(factor);
            factors.add(factor);
            factors.add(otherFactor);
        }

        return factors;
    }

    /**
     * Finds a factor of N.
     * @param N The number being factored.
     * @param factorBase The set of small primes making up the factor base for N.
     * @param bigRoots The big roots in x^2 = y^2 (mod N).
     * @param primePowers The prime decomposition of the squares of the bigRoots (mod N).
     * @return p, where p * q = N.
     */
    private static BigInteger FindFactor(BigInteger N, ArrayList<Integer> factorBase, ArrayList<BigInteger> bigRoots, ArrayList<ArrayList<Integer>> primePowers)
    {
        int numRows = primePowers.size();
        Boolean[] usedRows = new Boolean[numRows];
        for (int row = 0; row < numRows; ++row)
            usedRows[row] = false;

        return FindDependenceThatFactors(N, factorBase, bigRoots, primePowers, usedRows, 0);
    }

    /**
     * Finds a factor of N.
     * This method uses Gaussian elimination to find dependences in the primePowers matrix, so it is much faster than the brute-force method applied in FindFactor().
     * @param N The number being factored.
     * @param factorBase The set of small primes making up the factor base for N.
     * @param bigRoots The big roots in x^2 = y^2 (mod N).
     * @param primePowers The prime decomposition of the squares of the bigRoots (mod N). Multiplying factorBase[i]^primePowers[r][i] for all i gives bigRoots[r].
     * @return p, where p * q = N.
     */
    private static BigInteger FindFactorQuick(BigInteger N, ArrayList<Integer> factorBase, ArrayList<BigInteger> bigRoots, ArrayList<ArrayList<Integer>> primePowers)
    {
        // find linear dependences
        ArrayList<ArrayList<Integer>> primePowersCopy = new ArrayList<ArrayList<Integer>>();
        for (int row = 0; row < primePowers.size(); ++row)
        {
            ArrayList<Integer> rowCopy = new ArrayList<Integer>();
            for (int col = 0; col < primePowers.get(row).size(); ++col)
                rowCopy.add(primePowers.get(row).get(col));

            primePowersCopy.add(rowCopy);
        }
        ArrayList<ArrayList<Integer>> dependences = FindDependencesMod2(primePowersCopy);

        // check dependences for factoring
        for (int dep = 0; dep < dependences.size(); ++dep)
        {
            // accumulate the powers for the small root
            int[] powers = new int[factorBase.size()];
            for (int col = 0; col < powers.length; ++col)
                powers[col] = 0;
            for (int i = 0; i < dependences.get(dep).size(); ++i)
            {
                int depRow = dependences.get(dep).get(i);
                for (int col = 0; col < powers.length; ++col)
                    powers[col] += primePowers.get(depRow).get(col);
            }
            for (int col = 0; col < powers.length; ++col)
                powers[col] /= 2;

            // find the small root
            BigInteger smallRoot = Expand(factorBase, powers);
            smallRoot = smallRoot.mod(N);

            // find the big root
            BigInteger bigRoot = BigInteger.ONE;
            for (int i = 0; i < dependences.get(dep).size(); ++i)
            {
                int depRow = dependences.get(dep).get(i);
                bigRoot = bigRoot.multiply(bigRoots.get(depRow));
            }
            bigRoot = bigRoot.mod(N);
            BigInteger negativeBigRoot = bigRoot.negate().mod(N);

            // attempt to return non-trivial factor
            if (!smallRoot.equals(bigRoot) && !smallRoot.equals(negativeBigRoot))
            {
                BigInteger nonTrivialFactor = N.gcd(smallRoot.subtract(bigRoot)); // TODO: does there need to be a (mod n) after the subtraction?
                return nonTrivialFactor;
            }
        }

        return BigInteger.ONE;
    }

    /**
     * Multiplies bases[i]^exponents[i] for all i. Arrays are expected to have the same size.
     */
    private static BigInteger Expand(ArrayList<Integer> bases, int[] exponents)
    {
        BigInteger val = BigInteger.ONE;

        for (int i = 0; i < bases.size(); ++i)
        {
            BigInteger term = BigInteger.valueOf(bases.get(i));
            term = term.pow(exponents[i]);
            val = val.multiply(term);
        }

        return val;
    }
    
    /**
     * Finds linear dependences (mod 2) between rows in the matrix.
     * @param matrix Expected to be non-ragged and non-empty.
     * @return Each row contains a list of rows in the original matrix that are linearly dependent (mod 2).
     */
    private static ArrayList<ArrayList<Integer>> FindDependencesMod2(ArrayList<ArrayList<Integer>> matrix)
    {
        int originalWidth = matrix.get(0).size();
        
        // add an identity matrix to the end of the the given matrix
        for (int row = 0; row < matrix.size(); ++row)
        {
            for (int col = 0; col < matrix.size(); ++col)
            {
                if (row == col)
                    matrix.get(row).add(1);
                else
                    matrix.get(row).add(0);
            }
        }
        
        GaussianEliminateMod2(matrix);
        
        // contruct list of linear dependences
        ArrayList<ArrayList<Integer>> dependences = new ArrayList<ArrayList<Integer>>();
        
        for (int row = 0; row < matrix.size(); ++row)
        {
            boolean allZero = true;
            for (int col = 0; col < originalWidth && allZero; ++col)
            {
                if (matrix.get(row).get(col) != 0)
                    allZero = false;
            }
            
            if (allZero)
            {
                ArrayList<Integer> newDependence = new ArrayList<Integer>();
                for (int col = originalWidth; col < matrix.get(row).size(); ++col)
                {
                    if (matrix.get(row).get(col) != 0)
                        newDependence.add(col - originalWidth);
                }
                
                dependences.add(newDependence);
            }
        }
        
        return dependences;
    }

    /**
     * Performs Gaussian elimination on the matrix (mod 2).
     * @param matrix Matrix is expected to be non-ragged.
     */
    private static void GaussianEliminateMod2(ArrayList<ArrayList<Integer>> matrix)
    {
        for (int row = 0, col = 0; row < matrix.size() && col < matrix.get(row).size(); ++col)
        {
            // find a positive pivot in this column, starting at the current row
            int pivot = row;
            for (int i = row + 1; i < matrix.size() && matrix.get(pivot).get(col) % 2 == 0; ++i)
            {
                if (matrix.get(i).get(col) % 2 == 1)
                    pivot = i;
            }

            // move pivot to top and subtract it from all lower rows with a one in the current column
            if (matrix.get(pivot).get(col) % 2 == 1)
            {
                SwapRows(matrix, row, pivot);

                for (int lowerRow = row + 1; lowerRow < matrix.size(); ++lowerRow)
                {
                    if (matrix.get(lowerRow).get(col) % 2 == 1)
                        XORRowsMod2(matrix, row, lowerRow);
                }

                ++row;
            }
        }
    }

    /**
     * Assigns the xor of the two rows (mod 2) to the second row.
     * @param matrix Expected to be non-ragged.
     * @param row1 Row is used in xor operation, but is not changed.
     * @param row2 The row that gets the value of the xor.
     */
    private static void XORRowsMod2(ArrayList<ArrayList<Integer>> matrix, int row1, int row2)
    {
        for (int col = 0; col < matrix.get(row1).size(); ++col)
        {
            boolean val1 = (matrix.get(row1).get(col) % 2 == 1);
            boolean val2 = (matrix.get(row2).get(col) % 2 == 1);
            boolean xor = val1 ^ val2;
            if (xor)
                matrix.get(row2).set(col, 1);
            else
                matrix.get(row2).set(col, 0);
        }
    }

    /**
     * Swaps rows - yeah, lame java can't do this for me, apparently...
     * @param matrix Assumed to not be ragged (swapped rows must have same length).
     */
    private static void SwapRows(ArrayList<ArrayList<Integer>> matrix, int row1, int row2)
    {
        ArrayList<Integer> tmp = matrix.get(row1);
        matrix.set(row1, matrix.get(row2));
        matrix.set(row2, tmp);
    }

    /**
     * Finds a linear dependence in the primePowers matrix (mod 2) that leads to a factorization of N.
     * This is a brute-force method, which has rough worst-case runtime complexity of 2^n, where n is the number of bigRoots.
     * An improvement would be to use a method for solving sparse linear equations (mod 2).
     * @param N The number to be factored.
     * @param factorBase The set of primes which are smooth for N.
     * @param bigRoots The x in x^2 = y^2 (mod n).
     * @param primePowers The prime decomposition of y, where primePowers[i] is the power of factorBase[i].
     * @param usedRows The rows of primePowers that are combined in an attempt to find a linear dependence.
     * @param currentRow The row being considered for inclusion or not in this call.
     * @return A non-trivial factor of N, if one can be found. BigInteger.ONE otherwise.
     */
    private static BigInteger FindDependenceThatFactors(BigInteger N, ArrayList<Integer> factorBase, ArrayList<BigInteger> bigRoots, ArrayList<ArrayList<Integer>> primePowers, Boolean[] usedRows, int currentRow)
    {
        if (currentRow >= usedRows.length)
        {
            int[] powers = new int[primePowers.get(0).size()];
            for (int i = 0; i < powers.length; ++i)
                powers[i] = 0;

            for (int row = 0; row < usedRows.length; ++row)
            {
                if (usedRows[row])
                {
                    for (int col = 0; col < powers.length; ++col)
                        powers[col] += primePowers.get(row).get(col);
                }
            }

            boolean dependent = true;
            for (int i = 0; i < powers.length; ++i)
            {
                if (powers[i] % 2 > 0)
                {
                    dependent = false;
                    break;
                }
            }

            if (dependent)
            {
                BigInteger smallRoot = BigInteger.ONE;
                for (int i = 0; i < factorBase.size(); ++i)
                {
                    BigInteger term = BigInteger.valueOf(factorBase.get(i));
                    term = term.pow(powers[i] / 2);
                    smallRoot = smallRoot.multiply(term);
                }
                smallRoot = smallRoot.mod(N);

                BigInteger bigRoot = BigInteger.ONE;
                for (int row = 0; row < usedRows.length; ++row)
                {
                    if (usedRows[row])
                        bigRoot = bigRoot.multiply(bigRoots.get(row));
                }
                bigRoot = bigRoot.mod(N);
                BigInteger negativeBigRoot = bigRoot.negate().mod(N);

                if (!smallRoot.equals(bigRoot) && !smallRoot.equals(negativeBigRoot))
                {
                    BigInteger nonTrivialFactor = N.gcd(smallRoot.subtract(bigRoot));
                    return nonTrivialFactor;
                }
            }

            return BigInteger.ONE;
        }

        usedRows[currentRow] = false;
        BigInteger factor = FindDependenceThatFactors(N, factorBase, bigRoots, primePowers, usedRows, currentRow + 1);
        if (!factor.equals(BigInteger.ONE))
            return factor;

        usedRows[currentRow] = true;
        return FindDependenceThatFactors(N, factorBase, bigRoots, primePowers, usedRows, currentRow + 1);
    }

    /**
     * Returns a collection of primes which can act as a factor base for N.
     * @param N The integer being factored.
     */
    private static ArrayList<Integer> GetFactorBase(BigInteger N)
    {
        int numPrimes = EfficientFactorBaseSize(N);

        ArrayList<Integer> factorBase = new ArrayList<Integer>(numPrimes);
        //factorBase.add(-1); // TODO: regardless of what the paper says, it doesn't make sense to have -1 in here (not prime, and even powers of it are just 1).
        for (int i = 0; i < smallPrimes.size() && factorBase.size() < numPrimes; ++i)
        {
            BigInteger prime = BigInteger.valueOf(smallPrimes.get(i));
            // TODO: what do we do with prime == 2? (here, Legendre computes to 1, which means we include it, which may be an issue)
            if (LegendreSymbol(N, prime).equals(BigInteger.ONE))
                factorBase.add(smallPrimes.get(i));
        }

        if (factorBase.size() < numPrimes)
            System.out.println("Warning: Not enough small primes were generated to make an efficiently sized factor base for this N.");

        return factorBase;
    }

    /**
     * Determines the number of primes that should exist in the factor base for an efficient factorization using the quadratic sieve.
     * @param N The integer being factored.
     */
    private static int EfficientFactorBaseSize(BigInteger N)
    {
        double estimateN = N.doubleValue();

        double ln = Math.log(estimateN);
        double rootValue = Math.sqrt(ln * Math.log(ln));
        double base = Math.pow(Math.E, rootValue);
        double result = Math.pow(base, Math.sqrt(2) / 4);

        return (int)result;
    }

    /**
     * Determines the size of the sieving interval.
     * @param factorBaseSize The number of primes used in the factor base.
     */
    private static int EfficientSievingInterval(int factorBaseSize)
    {
        int result = (int)Math.pow(factorBaseSize, 3);
        return result;
    }

    /**
     * Determines if possibleFactor is a factor of N. Expects values greater than 0.
     */
    private static boolean IsFactor(BigInteger N, BigInteger possibleFactor)
    {
        return (N.mod(possibleFactor) == BigInteger.ZERO);
    }

    /**
     * Returns the floor of the square root of x. Implementation is based on Heron's method.
     */
    private static BigInteger FloorRoot(BigInteger x)
    {
        BigInteger one = BigInteger.valueOf(1);
        BigInteger two = BigInteger.valueOf(2);
        long intermediateGuess = (long)Math.sqrt(x.floatValue());
        BigInteger guess = BigInteger.valueOf(intermediateGuess);

        boolean guessBelowGoal;
        boolean guessPlusOneAboveGoal;
        do
        {
            guess = guess.add(x.divide(guess)).divide(two);

            guessBelowGoal = guess.pow(2).compareTo(x) <= 0;
            guessPlusOneAboveGoal = guess.add(one).pow(2).compareTo(x) > 0;
        } while (!(guessBelowGoal && guessPlusOneAboveGoal));

        return guess;
    }

    /**
     * Computes the Legendre symbol (a/p).
     * @param a Expected to be non-zero (mod p).
     * @param p Expected to be a prime != 2.
     */
    private static BigInteger LegendreSymbol(BigInteger a, BigInteger p)
    {
        BigInteger power = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
        BigInteger legendre = a.modPow(power, p);
        return legendre;
    }

    /**
     * Returns a collection of positive primes less than or equal to limit.
     * This method is only quick for limit smaller than about 9 digits.
     */
    private static ArrayList<Integer> SieveOfEratosthenes(int limit)
    {
        ArrayList<Integer> primes = new ArrayList<Integer>();

        limit = Math.max(limit, 1);
        ArrayList<Boolean> sieveArray = new ArrayList<Boolean>(limit + 1);
        sieveArray.add(false);
        sieveArray.add(false);
        for (int i = 2; i <= limit; ++i)
            sieveArray.add(true);

        int rootLimit = (int)Math.sqrt(limit);
        for (int i = 2; i <= rootLimit; ++i)
        {
            if (sieveArray.get(i))
            {
                for (int k = i*i; k < sieveArray.size(); k += i)
                    sieveArray.set(k, false);
            }
        }

        for (int i = 2; i < sieveArray.size(); ++i)
        {
            if (sieveArray.get(i))
                primes.add(i);
        }

        return primes;
    }
}



	 

