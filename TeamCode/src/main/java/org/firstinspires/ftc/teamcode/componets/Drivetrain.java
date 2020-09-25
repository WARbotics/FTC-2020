package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.hardware.motors.RevRobotics20HdHexMotor;
import com.qualcomm.robotcore.hardware.DcMotor;



public class Drivetrain {

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;

    public Drivetrain(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack){
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
        this.rightFront = rightFront;
    }

    public void driveCartisan(double leftX, double leftY, double rightX){
        // Left stick translation
        // right stick rotation
        double r = Math.hypot(leftX, leftY);
        double robotAngle = Math.atan2(leftY,leftX) - Math.PI/4;

        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;


        leftFront.setPower(v1);
        rightFront.setPower(v2);
        leftBack.setPower(v3);
        rightBack.setPower(v4);
    }

}
