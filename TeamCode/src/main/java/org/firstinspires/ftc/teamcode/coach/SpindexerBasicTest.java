package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.SpindexerSub;

import java.util.List;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Spindexer Basic Test", group = "Coach")
public class SpindexerBasicTest extends NextFTCOpMode {

    public SpindexerBasicTest(){
        addComponents(
                new SubsystemComponent(SpindexerSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos());

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPos());

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos());

        Gamepads.gamepad1().rightTrigger().greaterThan(.2)
                .whenBecomesTrue(SpindexerSub.INSTANCE.toShoot());
    }


    @Override
    public void onUpdate() {
        //BindingManager.update();
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Last Command", currentSnapshot);
        telemetry.update();
    }
}
