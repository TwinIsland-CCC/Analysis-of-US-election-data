package com.nd.aoue.analyzer;

import com.nd.aoue.analyzer.tools.StCandAnalysisTool;
import org.apache.hadoop.util.ToolRunner;

public class StCandAnalysisData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new StCandAnalysisTool(), args);
    }
}
