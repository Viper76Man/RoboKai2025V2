package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Auto Red Back")
public class AutoRedBack extends NextFTCOpMode {
    public AutoRedBack(){
        addComponents(
        new SubsystemComponent(IntakeSub.INSTANCE),
        new PedroComponent(Constants::createFollower),
        BulkReadComponent.INSTANCE,
        BindingsComponent.INSTANCE
        );

    }
}
