package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.Servo;

public class Hopper {

    Servo hopper;
    Servo pusher;



    public boolean isHopperUp = false;
    public boolean isPusherActive = false;

    public Hopper(Servo hopper, Servo pusher){
        this.hopper = hopper;
        this.pusher = pusher;

    }

    // This could be automated to allow for the pusher to automatically retract after pushing the ring and we could add some states to where the hopper is

    public void up(){
        this.hopper.setPosition(1);
        this.isHopperUp = true;
    }
    public void down(){
        this.hopper.setPosition(0);
        this.isHopperUp = false;
    }

    public void push(){
        // This can be automated after we find out the time of pushing
        this.pusher.setPosition(1);
        this.isPusherActive = true;
    }
    public void resetPush(){
        this.pusher.setPosition(0);
        this.isPusherActive = false;
    }

    public boolean getHopperStatus(){
        return this.isHopperUp;
    }




}
