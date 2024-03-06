import java.util.*;

/***
 * DFA - Deterministic Finite Automaton
 */
public class DFA {
    
    private final Set<String> states;
    private final Set<Character> alphabet;
    private final Map<String, Map<Character, String>> transitions;
    private final String initialState;
    private final Set<String> acceptingStates;
    
    public DFA() {
        this.states = null;
        this.alphabet = null;
        this.transitions = null;
        this.initialState = null;
        this.acceptingStates = null;
    }
    
    public DFA(Set<String> states, Set<Character> alphabet, Map<String, Map<Character, String>> transitions,
               String initialState, Set<String> acceptingStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
    }
    
    /***
     * Checks if deterministic through iterating in Set to see if every state has a way to it
     * @return is AFD deterministic
     */
    public boolean isDeterministic() {
        for (String state : states) {
            for (char symbol : alphabet) {
                if (!transitions.containsKey(state) || !transitions.get(state).containsKey(symbol)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean accept(String phrase) {
        String nowState = initialState;
        for (char currentSymbol : phrase.toCharArray()) {
            if (!transitions.containsKey(nowState) || !transitions.get(nowState).containsKey(currentSymbol)) {
                return printResult(phrase, false);
            }
            nowState = transitions.get(nowState).get(currentSymbol);
        }
        return printResult(phrase, acceptingStates.contains(nowState));
    }
    
    public boolean printResult(String phrase, boolean accepted) {
        if (phrase.isEmpty()) {
            phrase = "vazia";
        }
        if (accepted) {
            System.out.println("The phrase: " + phrase + ", is accepted by the automaton!");
        } else {
            System.out.println("The phrase: " + phrase + ", isn't accepted by the automaton!");
        }
        
        return accepted;
    }
    
}