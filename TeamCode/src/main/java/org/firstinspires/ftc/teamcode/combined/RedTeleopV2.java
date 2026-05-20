package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.combined.subsystems.Adjustablehoodtestsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ColorSensorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.HoodSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.LiftSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.MecanumDriveSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.RGBSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ServoSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.Turretsub;
import org.firstinspires.ftc.teamcode.combined.subsystems.VisionRedSub;
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

@TeleOp(name = "Red Teleop V2", group = "Coach")
public class RedTeleopV2  extends NextFTCOpMode {
    public RedTeleopV2(){
        addComponents(
                new SubsystemComponent(MecanumDriveSub.INSTANCE),
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeSub.INSTANCE),
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                new SubsystemComponent(ServoSub.INSTANCE),
                new SubsystemComponent(RGBSub.INSTANCE),
                new SubsystemComponent(LiftSub.INSTANCE),
                new SubsystemComponent(Adjustablehoodtestsub.INSTANCE),
                new SubsystemComponent(FlywheelSub.INSTANCE),
                new SubsystemComponent(VisionRedSub.INSTANCE),
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
                     alloff()
                ));
        // I want to try this and see if it can help it to hold the position
        Gamepads.gamepad1().circle()
                .whenBecomesTrue( LiftSub.INSTANCE.up);

        Gamepads.gamepad1().touchpad()
                .whenBecomesTrue(LiftSub.INSTANCE.down);
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
//        Gamepads.gamepad1().dpadUp()
//                        .whenBecomesTrue(new SequentialGroup(
//                                raise()
//                        ));
//        Gamepads.gamepad1().dpadDown()
//                        .whenBecomesTrue(new SequentialGroup(
//                                lower()
//                        ));

/*        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);
*/
//        Gamepads.gamepad1().dpadLeft()
//                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone1);
//
//        Gamepads.gamepad1().dpadUp()
//                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone2);
//
//        Gamepads.gamepad1().dpadRight()
//                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone3);
//        Gamepads.gamepad1().dpadDown()
//                .whenBecomesTrue(HoodSub.INSTANCE.hoodZone5);
//        Gamepads.gamepad1().circle()
//                        .whenBecomesTrue(LiftSub.INSTANCE.up);
        // I need to look at the axons and make sure that they are

//        Gamepads.gamepad1().triangle()
//                        .whenBecomesTrue(LiftSub.INSTANCE.down);
//        Gamepads.gamepad1().rightBumper()
//                .whenBecomesTrue(FlywheelSub.INSTANCE.flywheelNear);
//        Gamepads.gamepad1().leftBumper()
//                .whenBecomesTrue(FlywheelSub.INSTANCE.flywheelFar);


//                ));
//        Gamepads.gamepad1().cross()
//                .whenBecomesTrue(IntakeSub.INSTANCE.inIntake);




    }

    @Override
    public void onUpdate() {
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Detected Color", ColorSensorSub.INSTANCE.getDetectedColor(telemetry));
        telemetry.addData("Distance", ColorSensorSub.INSTANCE.getDistance());
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        //telemetry.addData("Lift Distance",LiftSub.INSTANCE.rightA);
        telemetry.addData("Hood Position",Adjustablehoodtestsub.INSTANCE.getHoodposition());
        telemetry.addData("Distance to Goal", VisionSub.INSTANCE.totalDistanceGoal());
        telemetry.addData("Zone", VisionSub.INSTANCE.getDectectedZone());
        telemetry.addData("tx",VisionSub.INSTANCE.getTx());
        telemetry.addData("Has Target",VisionSub.INSTANCE.hastarget());
        telemetry.addData("Command Position", Turretsub.Instance.turret.getPosition());
        telemetry.update();

        if (VisionRedSub.INSTANCE.getDectectedZone() == VisionRedSub.DetectedZone.ZONE4) {
            FlywheelSub.INSTANCE.flywheelNear.schedule();
            HoodSub.INSTANCE.hoodZone2.schedule();
            // This is anything that is less than 80 cm
        } else if (VisionRedSub.INSTANCE.getDectectedZone() == VisionRedSub.DetectedZone.ZONE5) {
            FlywheelSub.INSTANCE.flywheelMiddle.schedule();
            HoodSub.INSTANCE.hoodZone5.schedule();
            // This is anything less than 100 cm
        } else if (VisionRedSub.INSTANCE.getDectectedZone() == VisionRedSub.DetectedZone.ZONE6) {
            FlywheelSub.INSTANCE.flywheelMiddle.schedule();
            HoodSub.INSTANCE.hoodZone3.schedule();
            // This is anything that is less than 200 cm which is equal to the end of the zone.
            // This one I need to test and make sure it is flywheel middle rather than close
        }
        else if (VisionRedSub.INSTANCE.getDectectedZone() == VisionRedSub.DetectedZone.ZONE7)
        {
            FlywheelSub.INSTANCE.flywheelFar.schedule();
            // Add Angle
            // This is the backzone (400 Cm)
        }
        else if (VisionRedSub.INSTANCE.getDectectedZone() == VisionRedSub.DetectedZone.UNKOWN)
            FlywheelSub.INSTANCE.flywheelOff.schedule();
    }
// I made these numbers bigger becuase I wanted to make sure it would not be calling the same number more than once though this may be wrong or you dont have to do this
    public void onStop(){

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
    private void Intakeoff(){
        IntakeSub.INSTANCE.inIntake.schedule();
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
    private Command alloff (){
        IntakeSub.INSTANCE.stopIntake.schedule();
        FlywheelSub.INSTANCE.flywheelOff.schedule();
        return null;
    }

//    private Command Hood1(){
//        return HoodSub.INSTANCE.hoodZone1;
//    }
//    private Command Hood2(){
//        return HoodSub.INSTANCE.hoodZone2;
//    }
//    private Command Hood3(){
//        return HoodSub.INSTANCE.hoodZone3;
//    }
//    private Command Hood5(){
//        return HoodSub.INSTANCE.hoodZone5;
//    }
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


