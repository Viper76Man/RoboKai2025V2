package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.SpindexerSub;
import org.firstinspires.ftc.teamcode.coach.subsystems.WorkBench2Sub;

import java.util.List;
import java.util.concurrent.Delayed;

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

@TeleOp(name = "Spindexer Basic Test 2", group = "Coach")
public class WorkBench2Test extends NextFTCOpMode {

    String lastCommand = "None";

    public WorkBench2Test(){
        addComponents(
                new SubsystemComponent(WorkBench2Sub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(WorkBench2Sub.INSTANCE.toFirstPos());

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(WorkBench2Sub.INSTANCE.toSecondPos());

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(WorkBench2Sub.INSTANCE.toThirdPos());

        Gamepads.gamepad1().rightTrigger().greaterThan(.2)
                .whenBecomesTrue(shoot());

    }

    @Override
    public void onUpdate(){
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Spindexer Position", WorkBench2Sub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Last Command", lastCommand);
        telemetry.update();
    }

    private Command shoot(){
        return new SequentialGroup(
                //servo stuff up
                new Delay(.4),
                WorkBench2Sub.INSTANCE.toShoot(),
                //servo stuff down
                new Delay(.4),
                WorkBench2Sub.INSTANCE.toFirstPos(),
                //must be a lambda or it will actually run on the group initializing vs when it actually runs
                new InstantCommand(() ->{
                    lastCommand = "Shoot";
                })

        );
    }

}
