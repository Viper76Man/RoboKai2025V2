package org.firstinspires.ftc.teamcode.combined;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants;
    public static String frontLeft = "fl";
    public static String frontRight = "fr";
    public static String backLeft = "bl";
    public static String backRight = "br";
    public static DcMotorSimple.Direction frontLeftDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction backLeftDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction frontRightDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction backRightDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction frontLeftDirectionAuto = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction backLeftDirectionAuto = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction frontRightDirectionAuto = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction backRightDirectionAuto = DcMotorSimple.Direction.REVERSE;
    public static boolean useBrakeInTeleop = true;
    public String pinpointName = "pinpoint";


    static {
        followerConstants = new FollowerConstants()
                .mass(16.09)
                .headingPIDFCoefficients(new PIDFCoefficients(0.7, 0, 0.01, 0))
                .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0.4, 0.08040006101929494, 0.0014481301913960136));
    }

    public static MecanumConstants autoConstants = new MecanumConstants()
            .maxPower(1)
            .yVelocity(50.83)
            .xVelocity(65.42);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.95,
            50,
            1.5,
            1
    );

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-4.5866) // TODO: offsets
            .strafePodX(-1.991)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
