package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.robotcore.hardware.DcMotor;



public class Intake{

    DcMotor  IntakeMotor;

    public Intake(DcMotor Motor){
        this.IntakeMotor=Motor;
    }

    public void on(){
        this.IntakeMotor.setPower(.95);

    }

    public void off(){
        this.IntakeMotor.setPower(0);
    }

    public void reverse(){
        this.IntakeMotor.setPower(-1);

    }


}
