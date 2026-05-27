package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.combined.subsystems.Adjustablehoodtestsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ColorSensorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub2;
import org.firstinspires.ftc.teamcode.combined.subsystems.HoodSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.LiftSub2;
import org.firstinspires.ftc.teamcode.combined.subsystems.MecanumDriveSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.RGBSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ServoSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.Turretsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.VisionSub;

import java.util.List;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

@TeleOp(name = "Blue Teleop Combined V3", group = "Coach")
public class BlueV3 extends NextFTCOpMode {
    public BlueV3(){
        addComponents(
                new SubsystemComponent(MecanumDriveSub.INSTANCE),
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeSub.INSTANCE),
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                new SubsystemComponent(ServoSub.INSTANCE),
                new SubsystemComponent(RGBSub.INSTANCE),
                new SubsystemComponent(LiftSub2.Instance),
                new SubsystemComponent(Adjustablehoodtestsub.INSTANCE),
                new SubsystemComponent(FlywheelSub2.INSTANCE),
                new SubsystemComponent(VisionSub.INSTANCE),
                new SubsystemComponent(Turretsub.Instance),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit(){
        //Init stuff here
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        //Possible fix for first servo delay
        ServoSub.INSTANCE.downramp.schedule();

        //Start intake without group
        IntakeSub.INSTANCE.inIntake.schedule();

        loadingSequence().schedule();

        Gamepads.gamepad1().leftTrigger().atLeast(0.3)
                .whenBecomesTrue(new SequentialGroup (
                        IntakeSub.INSTANCE.outIntake
                ))
                .whenBecomesFalse(new SequentialGroup(
                        IntakeSub.INSTANCE.inIntake
                ));

        Gamepads.gamepad1().square()
                .whenBecomesTrue(new SequentialGroup(
                        FlywheelSub2.INSTANCE.flywheelOff,
                        IntakeSub.INSTANCE.stopIntake

                ));
//        Gamepads.gamepad1().circle()
//                .whenBecomesTrue( LiftSub.INSTANCE.up);
//
//        Gamepads.gamepad1().touchpad()
//                .whenBecomesTrue(LiftSub.INSTANCE.down);
        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone1);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone2);

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone3);
        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone5);

        Gamepads.gamepad1().rightTrigger().atLeast(0.3)
                .whenBecomesTrue(new SequentialGroup (
                        shotSequence(),
                        new Delay(.4),
                        loadingSequence()
                ));
        Command driveControlled = new MecanumDriverControlled(
                MecanumDriveSub.INSTANCE.frontLeft,
                MecanumDriveSub.INSTANCE.frontRight,
                MecanumDriveSub.INSTANCE.backLeft,
                MecanumDriveSub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driveControlled.schedule();
    }
    @Override
    public void onUpdate() {
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Detected Color", ColorSensorSub.INSTANCE.getDetectedColor(telemetry));
        telemetry.addData("Distance", ColorSensorSub.INSTANCE.getDistance());
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
//        telemetry.addData("Lift Distance",LiftSub2.);
        telemetry.addData("Hood Position",Adjustablehoodtestsub.INSTANCE.getHoodposition());
        telemetry.addData("Distance to Goal", VisionSub.INSTANCE.totalDistanceGoal());
        telemetry.addData("Zone", VisionSub.INSTANCE.getDectectedZone());
        telemetry.addData("tx",VisionSub.INSTANCE.getTx());
        telemetry.addData("Has Target",VisionSub.INSTANCE.hastarget());
        telemetry.addData("Command Position", Turretsub.Instance.turret.getPosition());
        telemetry.update();

        if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.ZONE0) {
            FlywheelSub2.INSTANCE.flywheelNear.schedule();
            HoodSub.INSTANCE.hoodZone2.schedule();
        }
        else if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.ZONE1) {
            FlywheelSub2.INSTANCE.flywheelNear.schedule();
            HoodSub.INSTANCE.hoodZone5.schedule();
        }
        else if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.Zone4){
            FlywheelSub2.INSTANCE.flywheelNear2.schedule();
            HoodSub.INSTANCE.hoodZone4.schedule();
        }
        else if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.ZONE2) {
            FlywheelSub2.INSTANCE.flywheelMiddle.schedule();
            HoodSub.INSTANCE.hoodZone3.schedule();
        }
        else if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.Zone3)
        {
            FlywheelSub2.INSTANCE.flywheelFar.schedule();
            // Add Angle
        }
        else if (VisionSub.INSTANCE.getDectectedZone() == VisionSub.DetectedZone.UNKOWN)
            FlywheelSub2.INSTANCE.flywheelOff.schedule();

    }

    @Override
    public void onStop(){
        VisionSub.INSTANCE.stopCamera();
        stopIntake();
        stopShooter();
    }


    private Command loadingSequence() {
        return new SequentialGroup(
                SpindexerSub.INSTANCE.toFirstPos,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toSecondPOS,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toThirdPos,
                RGBSub.INSTANCE.orange,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                RGBSub.INSTANCE.green
        );
    }
    private Command shotSequence(){
        return  new SequentialGroup(
                IntakeSub.INSTANCE.stopIntake,
                ServoSub.INSTANCE.upramp,
                new Delay(0.7),
                SpindexerSub.INSTANCE.toShootPos,
                IntakeSub.INSTANCE.inIntake,
                ServoSub.INSTANCE.downramp,
                RGBSub.INSTANCE.off
        );
    }
    private Command stopIntake(){
        return new SequentialGroup(
                IntakeSub.INSTANCE.stopIntake
        );
    }
    private Command stopShooter(){
        return new SequentialGroup(
                FlywheelSub2.INSTANCE.flywheelOff
        );
    }

//    private Command raise () {
//        Adjustablehoodtestsub.INSTANCE.adjustmentup();
//        return null;
//    }
//    private Command lower () {
//        Adjustablehoodtestsub.INSTANCE.adjustmentdown();
//
//        return null;
//    }
}

// Square Flywheel and Intake off
// Circle liftup
// Touchpad lift down
// Left Trigger is the out intake and can help to turn the spindexer back on.



