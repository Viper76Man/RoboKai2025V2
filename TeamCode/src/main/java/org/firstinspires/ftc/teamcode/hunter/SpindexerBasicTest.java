package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hunter.subsystem.ColorSensorSub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Spindexer Basic Test", group = "Coach")
public class SpindexerBasicTest extends NextFTCOpMode {

    public SpindexerBasicTest() {
        addComponents(
                new SubsystemComponent(org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub.INSTANCE),
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }
    @Override
    public void onStartButtonPressed() {
        new SequentialGroup(
                SpindexerSub.INSTANCE.toFirstPos,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toSecondPOS,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toThirdPos
        ).schedule();


    }
    @Override
    public void onUpdate() {
        BindingManager.update();
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Last Command", SpindexerSub.INSTANCE.lastCommand);
        telemetry.update();
    }
}

