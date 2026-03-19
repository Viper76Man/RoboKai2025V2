package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.SpindexerSub;

import java.util.List;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Spindexer Basic Test", group = "Coach")
public class SpindexerBasicTest extends NextFTCOpMode {

    String lastCommand = "None";

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
                .whenBecomesTrue(shoot());
    }


    @Override
    public void onUpdate() {
        //BindingManager.update();
        //It is possible to name actual things inside groups to see what it maybe stuck on
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Last Command", lastCommand);
        telemetry.update();
    }

    private Command shoot(){
        return new SequentialGroup(
                //servo stuff
                new Delay(.4),
                SpindexerSub.INSTANCE.toShoot(),
                new Delay(.4),
                SpindexerSub.INSTANCE.toFirstPos(),
                //must be a lambda or it will actually run on the group initializing vs when it actually runs
                new InstantCommand(() ->{
                    lastCommand = "Shoot";
                })
        );
    }
}
