package com.nd.aoue.analyzer;

import com.nd.aoue.analyzer.tools.OccupationPartyAnalysisTool;
import org.apache.hadoop.util.ToolRunner;

public class OccupationPartyAnalysisData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new OccupationPartyAnalysisTool(), args);
    }
}
