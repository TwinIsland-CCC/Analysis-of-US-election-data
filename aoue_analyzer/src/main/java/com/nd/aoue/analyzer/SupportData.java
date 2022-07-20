package com.nd.aoue.analyzer;

import com.nd.aoue.analyzer.tools.SupportTool;
import org.apache.hadoop.util.ToolRunner;

public class SupportData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new SupportTool(), args);
    }
}
