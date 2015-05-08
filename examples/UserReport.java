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

import java.util.HashMap;
import sbstframe.problem.ProblemInterface;

class UserReport implements ProblemInterface {
    String path;
    double fitnessMax;
    int worthless;
    HashMap s;
    int tcTotal;
    int reqTotal;

    public UserReport(String path, double fitnessMax, int worthless) {
        this.path = path;
        this.fitnessMax = fitnessMax;
        this.worthless = worthless;
        readBenchmarkFile();
        
    }

    public void readBenchmarkFile() {
        //read path
        HashMap h = new HashMap<Integer, Integer>();
        s = new HashMap<Integer, HashMap<Integer, Integer>>();
        
        h.put(0, 0);
        h.put(1, 0);
        h.put(2, 1);
        s.put(0, h);
        
        h.put(0, 0);
        h.put(1, 0);
        h.put(2, 0);
        s.put(1, h);
        
        h.put(0, 1);
        h.put(1, 1);
        h.put(2, 0);
        s.put(2, h);
        
        tcTotal = 3;
        reqTotal = 3;
    }

    @Override
    public String getBenchmarkPath() {
        return this.path;
    }

    @Override
    public int getTestCaseTotal() {
        return tcTotal;
    }

    @Override
    public int getRequirementTotal() {
        return reqTotal;
    }

    @Override
    public int getWorthlessReqTotal() {
        return worthless;
    }

    @Override
    public HashMap<Integer, HashMap<Integer, Integer>> getTests() {
        return s;
    }

    @Override
    public double getScoreMax() {
        return this.fitnessMax;
    }

    
    
}
