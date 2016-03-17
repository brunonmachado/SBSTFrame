/*
 * Copyright 2016 Eduardo Horst <Eddyosos at eduardoquijano2@gmail.com>.
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduardo Horst <Eddyosos at eduardoquijano2@gmail.com>
 */
public class DefaultReportTest {
    
    public DefaultReportTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBenchmarkPath method, of class DefaultReport.
     */
    @Test
    public void testGetBenchmarkPath() {
        System.out.println("getBenchmarkPath");
        DefaultReportLazy instance = null;
        String expResult = "";
        String result = instance.getBenchmarkPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestCaseTotal method, of class DefaultReport.
     */
    @Test
    public void testGetTestCaseTotal() {
        System.out.println("getTestCaseTotal");
        DefaultReportLazy instance = null;
        int expResult = 0;
        int result = instance.getTestCaseTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRequirementTotal method, of class DefaultReport.
     */
    @Test
    public void testGetRequirementTotal() {
        System.out.println("getRequirementTotal");
        DefaultReportLazy instance = null;
        int expResult = 0;
        int result = instance.getRequirementTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTest method, of class DefaultReport.
     */
    @Test
    public void testGetTest() {
        System.out.println("getTest");
        int testCase = 0;
        int testReq = 0;
        DefaultReportLazy instance = null;
        boolean expResult = false;
        boolean result = instance.getTest(testCase, testReq);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWorthlessReqTotal method, of class DefaultReport.
     */
    @Test
    public void testGetWorthlessReqTotal() {
        System.out.println("getWorthlessReqTotal");
        DefaultReportLazy instance = null;
        int expResult = 0;
        int result = instance.getWorthlessReqTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoreMax method, of class DefaultReport.
     */
    @Test
    public void testGetScoreMax() {
        System.out.println("getScoreMax");
        DefaultReportLazy instance = null;
        double expResult = 0.0;
        double result = instance.getScoreMax();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
