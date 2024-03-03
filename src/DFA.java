/*
1º Trabalho da disciplina de Linguagens Formais e Autômatos
Objetivo:
• Implementar um simulador de autômatos finitos determinísticos – AFD, que permita ao usuário testar o
reconhecimento de diversas cadeias para qualquer AFD de entrada.
Requisitos:
• O programa deverá permitir que o usuário introduza qualquer AFD, ou seja, para montar o autômato
A = (Q, Σ, δ, q0, F) o usuário deverá informar:

- Os estados do AFD
- O alfabeto do AFD
- A função de transição
- O estado inicial
- Os estados de aceitação

• Como forma de simplificação, é permitida a restrição do alfabeto a apenas 2 símbolos.
• Não é necessária a implementação de interface gráfica.
• O programa deverá rejeitar autômatos inválidos (Não determinísticos)
• Os componentes do autômato poderão ser lidos diretamente pelo console ou por um arquivo de entrada, ficando
a critério do programador a implementação que achar mais conveniente.
 */

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
            System.out.println("A cadeia " + phrase + ", é aceita pelo automato!");
        } else {
            System.out.println("A cadeia " + phrase + ", não é aceita pelo automato!");
        }
        
        return accepted;
    }
    
}