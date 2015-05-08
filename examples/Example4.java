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
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.Experiment;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.GeneticAlgorithm;

public class Example4 {
    public static void main(String[] args){
        //user's benchmark with his layout
        ProblemInterface userReport = new UserReport("userbenchmark.csv", 1.0, 0);
        
        //UserReport(path, max score, total of equivalents
//        System.out.println(userReport.getRequirementTotal()+ " "+ userReport.getTestCaseTotal());

        double mutation = 0.05;
        for(int i = 0; i < 4; i++){

            Experiment exp = new Experiment(1, 180);

            //algorithm(benchmark, popsize, indsize, crossover rate, mutation rate)
            AbstractEvolutionaryAlgorithm alg = new GeneticAlgorithm(userReport, 2, 3, 0.95, mutation); //restante dos params padrao                
            alg.setTerminationCondition(new GenerationCount(201)); //optional
            alg.setElitism(1); //optional

            Metrics results = exp.run(alg);
            System.out.println(results.toString());
            mutation += 0.3;
        }

    }
    
}
