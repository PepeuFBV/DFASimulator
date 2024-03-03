import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        DFA automaton = generatingTestAutomaton();
        automaton.accept("baaaaaaaba");
    }
    
    public static DFA generatingTestAutomaton() {
        Set<String> states = new HashSet<>(Arrays.asList("q0", "q1", "q2", "q3"));
        Set<Character> alphabet = new HashSet<>(Arrays.asList('a', 'b'));
        Map<String, Map<Character, String>> transitions = new HashMap<>();
        Map<Character, String> t1 = new HashMap<>();
        t1.put('b', "q1");
        transitions.put("q0", t1);
        Map<Character, String> t2 = new HashMap<>();
        t2.put('a', "q1");
        t2.put('b', "q2");
        transitions.put("q1", t2);
        Map<Character, String> t3 = new HashMap<>();
        t3.put('a', "q3");
        transitions.put("q2", t3);
        Map<Character, String> t4 = new HashMap<>();
        transitions.put("q3", t4);
        
        String initialState = "q0";
        Set<String> acceptingStates = new HashSet<>(List.of("q3"));
        
        return new DFA(states, alphabet, transitions, initialState, acceptingStates);
    }
}