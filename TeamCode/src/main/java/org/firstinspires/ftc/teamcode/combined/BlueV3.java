package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.combined.subsystems.RampSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;


@TeleOp(name = "Blue Teleop Combined V3", group = "Coach")
public class BlueV3 extends NextFTCOpMode {
    public BlueV3() {
        addComponents(
                new SubsystemComponent(RampSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit() {
        //Init stuff here
    }

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(RampSub.INSTANCE.position3);
//.5
        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(RampSub.INSTANCE.position2);
//.65
        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(RampSub.INSTANCE.position1);
        //.35
    }
}