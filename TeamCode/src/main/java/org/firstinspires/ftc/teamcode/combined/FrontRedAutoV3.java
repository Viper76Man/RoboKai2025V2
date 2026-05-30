package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.combined.subsystems.Adjustablehoodtestsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub2;
import org.firstinspires.ftc.teamcode.combined.subsystems.HoodSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ServoSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Front Red Back V2")
public class FrontRedAutoV3 extends NextFTCOpMode {
    public FrontRedAutoV3() {
        addComponents(
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeSub.INSTANCE),
                new SubsystemComponent(ServoSub.INSTANCE),
                new SubsystemComponent(Adjustablehoodtestsub.INSTANCE),
                new SubsystemComponent(FlywheelSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    // Declare OpMode members
    private DcMotor leftFront, rightFront, leftBack, rightBack;

    @Override
    public void runOpMode() {
        // Initialize the hardware (ensure names match your configuration)
        leftFront  = hardwareMap.get(DcMotor.class, "fl");
        rightFront = hardwareMap.get(DcMotor.class, "fr");
        leftBack   = hardwareMap.get(DcMotor.class, "bl");
        rightBack  = hardwareMap.get(DcMotor.class, "br");

        // Most robots need the left motors reversed to drive forward
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {
            // Set all motors to the same power to move forward
            double drivePower = -0.5;
            leftFront.setPower(drivePower);
            rightFront.setPower(drivePower);
            leftBack.setPower(drivePower);
            rightBack.setPower(drivePower);

            // Run for 1.5 seconds
            sleep(1000);

            // Stop all motors
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
            new Delay(2.0);
            shootSequence2();
            new Delay(15);
        }
    }
    private Command shootSequence2() {
        return new SequentialGroup(
                IntakeSub.INSTANCE.inIntake,
                ServoSub.INSTANCE.upramp,
                new Delay(0.7),
                HoodSub.INSTANCE.hoodZone4,
                FlywheelSub2.INSTANCE.flywheelNear2,
                SpindexerSub.INSTANCE.toShootPos,
                IntakeSub.INSTANCE.stopIntake,
                FlywheelSub2.INSTANCE.flywheelOff
        );
    }
}


