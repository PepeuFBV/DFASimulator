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
    
    public static DFA generatingAutomaton() {
        Scanner sc = new Scanner(System.in);
        
        //receiving and saving automaton's alphabet
        System.out.println("Please insert the automaton alphabet (char separated by spaces, enter to finish): ");
        Set<Character> alphabet = new HashSet<>();
        while (sc.hasNext()) {
            Character letter = sc.next().charAt(0);
            alphabet.add(letter);
        }
        
        //saving the states and all their transitions
        //TODO: add missing transitions to a created (if needed) state called death
        Set<String> states = new HashSet<>();
        Map<String, Map<Character, String>> transitions = new HashMap<>();
        boolean newState = true;
        while (newState) {
            System.out.println("Insert the state: ");
            String state = sc.next();
            states.add(state);
            System.out.print("How many transitions? ");
            int transitionsNum = sc.nextInt();
            for (int i = 0; i < transitionsNum; i++) {
                Map<Character, String> tempMap = new HashMap<>();
                System.out.println("Insert the character for the transition: ");
                Character character = sc.next().charAt(0);
                System.out.println("To which state it will go to: ");
                String nextState = sc.next();
                tempMap.put(character,nextState);
                transitions.put(state, tempMap);
            }
            System.out.println("\nMake a new state (Y/N)? ");
            if (sc.next().equals("N") || sc.next().equals("n")) {
                newState = false;
            }
        }
        
        System.out.println("What is the initial state of the automaton? ");
        String initialState = sc.next();
        
        
        
        
        return new DFA(states, alphabet, transitions, initialState, //missing final states collection);
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