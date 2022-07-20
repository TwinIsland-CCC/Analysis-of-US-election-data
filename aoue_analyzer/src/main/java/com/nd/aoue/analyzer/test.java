package com.nd.aoue.analyzer;

import com.nd.aoue.analyzer.tools.testtool;
import org.apache.hadoop.util.ToolRunner;

public class test {
    public static void main(String[] args) throws Exception {
        ToolRunner.run(new testtool(), args);
    }
}
