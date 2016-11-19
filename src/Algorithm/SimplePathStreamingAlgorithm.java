package Algorithm;

import Exceptions.ExceptionIndex;
import Exceptions.StreamingAlgorithmException;
import model.StreamingFormatXMLElement;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Shu SHANG on 14/11/16.
 */
public class SimplePathStreamingAlgorithm {
    private String[] stepArray; //query parameters
    private LinkedList<Integer> positionStack;
    private ArrayList<Integer> returnedOrderList;
    private int queryMatchPos;
    private int nodePreOrder;
    private int lineNum;

    public SimplePathStreamingAlgorithm(String query) throws StreamingAlgorithmException {
        parseQuery(query);
        positionStack = new LinkedList<>();
        returnedOrderList = new ArrayList<>();
        queryMatchPos = 1;
        nodePreOrder = 0;
        lineNum = 0;
    }

    private void parseQuery(String simplePathQuery) throws StreamingAlgorithmException {
        if (!simplePathQuery.startsWith("/")) {
            throw new StreamingAlgorithmException(ExceptionIndex.queryNotStartWithSlash);
        }
        stepArray = simplePathQuery.substring(2).split("/");
    }

    //streaming process of every line of input
    public void processOneLine(String line) {
        lineNum++;
        String[] splitedLine = line.split(" ");
        int startOrEnd = Integer.parseInt(splitedLine[0]);
        String tagName = splitedLine[1];
        StreamingFormatXMLElement element = new StreamingFormatXMLElement(startOrEnd, tagName);

        if (startOrEnd == 0) {
            if (element.getEleName().equals(stepArray[queryMatchPos - 1])) {
                if (queryMatchPos < stepArray.length) {
                    if (nodePreOrder != 0) {
                        positionStack.push(queryMatchPos);
                    }
                    queryMatchPos = queryMatchPos + 1;
                } else {
                    returnedOrderList.add(nodePreOrder);
                    if (nodePreOrder != 0) {
                        positionStack.push(queryMatchPos);
                    }
                    queryMatchPos = 1;
                }
            } else {
                positionStack.push(queryMatchPos);
            }
            nodePreOrder++;
        } else {
            // for the last close tag for the root node, stack is empty
            if (!positionStack.isEmpty()) {
                queryMatchPos = positionStack.pop();
            }
        }
    }

    public ArrayList<Integer> getReturnedOrderList() {
        return returnedOrderList;
    }
}
