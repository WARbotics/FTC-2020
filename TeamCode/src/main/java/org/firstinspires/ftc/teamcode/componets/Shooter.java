package org.firstinspires.ftc.teamcode.componets;

import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


import java.util.ArrayList;
@Config
public class Shooter {
    boolean shooterStatus = false;
    boolean shooterStatusPowerShot = false;
    public DcMotorEx shooterMotor;
    double radius;
    public static double UPPER_BOUND = 8.175;
    public static double LOWER_BOUND = 8.05;
    public double velocity = 0;
    public double targetVelocity = 0;
    ArrayList<Double> speedRn = new ArrayList<Double>();
    public Shooter(DcMotor shooterMotor, double radius, double p, double i, double d){
        this.shooterMotor = (DcMotorEx) shooterMotor;
        this.shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(p,i,d,.10));
        this.radius = radius;
    }

    public void setSpeed(double speed){

        //System.out.println(this.shooterMotor.getVelocity(AngleUnit.RADIANS));
        this.speedRn.add(this.shooterMotor.getVelocity(AngleUnit.RADIANS));
        this.targetVelocity = speed;
        this.shooterMotor.setVelocity(speed,AngleUnit.RADIANS);
    }

    public boolean isReady(){
        double speed = this.shooterMotor.getVelocity(AngleUnit.RADIANS); // m/s
        if(speed < UPPER_BOUND && speed > LOWER_BOUND){
            return true;
        }
        return false;
    }
    public double getShooterSpeed(){
        return this.shooterMotor.getVelocity(AngleUnit.RADIANS);
    }
    public boolean getShooterStatus(){
        return this.shooterStatus;
    }
    public void setShooterStatus(boolean status){
        this.shooterStatus = status;
    }
    public void setShooterStatusPower(boolean status){ this.shooterStatusPowerShot = status;}
    public boolean powerShotStatus(){
        return this.shooterStatusPowerShot;
    }
    public void getSpeedArr(){
        System.out.println(this.shooterMotor.getVelocity(AngleUnit.RADIANS));
        /*
        for (double i: this.speedRn)
        {
            System.out.println(i);
        }

         */
    }
    public void update(){
        this.velocity = this.shooterMotor.getVelocity(AngleUnit.RADIANS);
    }



}
