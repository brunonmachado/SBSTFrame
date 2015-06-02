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

/**
 * Implements an generic use of ProblemInterface for mutation test using an Oracule
 * @author Eduardo Horst
 * @see ProblemInterface
 */
public class OraculeReport implements ProblemInterface {
    /**
     * Holds the Oracule also known as the original program results
     */
    private ProblemInterface oracule;
    
    /**
     * Holds the mutants also known as the mutants results
     */
    private ProblemInterface mutants;

    /**
     * Default Constructor
     * @param oracule source of the expected results
     * @param mutants source of the results to be tested
     * @throws InvalidParameterException if the oracule has more than one test requirements
     *                                   or the oracule and mutants has a diferent quantity of test cases
     * @see ProblemInterface 
     */
    public OraculeReport(ProblemInterface oracule, ProblemInterface mutants) {
        if(oracule.getRequirementTotal() != 1) {
            throw new InvalidParameterException("The oracule must be the only test requirement");
        }
        if(oracule.getTestCaseTotal() != mutants.getTestCaseTotal()) {
            throw new InvalidParameterException("The oracule and it's mutants must have same quantity of testCases");
        }
        
        this.oracule = oracule;
        this.mutants = mutants;
    }
    
    /**
     * Get the test result, as if the mutant is alive or not
     * @param testCase index of the test case
     * @param testReq index of the test requisite
     * @return true if the mutant is dead
     *         false if the mutant is alive
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        return oracule.getTest(testCase, testReq) != mutants.getTest(testCase, testReq);
    }

    /**
     * BenchmarkPath of the mutants
     * @return the path
     */
    @Override
    public String getBenchmarkPath() {
        return mutants.getBenchmarkPath();
    }

    /**
     * Quantity of test cases
     * @return the quantity 
     */
    @Override
    public int getTestCaseTotal() {
        return mutants.getTestCaseTotal();
    }

    /**
     * Quantity of requirements in the mutants
     * @return the quantity
     */
    @Override
    public int getRequirementTotal() {
        return mutants.getRequirementTotal();
    }

    /**
     * Quantity of equal requirements in the mutants
     * @return  the quantity
     */
    @Override
    public int getWorthlessReqTotal() {
        return mutants.getRequirementTotal();
    }

    /**
     * Best score in the mutants
     * @return the best score
     */
    @Override
    public double getScoreMax() {
        return mutants.getScoreMax();
    }
    
}
