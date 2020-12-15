package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.android.dex.Code;
import org.firstinspires.ftc.teamcode.componets.Drivetrain;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.componets.Hopper;
import org.firstinspires.ftc.teamcode.componets.Intake;
import org.firstinspires.ftc.teamcode.componets.Shooter;
import org.firstinspires.ftc.teamcode.componets.Arm;



@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Config
public class Robot extends OpMode{
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    Drivetrain drivetrain;

    Intake intake;
    DcMotor intakeMotor;
    Servo hopperServo;
    Servo pusherServo;
    Hopper hopper;
    DcMotor shooterMotor;
    Shooter shooter;
    DcMotor armMotor;
    Servo claw;
    Arm arm;
    public static double shooterP = 5; //7 /5
    public static double shooterI = 1.05; //2 / 1.05
    public static double shooterD = 3.15; // 8  / 3.15

    public static double shooterSetSpeed = 7.9; // 8.113 / 7.75 /7.65
    FtcDashboard dashboard;
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
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        intake = new Intake(intakeMotor);
        hopperServo = hardwareMap.servo.get("hopperServo");
        pusherServo = hardwareMap.servo.get("pusherServo");
        hopper = new Hopper(hopperServo,pusherServo);
        dashboard = FtcDashboard.getInstance();
        armMotor = hardwareMap.dcMotor.get("armMotor");
        claw = hardwareMap.servo.get("clawServo");
        arm = new Arm(armMotor, claw);
        shooterMotor = hardwareMap.dcMotor.get("shooterMotor");
        shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        shooter = new Shooter(shooterMotor, .045,  shooterP,shooterI, shooterD); // 1.15, .35, .50


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


        if(gamepad1.left_bumper){
            shooter.setShooterStatus(true);
        }else{
            shooter.setShooterStatus(false);
        }
        if(shooter.getShooterStatus()){
            shooter.setSpeed(shooterSetSpeed); //8.113 /8.25
            if(shooter.isReady()){
                System.out.println("shoot");

            }
        }else{
            shooter.setSpeed(0);
        }

        if(gamepad1.x){
            hopper.up();
        }
        if(gamepad1.b){
            hopper.down();
        }
        if(gamepad1.y){
            hopper.push();
        }
        if(gamepad1.a && (!gamepad1.left_bumper && !gamepad1.right_bumper)){
            hopper.resetPush();
        }


        if(gamepad1.right_bumper && !gamepad1.a){
            intake.on();
        }
        else{
            intake.off();
        }


        if(gamepad1.dpad_down){
            intake.reverse();
        }

        if(gamepad1.dpad_right){
            arm.down();
        }else{
            arm.armOff();
        }
        if(gamepad1.dpad_left){
            arm.up();
        }else{
            arm.armOff();
        }
        if(gamepad1.a && gamepad1.left_bumper){
            arm.open();
        }
        if(gamepad1.a && gamepad1.right_bumper){
            arm.close();
        }
        TelemetryPacket packet = new TelemetryPacket();
        shooter.update();
        packet.put("shooter_V", shooter.velocity);
        packet.put("target_shooter_v", shooter.targetVelocity);
        dashboard.sendTelemetryPacket(packet);
        telemetry.addData("Ready to fire: ", this.shooter.isReady());
        telemetry.addData("Shooter Active: ", this.shooter.getShooterStatus());
        telemetry.addData("Shooter speed: ", this.shooter.getShooterSpeed());
        telemetry.addLine();
    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}