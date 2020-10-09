package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.internal.android.dex.Code;
import org.firstinspires.ftc.teamcode.componets.Drivetrain;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.componets.Hopper;
import org.firstinspires.ftc.teamcode.componets.Intake;
import org.firstinspires.ftc.teamcode.componets.Shooter;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class Robot extends OpMode{
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Drivetrain drivetrain;
    /*
    Intake intake;
    DcMotor intakeMotor;
    Servo hopperServo;
    Servo pusherServo;
    Hopper hopper;
    DcMotor shooterMotor;
    Shooter shooter;

    */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        drivetrain = new Drivetrain(leftFront,leftBack,rightFront,rightBack);
        /*
        intake= new Intake(intakeMotor);
        hopperServo = hardwareMap.servo.get("hopperServo");
        pusherServo = hardwareMap.servo.get("pusherServo");
        hopper = new Hopper(hopperServo,pusherServo, 1,0,1,0); // Come back to later and figure out the min and maxs
        shooterMotor = hardwareMap.dcMotor.get("shooterMotor");
        shooter = new Shooter((DcMotorEx) shooterMotor, 3,0.0,0.0,0.0);

         */
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }
    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
        drivetrain.driveCartesian(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
    /*
        if(gamepad1.right_bumper){
            shooter.setSpeed(30); // m/s <---- find through physics
        }else{
            shooter.setSpeed(0);
        }

        if(gamepad1.x && !hopper.isHopperActive){
            hopper.up();
        }else{
            hopper.down();
        }
        if(gamepad1.y && shooter.isReady()){
            hopper.push();
        }else{
            hopper.resetPush();
        }


        if(gamepad1.a){
            intake.on();

        }
        else{
            intake.off();

        }
        if(gamepad1.b){
            intake.reverse();

        }
        else{
            intake.off();

        }
        */

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}