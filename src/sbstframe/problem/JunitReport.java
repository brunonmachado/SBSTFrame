/*
 * Copyright 2015 Eduardo Horst.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sbstframe.problem;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Implements an ProblemInterface in order to use SBSTFrame with {@see Junit}
 * @author Eduardo Horst
 */
public class JunitReport implements ProblemInterface{
    /**
     * Holds the recently executed tests as Map<testRequisiteIndex, Map<testCaseIndex,result>>
     */
    private final Map<Integer, Map<Integer, Boolean>> testCache; //Stores the specified aumount of test cases results
    
    /**
     * Holds all the test suites, were each one contains all test cases for one testRequisite
     */
    private final TestSuite[] testCasesOnMutants; //Stores the testSuites (one for each mutant
    
    /**
     * Holds the quantity of equal test cases
     */
    private final int WorthLessReq;
    
    /**
     * Holds the best score of a test
     */
    private final double scoreMax;
    
    /**
     * 
     * Constructor of the {@see JunitReport}
     * @param testCasesOnMutants all test cases against all mutants, each {@see TestSuite}
     * @param WorthLessReq the quantity of equal test cases
     * @param scoreMax the max score of any given test case
     * @throws NullPointerException if testCasesOnMutants is null
     * @throws InvalidParameterException if the quantity of tests in any TestSuite of
     * testCaseOnMutants differs
     */
    public JunitReport(TestSuite[] testCasesOnMutants, int WorthLessReq, double scoreMax) throws NullPointerException, InvalidParameterException {
        //Integrity test, all mutants must have the same quantity of Tests since it is spected for them to run the same tests
        int qntTestCase = testCasesOnMutants[0].testCount();
        for(int mutantIndex = 0; mutantIndex < testCasesOnMutants.length; mutantIndex++) {
            if(testCasesOnMutants[mutantIndex].testCount() != qntTestCase) {
                throw new InvalidParameterException("The quantity of test cases"
                        + "difer from mutant 0 and " + mutantIndex);
            }
        }
        
        this.testCasesOnMutants = testCasesOnMutants;
        this.testCache = new HashMap<>();
        this.WorthLessReq = WorthLessReq;
        this.scoreMax = scoreMax;
    }

    /**
     * Gets the test result of the test case in the test requisite.
     * This method records recently used test cases in order to give a faster
     * answer, and only executes the test case if it isn't recorded
     * @param testCase test case index
     * @param testReq test requisite index
     * @return true if the test was covered
     *         false if the test wasn't covered
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        Map<Integer,Boolean> knownResults;
        knownResults = testCache.get(testReq);
        if(knownResults != null) {
            Boolean result;
            result = knownResults.get(testCase);
            
            if(result != null) {
                return result;
            }
        }
        
        return runAndRecordTest(testCase, testReq);
    }
    
    /**
     * Runs an test case and record it to testCache
     * @param testCase test case index
     * @param testReq test requisite index
     * @return true if the test was covered
     *          false if the test wasn't covered
     * @see getTest
     */
    private boolean runAndRecordTest(int testCase, int testReq) {
        TestResult result = new TestResult();
        testCasesOnMutants[testReq].testAt(testCase).run(result);
        
        Map<Integer,Boolean> testRequisiteContainer;
        testRequisiteContainer = testCache.get(testReq);
        if(testRequisiteContainer == null) {    //Test cache doesn't contains the testReq
            testRequisiteContainer = new HashMap<>();
            testCache.put(testReq, testRequisiteContainer);
        }
        testRequisiteContainer.put(testCase, result.wasSuccessful());
        
        return result.wasSuccessful();
    }

    @Override
    public String getBenchmarkPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Counts the quantity of test cases
     * @return the count
     */
    @Override
    public int getTestCaseTotal() {
        return testCasesOnMutants[0].testCount();
    }

    /**
     * Counts the quantity of test requirements
     * @return the count
     */
    @Override
    public int getRequirementTotal() {
        return testCasesOnMutants.length;
    }

    /**
     * Returns the quantity of equal test cases
     * @return the quantity
     */
    @Override
    public int getWorthlessReqTotal() {
        return this.WorthLessReq;
    }

    @Override
    public double getScoreMax() {
        return this.scoreMax;
    }
    
}
