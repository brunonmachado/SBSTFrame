/*
 * Copyright 2014 Bruno.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sbstframe.solution.searchTechniques;


/**
 *
 * @author Bruno
 */
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;
import static sbstframe.solution.searchTechniques.FitnessFunction.count;

public class MyTerminalCondition implements TerminationCondition {
    private int currentCall;
    private int maxCall;
   // private FitnessFunction fit;
    
    public MyTerminalCondition(int maxCall, int currentCall){
        this.currentCall = count;
        this.maxCall = maxCall;
    }


    @Override
    public boolean shouldTerminate(PopulationData<?> populationData) {
       
        if ( this.currentCall > this.maxCall){
            return true;
        } 
            
        return false;
        
    }    
    public long getMaxCall() {
        return this.maxCall;
    }

    public void setMaxCall(int maxCall) {
        this.maxCall = maxCall;
    }

    public void setCurrentCall(int currentCall) {
        this.currentCall = currentCall;
    }

    public int getCurrentCall() {
        return currentCall;
    }


}

