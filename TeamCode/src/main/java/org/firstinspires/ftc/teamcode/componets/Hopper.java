package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.Servo;

public class Hopper {

    Servo hopper;
    Servo pusher;

    double MAX_Hopper;
    double MIN_Hopper;
    double MAX_Pusher;
    double MIN_Pusher;

    public boolean isHopperActive = false;
    public boolean isPusherActive = false;

    public Hopper(Servo hopper, Servo pusher, double max_hopper, double min_hopper, double max_pusher, double min_pusher){
        this.hopper = hopper;
        this.pusher = pusher;
        this.MAX_Hopper = max_hopper;
        this.MIN_Hopper = min_hopper;
        this.MAX_Pusher = max_pusher;
        this.MIN_Pusher = min_pusher;

        // The max and min values of the servo should be found through iterative testing
        this.hopper.scaleRange(this.MIN_Hopper, this.MAX_Hopper);
        this.pusher.scaleRange(this.MIN_Pusher, this.MAX_Pusher);
    }

    // This could be automated to allow for the pusher to automatically retract after pushing the ring and we could add some states to where the hopper is

    public void up(){
        this.hopper.setPosition(1);
        this.isHopperActive = true;
    }
    public void down(){
        this.hopper.setPosition(0);
        this.isHopperActive = false;
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





}
