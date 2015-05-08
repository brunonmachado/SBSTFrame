/**
 * 
 * Copyright(c) 24/09/2014 SBSTFrame, Inc.  All Rights Reserved.
 * This framework is the proprietary information of SBSTFrame.
 *
 * @author Beatriz Proto
 * @author Bruno Machado
 * @author André Lobo
 * 
 */

package sbstframe;

import sbstframe.problem.Benchmarks;
import sbstframe.problem.DefaultReport;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.Experiment;
import sbstframe.solution.searchTechniques.*;

public class Example1 {
    
    public static void main(String[] args){
        //framework's benchmark and algorithm
        ProblemInterface defaultReport = new DefaultReport(Benchmarks.look);
        
        //total of executions, max time in seconds for each execution, 
        //optional: results folder and/or if show evolution
        Experiment exp = new Experiment(2, 10);
        
        //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
        AbstractEvolutionaryAlgorithm alg = new GeneticAlgorithm(defaultReport, 2, 3, 0.95, 0.05); //restante dos params padrao
        
        Metrics results = exp.run(alg);
        System.out.println(results.toString());

    }
 
}
