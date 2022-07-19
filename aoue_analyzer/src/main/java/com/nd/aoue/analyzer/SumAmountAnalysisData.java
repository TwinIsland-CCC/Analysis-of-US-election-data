package com.nd.aoue.analyzer;


import com.nd.aoue.analyzer.tools.SumAmountAnalysisTool;
import org.apache.hadoop.util.ToolRunner;

/**
 * 分析数据
 */
public class SumAmountAnalysisData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new SumAmountAnalysisTool(), args);
    }
}
