package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.componets.Hopper;
import org.firstinspires.ftc.teamcode.componets.Intake;
import org.firstinspires.ftc.teamcode.componets.Shooter;


// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Auto", group="Exercises")
//@Disabled
public class Auto extends LinearOpMode
{
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor shooterMotor;
    Servo pusherServo;
    Shooter shooter;
    Servo hopperServo;
    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        pusherServo = hardwareMap.servo.get("pusherServo");
        hopperServo = hardwareMap.servo.get("hopperServo");
        shooterMotor = hardwareMap.dcMotor.get("shooterMotor");
        shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter = new Shooter(shooterMotor, .045,  1.15,.23, .50); // 1, 1.1, .05



        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 30% power.
        leftFront.setPower(0.4);
        leftBack.setPower(0.4);
        rightFront.setPower(0.4);
        rightBack.setPower(0.4);




        sleep(8500);        // wait for 4 seconds.

        // set motor power to zero to stop motors.

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        sleep(500);
    /*
        leftFront.setPower(-.25);
        leftBack.setPower(0.25);
        rightFront.setPower(0.25);
        rightBack.setPower(-0.25);

        sleep(5000);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        shooter.setSpeed(7.6);


        while(true && opModeIsActive()){
            pusherServo.setPosition(1);

            sleep(500);

            pusherServo.setPosition(0);
        }

     */
    }
}