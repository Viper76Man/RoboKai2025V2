package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.RGBSub2;

import java.util.List;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "RGB Test 2", group = "Coach")
public class RGBTest2 extends NextFTCOpMode {

    public RGBTest2(){
        addComponents(
                new SubsystemComponent(RGBSub2.INSTANCE),
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
                RGBSub2.INSTANCE.red,
                new Delay(5),
                RGBSub2.INSTANCE.orange,
                new Delay(5),
                RGBSub2.INSTANCE.yellow,
                new Delay(5),
                RGBSub2.INSTANCE.sage,
                new Delay(5),
                RGBSub2.INSTANCE.green,
                new Delay(5),
                RGBSub2.INSTANCE.azure,
                new Delay(5),
                RGBSub2.INSTANCE.blue,
                new Delay(5),
                RGBSub2.INSTANCE.indigo,
                new Delay(5),
                RGBSub2.INSTANCE.violet,
                new Delay(5),
                RGBSub2.INSTANCE.white,
                new Delay(5),
                RGBSub2.INSTANCE.off
        );
    }
}
