package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hunter.subsystem.ColorSensorSub;

import java.util.List;

import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Color Sensor Test", group = "Coach")
public class ColorSensor extends NextFTCOpMode {
    public ColorSensor(){
        addComponents(
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onUpdate(){
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Detected Color", ColorSensorSub.INSTANCE.getDetectedColor(telemetry));
        telemetry.addData("Distance", ColorSensorSub.INSTANCE.getDistance());
        telemetry.update();
    }

}