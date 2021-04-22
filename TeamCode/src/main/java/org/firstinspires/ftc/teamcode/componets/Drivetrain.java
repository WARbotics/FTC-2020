package org.firstinspires.ftc.teamcode.componets;

import com.qualcomm.hardware.motors.RevRobotics20HdHexMotor;
import com.qualcomm.robotcore.hardware.DcMotor;import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
//import com.kauailabs.navx.ftc.AHRS;
//import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;




public class Drivetrain {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

   /* private final int NANX_DIM_I2C_PORT=0;
    private AHRS navx_device;
    private navXPIDController yawPIDController;
    private ElapsedTime runtime = new ElapsedTime();

    private final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;

    private final double TARGET_ANGLE_DEGREES = 0.0;
    private final double TOLERANCE_DEGREES = 2.0;
    private final double MIN_MOTOR_OUTPUT_VALUE = -1.0;
    private final double MAX_MOTOR_OUTPUT_VALUE = 1.0;
    private final double YAW_PID_P = 0.005;
    private final double YAW_PID_I = 0.0;
    private final double YAW_PID_D = 0.0;

    private final double TOTAL_RUN_TIME_SECONDS = 10.0;



    private int DEVICE_TIMEOUT_MS = 500;
    private navXPIDController.PIDResult yawPIDResult = new navXPIDController.PIDResult();
    double drive_speed = 0.5;

    */

    public Drivetrain(DcMotor leftFront, DcMotor leftBack, DcMotor rightFront, DcMotor rightBack){
        this.leftFront = leftFront;
        this.leftBack = leftBack;
        this.rightBack = rightBack;
        this.rightFront = rightFront;


        /*yawPIDController.setContinuous(true);
        yawPIDController.setOutputRange(MIN_MOTOR_OUTPUT_VALUE, MAX_MOTOR_OUTPUT_VALUE);
        yawPIDController.setTolerance(navXPIDController.ToleranceType.ABSOLUTE, TOLERANCE_DEGREES);
        yawPIDController.setPID(YAW_PID_P, YAW_PID_I, YAW_PID_D);
        yawPIDController.enable(true);*/
/*
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/


    }



    public void driveCartesian(double x, double y, double theta){

        leftFront.setPower((y - x - theta)*1.80);
        rightFront.setPower((y + x + theta)*1.80);
        leftBack.setPower((y + x - theta));
        rightBack.setPower((y - x + theta));



    }

   public void driveForward(int distance){
    rightBack.setTargetPosition(distance);
    leftBack.setTargetPosition(distance);

        leftBack.setPower(-1);
        rightBack.setPower(-1);

    leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
   }


   /* public void turnTo(double degrees) throws InterruptedException {
        yawPIDController.setSetpoint(degrees);
        while (runtime.time() < TOTAL_RUN_TIME_SECONDS) {
            if (yawPIDController.waitForNewUpdate(yawPIDResult, DEVICE_TIMEOUT_MS)) {
                if (yawPIDResult.isOnTarget()) {
                    leftFront.setPower(drive_speed);
                    rightBack.setPower(drive_speed);
                    leftBack.setPower(drive_speed);
                    rightFront.setPower(drive_speed);
                } else {
                    double output = yawPIDResult.getOutput();
                    if (output < 0) {
                        //Rotate Left
                        leftFront.setPower(drive_speed - output);
                        rightFront.setPower(drive_speed + output);
                        leftBack.setPower(drive_speed - output);
                        rightBack.setPower(drive_speed + output);
                    } else {
                        //Rotate Right
                        leftFront.setPower(drive_speed + output);
                        rightFront.setPower(drive_speed - output);
                        leftBack.setPower(drive_speed + output);
                        rightBack.setPower(drive_speed - output);
                    }
                }
            } else {
                //A timeout occurred

            }
            }*/




    }
