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
package sbstframe.solution;

import java.io.IOException;
import org.jfree.data.xy.XYSeries;
import sbstframe.problem.ProblemInterface;
import sbstframe.results.Metrics;
import sbstframe.solution.searchTechniques.AbstractEvolutionaryAlgorithm;
import sbstframe.solution.searchTechniques.FitnessFunction;

/**
    se exec = 1 e true: imprime e salva evolucao da execucao e resultado final e mostra grafico da evolucao
    se exec = 1 e false: imprime e salva resultado final da execucao
    se exec > 1 e true: imprime e salva resultados finais das execucoes e mostra grafico do experimento
    se exec > 1 e false: imprime e salva resultado geral do experimento  

 */
public class Experiment {
    private int execTotal;
    private int timeMax;
    private Metrics metrics;
    private ProblemInterface benchmark;
    private XYSeries dataChart;

    
    public Experiment(int execTotal, int timeMax) {
        this(execTotal, timeMax, "results/", true);
    }
    public Experiment(int execTotal, int timeMax, String outPath) {
        this(execTotal, timeMax, outPath, true);
    }
    public Experiment(int execTotal, int timeMax, boolean showData) {
        this(execTotal, timeMax, "results/", showData);
    }
    public Experiment(int execTotal, int timeMax, String outputDir, boolean showData) {
        this.execTotal = execTotal;
        this.timeMax = timeMax*1000;
        if(showData) this.dataChart = new XYSeries("Best Fitness Experiment"); 
        else this.dataChart = null;
        this.metrics = new Metrics(outputDir);
        metrics.setGraphData(dataChart);
        
    }

    public Metrics run(AbstractEvolutionaryAlgorithm alg){
        metrics.setAlgInfo(alg.toString());

        try{
            int[] ind = null, indMax = new int[alg.getIndSize()];
            double fitness, maxFit = -1, fitnessSum = 0, stdDev = 0;
            double[] fitnessAll = new double[execTotal];
            int fitnessCalls = 0;
            float timeSum = 0;
             
            alg.setTime(timeMax);
          
            // alg.setCallFitness(1000000); Tentativa de criar metodo para setar o num de chamadas da funçao fitness

            for(int i = 0; i < execTotal; i++){
                long start;
                float time = 0;
                
                if(execTotal == 1 && dataChart != null)
                    alg.addMetrics(metrics);

                start = System.currentTimeMillis();
                ind = alg.evolve();

                time = (float)((System.currentTimeMillis() - start)/1000.0);
                
                FitnessFunction ff = new FitnessFunction(alg.getBenchmark());
                fitness = ff.getFitness(ind, null);
                //fitness = ff.getFitness(ind, null, 1000000);

                if(fitness > maxFit) {
                    maxFit = fitness;
                    System.arraycopy(ind, 0, indMax, 0, alg.getIndSize());

                }
                fitnessAll[i] = fitness;
                fitnessCalls += alg.getEvaluatorCallsTotal();
                fitnessSum += fitness;
                timeSum += time;
                
                if(execTotal == 1 || dataChart != null){
                    metrics.saveExecutionOutput(i, fitness);
                }
                if(execTotal > 1 && dataChart != null){
                    metrics.getGraphData().add(i, fitness);
                    
                }
                if(!(execTotal > 1 && dataChart == null))
                    System.out.println("Execution "+i+" - Fitness: "+fitness+" Time: "+time);
            }

            metrics.setAverageScore(fitnessSum/execTotal);
            metrics.setAverageTime(timeSum/execTotal);
            metrics.setMaxScore(maxFit);
            metrics.setBestSubset(indMax);

            for (int i = 0; i< execTotal;i++){
                stdDev += Math.pow(fitnessAll[i] - metrics.getAverageScore(), 2);
            }
            metrics.setStdDeviation(stdDev);
            metrics.setEvaluatorCallsTotal(fitnessCalls);

            if(execTotal > 1){
                metrics.saveExperimentOutput();
            }

            if(dataChart != null){
                metrics.drawGraphic();
            }
            metrics.getOutFile().write('\n');
            metrics.getOutFile().close();
        }catch (IOException e) {
            System.err.println(e);
        }
        return metrics;
    }
}
