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
package sbstframe.solution.searchTechniques;

import sbstframe.solution.searchTechniques.operators.FitnessOrTime;
import sbstframe.solution.searchTechniques.operators.EvolutionLogger;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
   
public abstract class AbstractEvolutionaryAlgorithm {

    private FitnessFunction fitness;
    private int popSize;
    private int indSize;
    private int elitism;
    private boolean maximizing;
    private TerminationCondition terminationCondition;
  //  private MyTerminalCondition terminationCondition2;
    private EvolutionEngine<int[]> engine;
    private IslandEvolution<int[]> islandEngine;
    private ProblemInterface benchmark;
    private int epoch;
    private int migration;
    
    
    public AbstractEvolutionaryAlgorithm(ProblemInterface benchmark, int popSize, int indSize) {
        this.benchmark = benchmark;
        this.popSize = popSize;
        this.indSize = indSize;
        this.fitness = new FitnessFunction(benchmark);
        this.elitism = 1;
        this.maximizing = true;
        this.migration = -1;
        this.epoch = -1;
    }
                
    public int[] evolve() {
        
        if(migration == -1){
            
            int[] result = engine.evolve(popSize, elitism, terminationCondition); //cond. termino, fitness, maximiza ou min, miliseg        
            return result;
        }
        
        int[] result = islandEngine.evolve(popSize, elitism, epoch, migration, terminationCondition); //cond. termino, fitness, maximiza ou min, miliseg
        return result;

    }
   
    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }
    
    public int getPopSize() {
        return popSize;
    }

    public void setElitism(int elitism) {
        this.elitism = elitism;
    }

    public void setMaximizing(boolean maximizing) {
        this.maximizing = maximizing;
    }
    
    public void setFitnessFunction(FitnessFunction fitness) {
        this.fitness = fitness;
    }
    
    public FitnessFunction getFitnessFunction() {
        return this.fitness;
    }
    
    public int getEvaluatorCallsTotal() {
        return fitness.getEvaluatorCallsTotal();
    }
    
    public EvolutionEngine<int[]> getEngine() {
        return engine;
    }

    public void setEngine(EvolutionEngine<int[]> engine) {
        this.engine = engine;
    }
    
    public IslandEvolution<int[]> getIslandEngine() {
        return this.islandEngine;
    }

    public void setIslandEngine(IslandEvolution<int[]> engine, int migration, int epoch) {
        this.islandEngine = engine;
        this.migration = migration;
        this.epoch = epoch;
    
    }

    public void setTime(int time) {
        if(this.terminationCondition == null){
            this.terminationCondition = new FitnessOrTime(benchmark.getScoreMax(), maximizing, time);
        }
    }
    
    /* Tentativa de controlar a chamada de funçao fitness
    public void setCallFitness(int maxCallFitness) {
            this.terminationCondition = new MyTerminalCondition(maxCallFitness, 0);
    }*/
     
     
    public ProblemInterface getBenchmark() {
        return benchmark;
    }
    
    public int getIndSize() {
        return indSize;
    }
    
    public void addMetrics(Metrics metrics){
        this.engine.addEvolutionObserver(new EvolutionLogger(metrics));
    }
    
    public TerminationCondition getTerminationCondition() {
        return terminationCondition;
    }

    public void setTerminationCondition(TerminationCondition terminationCondition) {
        this.terminationCondition = terminationCondition;
    }
    
}
