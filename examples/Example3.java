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
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;

public class Example3 {
    public static void main(String[] args){
        //framework's benchmark
        
        ProblemInterface defaultReport = new DefaultReport(Benchmarks.look);
        
        Experiment exp = new Experiment(1, 10, true);
        
        //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
        AbstractEvolutionaryAlgorithm alg = new UserAlgorithm(defaultReport, 2, 3, 0.95, 0.05); //restante dos params padrao
        
        Metrics results = exp.run(alg);
        System.out.println(results.toString());
        
    }
}
