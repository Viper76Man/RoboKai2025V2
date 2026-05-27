package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.combined.subsystems.LiftSub2;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Lift Test", group = "Coach")
public class LiftTest extends NextFTCOpMode {

    public LiftTest(){
        addComponents(
                new SubsystemComponent(LiftSub2.Instance),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(LiftSub2.Instance.goToPosition());
    }
}
