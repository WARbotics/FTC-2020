package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


public class Arm {

    public DcMotor arm;
    Servo claw;

    public Arm (DcMotor armMotor, Servo claw){

        this.arm = armMotor;
        this.claw = claw;
        this.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void up(){

        arm.setTargetPosition(-550);
        arm.setPower(-1);
        this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void drop(){
        arm.setTargetPosition(-1300);
        arm.setPower(-1);
        this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void down(){

        arm.setTargetPosition(0);
        arm.setPower(1);
        this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
