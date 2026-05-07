package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;

import java.util.List;

import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Combined Spindexer Test", group = "Coach")
public class SpindexerTest extends NextFTCOpMode {

    public SpindexerTest() {
        addComponents(
                new SubsystemComponent(SpindexerSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );

    }

    @Override
    public void onInit(){
    }

    public void onStartButtonPressed() {
        telemetry.addLine("Running");

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);

    }

    public void onUpdate(){
            List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
            telemetry.addData("Running Commands", currentSnapshot);
            telemetry.update();
        }

    }
