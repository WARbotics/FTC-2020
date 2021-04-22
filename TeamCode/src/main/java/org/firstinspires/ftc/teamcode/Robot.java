package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.componets.Drivetrain;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.componets.Hopper;
import org.firstinspires.ftc.teamcode.componets.Intake;
import org.firstinspires.ftc.teamcode.componets.Shooter;
import org.firstinspires.ftc.teamcode.componets.Arm;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Config
public class Robot extends LinearOpMode{
    SampleMecanumDrive drive;
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
    public static double shooterP = 23; //7 /5
    public static double shooterI = 1; //2 / 1.05
    public static double shooterD = 28; // 8  / 3.15
    public static double powerShotSpeed = 7.8;
    public static double shooterSetSpeed = 8.113; // 8.113 / 7.75 /7.65
    FtcDashboard dashboard;

    enum Mode {
        DRIVER_CONTROL,
        AUTOMATIC_CONTROL
    }
    Mode currentMode = Mode.DRIVER_CONTROL;
    @Override
    public void runOpMode() throws InterruptedException {

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
        drive = new SampleMecanumDrive(hardwareMap);
        shooter = new Shooter(shooterMotor, .045,  shooterP,shooterI, shooterD); // 1.15, .35, .50

        this.drive.setPoseEstimate(PoseStorage.currentPose);

        Pose2d poseEstimate = drive.getPoseEstimate();

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {
            drive.update();
            TelemetryPacket packet = new TelemetryPacket();
            shooter.update();
            packet.put("shooter_V", shooter.velocity);
            packet.put("target_shooter_v", shooter.targetVelocity);

            dashboard.sendTelemetryPacket(packet);

            telemetry.addData("Ready to Fire: ", this.shooter.isReady());
            telemetry.addLine();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
            switch (currentMode) {
                case DRIVER_CONTROL:

                    drive.setWeightedDrivePower(
                            new Pose2d(
                                    -gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x,
                                    -gamepad1.right_stick_x
                            )
                    );

                    if (gamepad1.right_trigger > 0) {
                        // If the A button is pressed on gamepad1, we generate a splineTo()
                        // trajectory on the fly and follow it
                        // We switch the state to AUTOMATIC_CONTROL Math.toRadians(334)

                        Trajectory traj1 = drive.trajectoryBuilder(poseEstimate)
                                .lineToSplineHeading(new Pose2d(53.1176,20.9126, Math.toRadians(353.607)))
                                .build();

                        drive.followTrajectory(traj1);

                        currentMode = Mode.AUTOMATIC_CONTROL;
                    }



                    if (gamepad1.left_trigger > 0) {
                        shooter.setShooterStatus(true);
                    } else {
                        shooter.setShooterStatus(false);
                    }
                    if (shooter.getShooterStatus()) {
                        shooter.setSpeed(shooterSetSpeed); //8.113 /8.25
                        if(this.shooter.isReady()) {
                            this.hopper.down();
                        }else{
                            this.hopper.up();
                        }
                    } else {

                        shooter.setSpeed(0);
                    }

                    /*
                    if(gamepad1.b){ //334
                        drive.turn(Math.toRadians(334));

                        currentMode = Mode.AUTOMATIC_CONTROL;
                    }
                       */
                    /*
                    if (gamepad1.x) {
                        this.hopper.up();
                    }

                    if (gamepad1.b) {

                        if(this.shooter.isReady()) {
                            this.hopper.down();
                        }


                        //this.hopper.down();
                    }


                    */

                    if (gamepad1.y) {
                        hopper.push();
                    }
                    if (gamepad1.a && (!gamepad1.left_bumper && !gamepad1.right_bumper)) {
                        hopper.resetPush();
                    }


                    if (gamepad1.right_bumper && !gamepad1.a) {
                        intake.on();
                    } else {
                        intake.off();
                    }


                    if (gamepad1.dpad_down) {
                        intake.reverse();
                    }

                    if (gamepad1.dpad_right) {
                        arm.down();
                    }

                    if (gamepad1.dpad_left) {
                        arm.up();
                    }
                    if (gamepad1.dpad_up) {
                        arm.drop();
                    }

                    if (gamepad1.a && gamepad1.left_bumper) {
                        arm.open();
                    }
                    if (gamepad1.a && gamepad1.right_bumper) {
                        arm.close();
                    }
                    break;
                case AUTOMATIC_CONTROL:
                    // If x is pressed, we break out of the automatic following
                    if (gamepad1.x) {
                        drive.cancelFollowing();
                        currentMode = Mode.DRIVER_CONTROL;
                    }


                    // If drive finishes its task, cede control to the driver
                    if (!drive.isBusy()) {
                        currentMode = Mode.DRIVER_CONTROL;
                    }
                    break;

            }




        }
    }



}

