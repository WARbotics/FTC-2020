package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.componets.Arm;
import org.firstinspires.ftc.teamcode.componets.Hopper;
import org.firstinspires.ftc.teamcode.componets.Shooter;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.Vector;

@Autonomous(name="AutoOne", group="Exercises")
// Route A
public class AutoOne extends LinearOpMode {
    Servo claw;
    DcMotor armMotor;
    Arm arm;
    Shooter shooter;
    DcMotor shooterMotor;

    public static double shooterP = 23; //7 /5
    public static double shooterI = 1; //2 / 1.05
    public static double shooterD = 28; // 8  / 3.15
    public static double powerShotSpeed = 7.8;
    public static double shooterSetSpeed = 8.113; // 8.113 / 7.75 /7.65
    Servo hopperServo;
    Servo pusherServo;
    Hopper hopper;
    private ElapsedTime time;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        this.claw = hardwareMap.servo.get("clawServo");
        this.armMotor = hardwareMap.dcMotor.get("armMotor");;
        arm = new Arm(armMotor, claw);

        this.shooterMotor = hardwareMap.dcMotor.get("shooterMotor");
        this.shooter = new Shooter(shooterMotor,.045,  shooterP,shooterI, shooterD);

        hopperServo = hardwareMap.servo.get("hopperServo");
        pusherServo = hardwareMap.servo.get("pusherServo");
        hopper = new Hopper(hopperServo,pusherServo);
        shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        boolean secondRun = false;
        this.time = new ElapsedTime();
        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .addSpatialMarker(new Vector2d(0,0),()->{
                    this.arm.open();
                })
                .addSpatialMarker(new Vector2d(5,5),()->{
                    this.arm.open();
                })
                .splineTo(new Vector2d(73, 20), Math.toRadians(90))
                .addTemporalMarker(1,()->{})
                .addTemporalMarker(1.5, ()->{})
                .addSpatialMarker(new Vector2d(73,20),()->{
                    this.arm.up();
                    this.arm.close();
                })
                .addTemporalMarker(2,()->{})
                .addTemporalMarker(2.5, ()->{})
                //.splineTo(new Vector2d(70,10), Math.toRadians(90))
                .addDisplacementMarker(()->{
                    this.arm.down();
                })
                .splineTo(new Vector2d(58.1176,20.9126), Math.toRadians(345))
                .addTemporalMarker(7,()->{this.arm.down();})
                .addTemporalMarker(8, ()->{this.hopper.resetPush();})
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(myTrajectory);
        if(!drive.isBusy()){
            this.time.reset();
        }
        while (opModeIsActive() && !isStopRequested() && !drive.isBusy()) {

            shooter.setSpeed(shooterSetSpeed); //8.113 /8.25
            if(this.shooter.isReady()) {
                this.hopper.down();
            }else{
                this.hopper.up();
            }
            if(time.seconds() >= 15){
                drive.cancelFollowing();
                secondRun = true;
                break;
            }
        }
        Trajectory startlLine = drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineTo(new Vector2d(70,10), Math.toRadians(90))
                .build();
        if(secondRun){
            drive.followTrajectory(startlLine);
        }
        PoseStorage.currentPose = drive.getPoseEstimate();

    }
}
