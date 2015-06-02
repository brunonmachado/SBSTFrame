/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import sbstframe.problem.JunitReport;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.Experiment;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.RandomAlgorithm;

/**
 *
 * @author eddyosos
 */
public class JunitSbstFrameTest {

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
        TestResult result;
        result = new TestResult();
        testCasesOnMutants[0].testAt(1).run(result);
        System.out.println(result.toString());
        defaultReport = new JunitReport(testCasesOnMutants, 0, 0);
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
