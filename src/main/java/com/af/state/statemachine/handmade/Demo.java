package com.af.state.statemachine.handmade;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum States {
    OFF_HOOK,       // Starting State
    ON_HOOK,        // Final State
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Triggers {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

public class Demo {

    private static Map<States, List<Pair<Triggers, States>>> rules
                                = new HashMap<>();

    static{
        rules.put(States.OFF_HOOK, List.of(
           new Pair<>(Triggers.CALL_DIALED, States.CONNECTING),
           new Pair<>(Triggers.STOP_USING_PHONE, States.ON_HOOK)
        ));

        rules.put(States.CONNECTING, List.of(
                new Pair<>(Triggers.HUNG_UP, States.OFF_HOOK),
                new Pair<>(Triggers.CALL_CONNECTED, States.CONNECTED)
        ));

        rules.put(States.CONNECTED, List.of(
                new Pair<>(Triggers.LEFT_MESSAGE, States.OFF_HOOK),
                new Pair<>(Triggers.HUNG_UP, States.OFF_HOOK),
                new Pair<>(Triggers.PLACED_ON_HOLD, States.OFF_HOOK)
        ));

        rules.put(States.ON_HOLD, List.of(
                new Pair<>(Triggers.TAKEN_OFF_HOLD, States.CONNECTED),
                new Pair<>(Triggers.HUNG_UP, States.OFF_HOOK)
        ));
    }

    private static States currentState = States.OFF_HOOK;
    private static States exitState = States.ON_HOOK;

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("The Phone is currently : "+currentState);
            System.out.println("Select a Trigger");

            for(int i=0; i<rules.get(currentState).size();i++){
                Triggers trigger = rules.get(currentState).get(i).getValue0();
                System.out.println(""+i+"."+trigger);
            }

            boolean parseOK = false;
            int choice = 0;

            do{
                try{
                    System.out.println("Please enter your choice : ");
                    choice = Integer.parseInt(console.readLine());
                    parseOK = (choice>=0 && choice<=rules.get(currentState).size());
                } catch (IOException e) {
                    e.printStackTrace();
                    parseOK = false;
                }
            }while(!parseOK);

            currentState = rules.get(currentState).get(choice).getValue1();

            if(currentState == exitState){
                break;
            }
        }

        System.out.println("We are done with whole cycle!");
    }
}
