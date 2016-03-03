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

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storage of test results in order to not need re-tests.
 * Be aware, it only caches getTest() results.
 * @author Eduardo Horst
 */
public class CachedReport implements ProblemInterface {
    private ProblemInterface toBeCached;
    private Map<Key, Boolean> cache;
    
    /**
     * Default Constructor
     * @param myProblem The ProblemInterface to be cached
     */
    public CachedReport(ProblemInterface myProblem) {
        if(myProblem == null) {
            String msg = "ProblemInterface myProblem can't be null";
            throw new NullPointerException(msg);
        }
        
        cache = new HashMap();
        
        this.toBeCached = myProblem;
    }
    
    /**
     * Returns the result of the same call for myProblem.getTest() (given in the 
     * constructor), but it stores it's result so it won't need to make the same
     * call in the future.
     * Be aware that since it only calls myProblem.getTest() once, it will all-
     * ways return the same result (given same parameters).
     * @return 
     */
    @Override
    public boolean getTest(int testCase, int testReq) {
        final Key currentKey;
        currentKey = new Key(testCase, testReq);
        
        return cache.computeIfAbsent(currentKey, 
            (Key myKey) -> toBeCached.getTest(myKey.testCase, myKey.testReq)
        );
        
    }
    
    /**
     * It transfers the call to toBeCached object
     * @return toBeCached.getBenchamrkPath()
     */
    @Override
    public String getBenchmarkPath() {
        return toBeCached.getBenchmarkPath();
    }

    /**
     * It transfers the call to toBeCached object
     * @return toBeCached.getTestCaseTotal()
     */
    @Override
    public int getTestCaseTotal() {
        return toBeCached.getTestCaseTotal();
    }

    /**
     * It transfers the call to toBeCached object
     * @return toBeCached.getRequirementTotal()
     */
    @Override
    public int getRequirementTotal() {
        return toBeCached.getRequirementTotal();
    }

    /**
     * It transfers the call to toBeCached object
     * @return toBeCached.getRequirementTotal()
     */
    @Override
    public int getWorthlessReqTotal() {
        return toBeCached.getWorthlessReqTotal();
    }

    /**
     * It transfers the call to toBeCached object
     * @return toBeCached.getRequirementTotal()
     */
    @Override
    public double getScoreMax() {
        return toBeCached.getScoreMax();
    }
    
    /**
     * Storage for the key identifier used in hashmap
     */
    private class Key {
        /**
         * Index of the test case
         */
        private final int testCase;
        
        /**
         * Index of the test requirement
         */
        private final int testReq;
        
        /**
         * Creates a key for usage in the hash map, note parameters order matter,
         * expression ( new key(1,2) != new key(2,1) ) is true
         * @param testCase index of test case
         * @param testReq  index of test requirement
         */
        private Key(int testCase, int testReq) {
            this.testCase = testCase;
            this.testReq = testReq;
        }
        
        /**
         * Exposes testCase for reading
         * @return testCase
         */
        public int getTestCase() {
            return testCase;
        }
        
        /**
         * Exposes testReq for reading
         * @return 
         */
        public int getTestReq() {
            return testReq;
        }

        /**
         * Hash function for this class, depedent on testCase and testReq
         * @return hashCode
         */
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + this.testCase;
            hash = 97 * hash + this.testReq;
            return hash;
        }

        /**
         * Compares this instance with obj
         * @param obj any other object
         * @return ture if obj is an instance of Key and has the same testCase 
         * and testReq
         */
        @Override
        public boolean equals(Object obj) {
            if (obj != null &&
                getClass() == obj.getClass()) {
                
                final Key other = (Key) obj;
                
                return this.testCase == other.testCase && 
                        this.testReq == other.testReq;
                
            }

            return false;
        }
    }
}
