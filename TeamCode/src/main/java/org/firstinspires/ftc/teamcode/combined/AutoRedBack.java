package org.firstinspires.ftc.teamcode.combined;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Auto Red Back")
public class AutoRedBack extends NextFTCOpMode {
    public AutoRedBack(){
        addComponents(
        new SubsystemComponent(IntakeSub.INSTANCE),
        new PedroComponent(Constants::createFollower),
        BulkReadComponent.INSTANCE,
        BindingsComponent.INSTANCE
        );

    }

    private Paths paths;

    @Override
    public void onInit() {
        paths = new Paths(PedroComponent.follower());
        PedroComponent.follower().setStartingPose(new Pose(87, 9, Math.toRadians(90)));
        Globals.alliance = Globals.Alliance.RED;
    }

    @Override
    public void onStartButtonPressed() {
        new FollowPath(paths.leave).schedule();
    }

    @Override
    public void onUpdate() {
        Globals.pose = PedroComponent.follower().getPose();
        //FtcDashboard.getInstance().getTelemetry().update();
    }

    public static class Paths {

        public PathChain leave;

        public Paths(Follower follower) {
            leave = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(87.000, 9.000), new Pose(87.000, 33.000))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(90))
                    .build();
        }
    }
}
