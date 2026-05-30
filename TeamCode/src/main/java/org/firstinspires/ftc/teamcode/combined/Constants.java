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
public static MecanumConstants driveConstants = new MecanumConstants()
        .maxPower(.5)
        .rightFrontMotorName("fr")
        .rightRearMotorName("br")
        .leftFrontMotorName("fl")
        .leftRearMotorName("bl")
        .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
        .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
        .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
        .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
        .yVelocity(50.83)
        .xVelocity(65.42);
    public static PathConstraints pathConstraints = new PathConstraints(
            1,
            600,
            1.25,
            1
    );

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(2) // TODO: offsets
            .strafePodX(-6)
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
}
