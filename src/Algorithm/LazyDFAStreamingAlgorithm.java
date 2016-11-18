package Algorithm;

import model.NFA;
import model.NFAState;
import sun.jvm.hotspot.tools.SysPropsDumper;

/**
 * Created by Shu SHANG on 15/11/16.
 */

public class LazyDFAStreamingAlgorithm {

    public LazyDFAStreamingAlgorithm(String query) {
        NFA newNFA = new NFA();
        newNFA.XPathToNFA(query);
        newNFA.getNfa().forEach(System.out::println);
    }



}
