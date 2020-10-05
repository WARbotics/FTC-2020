package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.componets.Drivetrain;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class Robot extends OpMode{
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Drivetrain drivetrain;


    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        drivetrain = new Drivetrain(leftFront,leftBack,rightFront,rightBack);

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


    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}