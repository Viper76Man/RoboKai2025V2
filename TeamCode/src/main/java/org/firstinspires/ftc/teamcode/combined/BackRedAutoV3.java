package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.combined.subsystems.Adjustablehoodtestsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ColorSensorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub2;
import org.firstinspires.ftc.teamcode.combined.subsystems.HoodSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.LiftSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.MecanumDriveSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.RGBSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ServoSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.TurretBlueBacksub;
import org.firstinspires.ftc.teamcode.combined.subsystems.VisionSub;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Back V3")
public class BackRedAutoV3 extends NextFTCOpMode{
public BackRedAutoV3() {
    addComponents(
            new SubsystemComponent(SpindexerSub.INSTANCE),
            new SubsystemComponent(IntakeSub.INSTANCE),
            new SubsystemComponent(ServoSub.INSTANCE),
            new SubsystemComponent(Adjustablehoodtestsub.INSTANCE),
            new SubsystemComponent(FlywheelSub2.INSTANCE),
            BulkReadComponent.INSTANCE,
            BindingsComponent.INSTANCE
    );
}

        // Declare OpMode members
        private DcMotor leftFront, rightFront, leftBack, rightBack;

        @Override
        public void onStartButtonPressed() {

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
                IntakeSub.INSTANCE.inIntake.schedule();
                HoodSub.INSTANCE.hoodZone4.schedule();
                FlywheelSub2.INSTANCE.flywheelFar.schedule();
                shootSequence().schedule();
                sleep(5000);
                double drivePower = 0.5;
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

            }
        }
    private Command shootSequence() {
        return new SequentialGroup(
                ServoSub.INSTANCE.upramp,
                new Delay(0.7),
                SpindexerSub.INSTANCE.toShootPos,
                new Delay(0.5),
                ServoSub.INSTANCE.downramp
        );
    }
    }

