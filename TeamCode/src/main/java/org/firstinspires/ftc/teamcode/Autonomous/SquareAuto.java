package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.drive.MecanumDriveBase;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous
//@Disabled
public class SquareAuto extends LinearOpMode {
    static final double FEET_PER_METER = 3.28084;
    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDriveBase drive = new MecanumDriveBase(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //move forward 20 in
        Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                .forward(20)
                .build();

        //move right while maintaining same heading for 20 in
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .strafeRight(20)
                .build();

        //move back for 20 in
        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .back(20)
                .build();

        //move left while maintaining same heading for 20 in
        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .strafeLeft(20)
                .build();

        waitForStart();

        drive.followTrajectory(traj1);
        drive.followTrajectory(traj2);
        drive.followTrajectory(traj3);
        drive.followTrajectory(traj4);


        telemetry.clearAll();

        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
}