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

import java.util.ArrayList;
import java.util.List;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.IntArrayCrossover;
import org.uncommons.watchmaker.framework.selection.SigmaScaling;
import sbstframe.problem.ProblemInterface;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.FitnessFunction;
import sbstframe.solution.searchTechniques.operators.IntArrayFactory;
import sbstframe.solution.searchTechniques.operators.IntArrayMutationAll;

public class UserAlgorithm extends AbstractEvolutionaryAlgorithm{
    private double crossRate;
    private double mutRate;
    
    public UserAlgorithm(ProblemInterface benchmark, int popSize, int indSize,
            double crossRate, double mutRate){
        
        super(benchmark, popSize, indSize);
        this.crossRate = crossRate;
        this.mutRate = mutRate;
         
        IntArrayFactory factory = new IntArrayFactory(indSize, benchmark.getTestCaseTotal());
        List<EvolutionaryOperator<int[]>> operators = new ArrayList<EvolutionaryOperator<int[]>>(2);
        IntArrayCrossover crossoverOp = new IntArrayCrossover(1, new Probability(crossRate));
        IntArrayMutationAll mutationOp = new IntArrayMutationAll(benchmark.getTestCaseTotal(), new Probability(mutRate));
        operators.add(crossoverOp);
        operators.add(mutationOp);
        EvolutionPipeline<int[]> pipeline = new EvolutionPipeline<int[]>(operators);
        
        FitnessFunction fitness = getFitnessFunction();
        
        SigmaScaling selection = new SigmaScaling();
        
        EvolutionEngine<int[]> engine = new GenerationalEvolutionEngine<int[]>(factory, 
            pipeline, fitness, selection, new MersenneTwisterRNG());
        
        setEngine(engine);
        
    }
    
    @Override
    public String toString(){
        return "userAlg;"+crossRate+";"+mutRate;
    }
}
