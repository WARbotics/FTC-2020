package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Shooter {

    DcMotorEx shooterMotor;
    double radius;
    double UPPER_BOUND;
    double LOWER_BOUND;

    public Shooter(DcMotorEx shooterMotor, double radius, double p, double i, double d){
        this.shooterMotor = shooterMotor;
        this.shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(p,i,d,0));
        this.radius = radius;
    }

    public void setSpeed(double speed){
        double angularV = speed/radius;
        this.shooterMotor.setVelocity(angularV);
    }

    public boolean isReady(){
        double speed = this.shooterMotor.getVelocity(AngleUnit.RADIANS)/this.radius; // m/s
        if(speed < UPPER_BOUND && speed > LOWER_BOUND){
            return true;
        }
        return false;
    }





}
