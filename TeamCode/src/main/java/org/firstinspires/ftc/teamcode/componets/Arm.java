package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm {

    DcMotor arm;
    Servo claw;

    public Arm (DcMotor armMotor, Servo claw){

        this.arm = armMotor;
        this.claw = claw;
    }

    public void up(){
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setPower(1);
    }

    public void down(){
        arm.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setPower(.8);
    }
    public void armOff(){
        arm.setPower(0);
    }

    public void open(){

        claw.setPosition(1);
    }

    public void close(){

        claw.setPosition(0);
    }


}
