package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.RGBSub;

import java.util.List;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "RGB Test", group = "Coach")
public class RGBTest extends NextFTCOpMode {

    public RGBTest(){
        addComponents(
                new SubsystemComponent(RGBSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");
        changeColors().schedule();

    }

    @Override
    public void onUpdate(){
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.update();
    }

    private Command changeColors(){
        return new SequentialGroup(
                RGBSub.INSTANCE.red,
                new Delay(5),
                RGBSub.INSTANCE.orange,
                new Delay(5),
                RGBSub.INSTANCE.yellow,
                new Delay(5),
                RGBSub.INSTANCE.sage,
                new Delay(5),
                RGBSub.INSTANCE.green,
                new Delay(5),
                RGBSub.INSTANCE.azure,
                new Delay(5),
                RGBSub.INSTANCE.blue,
                new Delay(5),
                RGBSub.INSTANCE.indigo,
                new Delay(5),
                RGBSub.INSTANCE.violet,
                new Delay(5),
                RGBSub.INSTANCE.white,
                new Delay(5),
                RGBSub.INSTANCE.off
        );
    }
}
