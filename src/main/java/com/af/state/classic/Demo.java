package com.af.state.classic;

class Bulb{
    void on(LightSwitch ls){
        System.out.println("Bulb is already ON");
    }

    void off(LightSwitch ls){
        System.out.println("Bulb is already OFF");
    }
}

class OnBulb extends Bulb{

    public OnBulb() {
        System.out.println("Bulb is turned ON");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching Bulb OFF...");
        ls.setBulb(new OffBulb());
    }
}

class OffBulb extends Bulb{

    public OffBulb() {
        System.out.println("Bulb is turned OFF");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching Bulb ON...");
        ls.setBulb(new OnBulb());
    }
}

class LightSwitch{
    private Bulb bulb; //ON or OFF

    public LightSwitch() {
        this.bulb = new OffBulb();
    }

    public void setBulb(Bulb bulb) {
        this.bulb = bulb;
    }

    void on(){
        bulb.on(this);
    }

    void off(){
        bulb.off(this);
    }
}

public class Demo {
    public static void main(String[] args) {
        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
