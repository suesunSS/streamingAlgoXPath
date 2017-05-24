package Main;

import Algorithm.LazyDFAStreamingAlgorithm;
import Algorithm.SimplePathStreamingAlgorithm;
import Exceptions.StreamingAlgorithmException;
import Model.QueryForm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public class Main {
    //start the program
    public static void main(String[] args) throws StreamingAlgorithmException, IOException {

        //measure the execution time
        long startTime = System.nanoTime();

        String fileXML = args[0];
        String query = args[1];

        QueryForm formOfQuery;
        SimplePathStreamingAlgorithm spsa = null;
        LazyDFAStreamingAlgorithm dfaAlgo = null;
        ArrayList<Integer> output = null;
        int sizeDoc = 0;
        if (query.substring(2).contains("//")) {
            formOfQuery = QueryForm.COMPLEX_PATH;
            dfaAlgo = new LazyDFAStreamingAlgorithm(query);
        } else {
            formOfQuery = QueryForm.SIMPLE_PATH;
            spsa = new SimplePathStreamingAlgorithm(query);
        }

        //streaming algorithm, the input file is read line by line
        BufferedReader br = new BufferedReader(new FileReader(fileXML));
        String line;
        while ((line = br.readLine()) != null) {
            switch (formOfQuery) {
                case SIMPLE_PATH:
                    spsa.processOneLine(line);
                    break;
                case COMPLEX_PATH:
                    dfaAlgo.processOneLine(line);
                break;
            }
        }

        //output the result
        switch (formOfQuery) {
            case SIMPLE_PATH:
                output = spsa.getReturnedOrderList();
                sizeDoc = spsa.getLineNum();
                break;
            case COMPLEX_PATH:
                output = dfaAlgo.getReturnedOrderList();
                sizeDoc = dfaAlgo.getLineNum();
                break;
        }

        if (output != null) {
            output.forEach(System.out::println);
        }

        System.out.println("Size of the document (number of lines): " + sizeDoc);

        long stopTime = System.nanoTime();
        System.out.println("Execution time (in nanoseconds): " + (stopTime - startTime));

        // measure memory usage
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedBytes = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println("Memory usage (in bytes): " + usedBytes);
    }
}
