package com.af.state.statemachine.spring;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

enum States{
    OFF_HOOK,       // Starting State
    ON_HOOK,        // Final State
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Events{
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

public class Demo {
    public static void main(String[] args) {

    }
}
