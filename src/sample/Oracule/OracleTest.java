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
package sample.Oracule;

import junit.framework.TestSuite;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import sbstframe.problem.JunitReport;
import sbstframe.problem.OracleReport;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.Experiment;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.RandomAlgorithm;

/**
 * OracleReport testing
 * @author Eduardo Hosrt
 */
public class OracleTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //framework's benchmark and algorithm
        ProblemInterface defaultReport;

        //total of executions, max time in seconds for each execution, 
        //optional: results folder and/or if show evolution
        Experiment exp;
        AbstractEvolutionaryAlgorithm alg;
        Metrics results;
        TestSuite[] testCasesOnMutants;
        System.out.println("Experimento 1 - Benchmark Money Algoritmo Aleatório Taxa de Mutação 1 %");
        testCasesOnMutants = new TestSuite[5];
        testCasesOnMutants[0] = new TestSuite(sample.junit.mutantes.original.MoneyTest.class);
        testCasesOnMutants[1] = new TestSuite(sample.junit.mutantes.m1.MoneyTest.class);
        testCasesOnMutants[2] = new TestSuite(sample.junit.mutantes.m2.MoneyTest.class);
        testCasesOnMutants[3] = new TestSuite(sample.junit.mutantes.m3.MoneyTest.class);
        testCasesOnMutants[4] = new TestSuite(sample.junit.mutantes.m4.MoneyTest.class);

        TestSuite[] Oracule = new TestSuite[1];
        Oracule[0] = new TestSuite(sample.junit.mutantes.original.MoneyTest.class);
        
        defaultReport = new OracleReport(new JunitReport(Oracule,0,0), new JunitReport(testCasesOnMutants, 0, 0));
        exp = new Experiment(1, 60);
        
        //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
        alg = new RandomAlgorithm(defaultReport, 2, 1); //restante dos params padrao

        alg.setTerminationCondition(new GenerationCount(50));
        
        results = exp.run(alg);
        System.out.println(results.toString());
        
        for(int testReq = 0; testReq < defaultReport.getRequirementTotal(); testReq++) {
            for(int testCase = 0; testCase < defaultReport.getTestCaseTotal(); testCase++) {
                System.out.println("REQ " + testReq + " "
                                 + "CASE " + testCase + " " 
                                 + "RESULT " + defaultReport.getTest(testCase, testReq));
            }
        }
    }
}
