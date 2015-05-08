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

import org.uncommons.watchmaker.framework.termination.GenerationCount;
import sbstframe.problem.DefaultReport;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.Experiment;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.GeneticAlgorithm;

public class Example2 {
    public static void main(String[] args){
        //user's benchmark with framework's layout
        ProblemInterface userBenchmark = new DefaultReport("benchmarks/userBenchmark.csv", 1.0d, 7);
        
        double mutation = 0.05;
        for(int i = 0; i < 4; i++){

            Experiment exp = new Experiment(3, 180);

            //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
            AbstractEvolutionaryAlgorithm alg = new GeneticAlgorithm(userBenchmark, 2, 3, 0.95, mutation); //restante dos params padrao                
            alg.setTerminationCondition(new GenerationCount(201)); //optional
            alg.setElitism(1); //optional

            Metrics results = exp.run(alg);
            System.out.println(results.toString());
            mutation += 0.3;
        }

    }
 
}
