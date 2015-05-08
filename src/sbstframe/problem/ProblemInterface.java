/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *  Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package sbstframe.problem;

import java.util.HashMap;

/**
 * Interface to implement the benchmark file reading, 
 * it calculates the total amount of test cases, requirements,
 * also sets the hashMap tests based on requirements covered or not by the test cases.
 * 
 * @see DefaultReport, it reads according to the proposed layout
 * 
 */

public interface ProblemInterface {
    
    /**
     * @return the hashMap containing results of 
     * all test cases applied to all requirements
     * 
     * The benchmark file can be read after passing its path on the constructor,
     * meanwhile it's possible to calculate the total amount of test cases and requirements.
     * Also the hashMap tests elements has to be set as 1 (covered) and 0 (not covered) 
     * based on test cases applied to requirements.
     * e.g: < 2, < 6, 1 >> requirement number 2 is covered by test case number 6
     * e.g: < 2, < 6, 0 >> requirement number 2 isn't covered by test case number 6 
     * 
     * 
     */
    public HashMap<Integer, HashMap<Integer, Integer>> getTests();
    
    /**
     * 
     * @return the benchmark file path
     */
    public String getBenchmarkPath();
    
    
    /**
     * 
     * @return the total amount of test cases
     */
    public int getTestCaseTotal();
    
    /**
     * 
     * @return the total amount of requirements
     */
    public int getRequirementTotal();
   
    /**
     * 
     * @return total amount of requirements equivalents
     */
    public int getWorthlessReqTotal(); 
    
    /**
     * 
     * @return maximum score possible influenced by requiriments that can not be covered
     */
    public double getScoreMax();
}

    

