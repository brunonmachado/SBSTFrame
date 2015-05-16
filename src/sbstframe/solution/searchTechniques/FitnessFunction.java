/**
 *
 * Copyright (c) 2014-2015,
 *	Beatriz Proto          <beatrizproto@gmail.com>
 *	Bruno Machado          <brunonmachado@outlook.com>	
 *	André Lôbo             <andre.assis.lobo@gmail.com> 
 *	Celso Camilo           <celso@inf.ufg.br>
 *      Auri Vincenzi          <auri@inf.ufg.br>                                             
 *	Cassio Rodrigues       <cassio@inf.ufg.br>
 *	Plinio Júnior          <plinio@inf.ufg.br>
 *      Eduardo Horst          <eduardoquijano2@gmail.com>
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

package sbstframe.solution.searchTechniques;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import sbstframe.problem.ProblemInterface;

public class FitnessFunction implements FitnessEvaluator<int[]>{
    int reqTotal;
    ProblemInterface benchmark;
    static int count;
   
    public FitnessFunction(ProblemInterface benchmark){
        this.reqTotal = benchmark.getRequirementTotal();
        this.benchmark = benchmark;
        this.count = 0;
    }
    

    public double getFitness(int[] candidate, List<? extends int[]> population)
    {
        count++;
        Set<Integer> covered = new HashSet<>();
        int j;
        for (int i = 0; i<reqTotal; i++){
            for (j = 0; j<candidate.length; j++){
                if(benchmark.getTest(candidate[j],i)){
                    /*mutant i was killed by individual's test case j*/
                    covered.add(i);
                    break;
                }
            }
        }
        
        return this.objectiveFunction(covered.size());
        
    }

    public double getFitness(int[] candidate, List<? extends int[]> population, int callfitness)
    {
        count++;
        Set<Integer> covered = new HashSet<>();
        int j;
        for (int i = 0; i<reqTotal; i++){
            for (j = 0; j<candidate.length; j++){
                if(benchmark.getTest(candidate[j], i)){
                    /*mutant i was killed by individual's test case j*/
                    covered.add(i);
                    break;
                }
            }
        }
        
        return this.objectiveFunction(covered.size());
        
    }    

    /**
     * Execute the test case against the mutant
     * @param mut identifier of the mutant
     * @param tc identifier of the test case
     * @return 
     * @deprecated use a ProblemInterface.getTest() 
     */
    @Deprecated
    public boolean executeTest(int mut, int tc) {
        return benchmark.getTest(tc,mut);
    }
    
    public double objectiveFunction(int covered){
        return (double) covered / (double)(reqTotal - this.benchmark.getWorthlessReqTotal());
    }

    public boolean isNatural() //if true maximize fitness
    {
        return true;
    }

    int getEvaluatorCallsTotal() {
        return this.count;
    }

}