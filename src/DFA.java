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
        Set<Character> alphabet = new LinkedHashSet<>();
        Arrays.stream(sc.nextLine().split(" ")).forEach(letter -> {
            alphabet.add(letter.charAt(0));
        });
        
        Set<String> states = new LinkedHashSet<>();
        Map<String, Map<Character, String>> transitions = new HashMap<>();
        
        System.out.print("How many states does the automaton have, including the death state? ");
        int statesAmount = sc.nextInt(); sc.nextLine();
        
        // reads all automaton states
        for (int i = 1; i <= statesAmount; i++) {
            System.out.println("Insert the state " + i + ": ");
            states.add(sc.nextLine());
        }
        System.out.println("");
        
        // reads all transitions, alphabet.size() for each state
        for (String state : states) {
            Map<Character, String> tempMap = new HashMap<>();
            
            for (Character character : alphabet) {
                System.out.println("From state " + state + ", where will '" + character + "' go? ");
                String nextState;
                while (!states.contains(nextState = sc.nextLine())) {
                    System.out.println("This state doesn't exist!");
                    System.out.println("Please inform the state again: ");
                }
                
                tempMap.put(character, nextState);
            }
            transitions.put(state, tempMap);
        }
        
        System.out.println("\nWhat is the initial state of the automaton? ");
        String initialState;
        while (!states.contains(initialState = sc.nextLine())) {
            System.out.println("This state doesn't exist!");
            System.out.println("Please inform the initial state again: ");
        }
        
        System.out.println("\nWhat are the final states of the automaton? "
                + "(states separated by spaces, enter to finish)");
        Set<String> acceptingStates = new HashSet<>();
        Arrays.stream(sc.nextLine().split(" ")).forEach(readState -> {
            if (states.contains(readState)) {
                acceptingStates.add(readState);
            } else {
                System.out.println("The state " + readState + " is not part of the automaton's state set!");
            }
        });
        
        return new DFA(states, alphabet, transitions, initialState, acceptingStates);
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
            phrase = "empty";
        }
        if (accepted) {
            System.out.println("The phrase: " + phrase + ", is accepted by the automaton!");
        } else {
            System.out.println("The phrase: " + phrase + ", isn't accepted by the automaton!");
        }
        
        return accepted;
    }
    
}