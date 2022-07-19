package com.nd.aoue.analyzer;

import com.nd.aoue.analyzer.tools.OccupationTool;
import org.apache.hadoop.util.ToolRunner;

public class OccupationData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new OccupationTool(), args);
    }
}
