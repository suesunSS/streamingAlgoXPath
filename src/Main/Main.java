package Main;

import Algorithm.SimplePathStreamingAlgorithm;
import Exceptions.StreamingAlgorithmException;
import model.QueryForm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Shu SHANG on 15/11/16.
 */

public class Main {
    //start the program
    public static void main(String[] args) throws StreamingAlgorithmException, IOException {
        String fileXML = args[0];
        String query = args[1];

        QueryForm formOfQuery;
        SimplePathStreamingAlgorithm spsa = null;
        ArrayList<Integer> output = null;
        if (query.substring(2).contains("//")) {
            formOfQuery = QueryForm.COMPLEX_PATH;
            //TODO
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
                    //TODO
                    break;
            }
        }

        //output the result
        switch (formOfQuery) {
            case SIMPLE_PATH:
                output = spsa.getReturnedOrderList();
                break;
            case COMPLEX_PATH:
                //TODO
                break;
        }

        if (output != null) {
            for (Integer preOrder : output) {
                System.out.println(preOrder);
            }
        }

    }
}
