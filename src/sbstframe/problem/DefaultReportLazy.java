/**
 *
 * Copyright (c) 2014-2015, 
 * Beatriz Proto          <beatrizproto@gmail.com>
 * Bruno Machado          <brunonmachado@outlook.com>
 * André Lôbo             <andre.assis.lobo@gmail.com>
 * Celso Camilo           <celso@inf.ufg.br>
 * Auri Vincenzi          <auri@inf.ufg.br>
 * Cassio Rodrigues       <cassio@inf.ufg.br>
 * Plinio Júnior          <plinio@inf.ufg.br>
 * Eduardo Horst          <eduardoquijano2@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package sbstframe.problem;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the benchmark file using the proposed layout
 * @see ProblemInterface
 * @author Andre/Beatriz/Bruno/Eduardo
 */
public class DefaultReportLazy implements ProblemInterface {

    private final static char SEPARATOR = ';';
    
    private String benchmarkPath;
    private int tcTotal; //Number of test cases
    private int reqTotal; //Number of requirements
    private int worthlessReqTotal; //Number of worthless requirements
    private double scoreMax;
    
    /**
     * Default constructor for {@code ProblemInterface}
     * @param path path to benchmark, ex: benchmarks/bubcorrecto.csv"
     * @param scoreMax Max score to be considerated
     * @param worthlessReqTotal  quantity of useless test requirements (whose 
     * will allways return the same test result as some other test requirement)
     */
    public DefaultReportLazy(String path, double scoreMax, int worthlessReqTotal) throws IOException {
        this.benchmarkPath = path;
        this.tcTotal = -1;
        this.reqTotal = -1;
        this.worthlessReqTotal = worthlessReqTotal;
        this.scoreMax = scoreMax;
        sizeFile();
    }

    public DefaultReportLazy(Benchmarks benchmark) throws IOException{
        this.benchmarkPath = "benchmarks/";
        this.tcTotal = -1;
        this.reqTotal = -1;
        this.scoreMax = 1.0;
        switch (benchmark) {
            case bubcorrecto:
                benchmarkPath += "bubcorrecto.csv";
                worthlessReqTotal = 12;
                break;
            case fourballs:
                benchmarkPath += "fourballs.csv";
                worthlessReqTotal = 44;
                break;
            case mid:
                benchmarkPath += "mid.csv";
                worthlessReqTotal = 43;
                break;
            case trityp:
                benchmarkPath += "trityp.csv";
                worthlessReqTotal = 70;
                break;
            case cal:
                benchmarkPath += "cal.csv";
                worthlessReqTotal = 344;
                this.scoreMax = 0.99742;
                break;
            case comm:
                benchmarkPath += "comm.csv";
                worthlessReqTotal = 222;
                break;
            case look:
                benchmarkPath += "look.csv";
                worthlessReqTotal = 233;
                break;
            case uniq:
                benchmarkPath += "uniq.csv";
                worthlessReqTotal = 224;
                break;

            case spaceResultByMethod:
                benchmarkPath += "spaceResultByMethod.csv";
                worthlessReqTotal = 0;
                break;

            case spaceResultByLine:
                benchmarkPath += "spaceResultByLine.csv";
                worthlessReqTotal = 0;
                break;

        }
        
        
        sizeFile();
    }

    /**
     * Reads the benchmarkPath file in order to size it.
     * @throws NoSuchElementException if benchmark path file is empty
     * @throws IOException if benchmarkPath file doesn't exists
     */
    private void sizeFile() throws IOException {
        try (FileReader reader = new FileReader(new File(benchmarkPath))) {
            reqTotal = tcTotal = 0;
            
            boolean lineOver = false;
            while(reader.ready() && !lineOver) switch (reader.read()) {
                case SEPARATOR:
                    tcTotal++;
                    break;
                case '\n':
                    lineOver = true;
                    break;
            }
            while(reader.ready()) if(reader.read() == '\n') reqTotal++;
            
        }
    }
    
    /**
     * Path to benchmark path
     * @return path
     */
    @Override
    public String getBenchmarkPath() {
        return this.benchmarkPath;
    }

    /**
     * Quantity of test cases
     * @return count
     */
    @Override
    public int getTestCaseTotal() {
        return this.tcTotal;
    }

    /**
     * Quantity of test requirements
     * @return count
     */
    @Override
    public int getRequirementTotal() {
        return this.reqTotal;
    }

    /**
     * Default implementation of the {@see ProblemInterface.getTest}
     *
     * @see ProblemInterface.getTest
     */
    @Override
    @SuppressWarnings("empty-statement")
    public boolean getTest(int testCase, int testReq) {
         try(FileReader reader = new FileReader(benchmarkPath)) {
            reader.skip((tcTotal*2 + 1) * testReq + 2 * testCase);
            return reader.read() == '1';
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
}
        
    
    
    /**
     * Quantity of repeated test cases
     * @return 
     */
    @Override
    public int getWorthlessReqTotal() {
        return this.worthlessReqTotal;
    }

    /**
     * Max score to be considerated
     * @return 
     */
    @Override
    public double getScoreMax() {
        return this.scoreMax;
    }

    

}
