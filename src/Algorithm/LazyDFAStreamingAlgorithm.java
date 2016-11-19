package Algorithm;

import model.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Shu SHANG on 15/11/16.
 */

public class LazyDFAStreamingAlgorithm {

    private NFA nfa;
    private LazyDFA lazyDFA;
    private LinkedList<DFAState> stateStack;
    private ArrayList<Integer> returnedOrderList;
    private int dfaStateNum;
    private int nodePreOrder;
    private int lineNum;

    public LazyDFAStreamingAlgorithm(String query) {
        nfa = new NFA();
        nfa.XPathToNFA(query);
        lazyDFA = new LazyDFA();
        stateStack = new LinkedList<>();
        returnedOrderList = new ArrayList<>();
        dfaStateNum = 1;
        nodePreOrder = 0;
        lineNum = 0;
        //System.out.println(nfa);
        initStack();
    }

    public ArrayList<Integer> getReturnedOrderList() {
        return returnedOrderList;
    }

    public void processOneLine(String line) {
        lineNum++;
        String[] splitedLine = line.split(" ");
        int startOrEnd = Integer.parseInt(splitedLine[0]);
        String tagName = splitedLine[1];
        StreamingFormatXMLElement element = new StreamingFormatXMLElement(startOrEnd, tagName);

        if (element.getStartOrEnd() == 0) {
            startElement(element.getEleName());
        } else {
            endElement();
        }
    }

    // initialize the stack, call before processing every line
    private void initStack() {
        AutomatonState nfaS1 = nfa.getNfa().get(0);
        AutomatonState nfaS2 = nfa.getNfa().get(1);

        ArrayList<AutomatonState> nfaStates = new ArrayList<>();
        nfaStates.add(nfaS1);
        nfaStates.add(nfaS2);

        // dfa has self start transition ==> true if there is one of its NFA states that is self enclosed
        boolean endState = nfaS1.isEndState() || nfaS2.isEndState();
        boolean hasSelfStarTransition = nfaS1.hasSelfStarTransition() || nfaS2.hasSelfStarTransition();
        DFAState dfaS1 = new DFAState(dfaStateNum++, endState, hasSelfStarTransition, nfaStates);
        lazyDFA.getLazyDFA().add(dfaS1);
        stateStack.push(dfaS1);
    }

    private void startElement(String eleName) {
        DFAState top = stateStack.peek();
        if (top.hasTransition(eleName)) {
            updateAndMatchInStack(top, eleName);
        }
        nodePreOrder++;
    }

    private void endElement() {
        stateStack.pop();
    }

    private void updateAndMatchInStack(DFAState state, String input) {
        ArrayList<AutomatonState> nextNFAStates = state.nextPossibleStates(input);
        DFAState nextState = returnDFAStateIfAlreadyExistInStack(nextNFAStates);
        if (nextState != null) {
            stateStack.push(nextState);
            if (nextState.isEndState()) {
                returnedOrderList.add(nodePreOrder);
            }
        } else {
            ArrayList<AutomatonState> nfaStates = new ArrayList<>();
            for (AutomatonState nfaState : nextNFAStates) {
                if (!nfaStates.contains(nfaState)) {
                    nfaStates.add(nfaState);
                }
                if (nfaState.hasTransition("epsilon")) {
                    AutomatonState stateAfterEpsilonTransition = nfaState.nextState("epsilon");
                    if (!nfaStates.contains(stateAfterEpsilonTransition)) {
                        nfaStates.add(stateAfterEpsilonTransition);
                    }
                }
            }
            boolean isEndState = false;
            boolean hasSelfStarTransition = false;
            for (AutomatonState everyNFAState : nfaStates) {
                if (everyNFAState.isEndState() == true) {
                    isEndState = true;
                }
                if (everyNFAState.hasSelfStarTransition() == true) {
                    hasSelfStarTransition = true;
                }
            }
            DFAState newDFAState = new DFAState(dfaStateNum++, isEndState, hasSelfStarTransition, nfaStates);
            stateStack.push(newDFAState);
            if (newDFAState.isEndState()) {
                returnedOrderList.add(nodePreOrder);
            }
        }
    }

    private DFAState returnDFAStateIfAlreadyExistInStack(ArrayList<AutomatonState> nextStates) {
        DFAState dfaState;
        for (DFAState s : stateStack) {
            if (s.getNfaStates().equals(nextStates)) {
                return s;
            }
        }
        return null;
    }
}
