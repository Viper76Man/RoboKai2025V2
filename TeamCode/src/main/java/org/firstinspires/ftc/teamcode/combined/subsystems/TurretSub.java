package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class TurretSub implements Subsystem {
    public static final TurretSub INSTANCE = new TurretSub();

    private TurretSub() {
    }

    private static final double KP =0.05;
    private static final double Minpos =-.8;
    private static final double Maxpos =.8;
    private static final double Centerpos = -.3;
    private static final double Deadbandangle = 0.01;
    // This value I can tune to figure out how to make it not rotate to much
    private static final double Tx_sign =1.0;
    // Need to figure out what is the max and min values for the turret

    public final ServoEx turret = new ServoEx("turretaxon");
    private double Commandedposition = Centerpos;

    @Override
    public void initialize() {
        turret.setPosition(Commandedposition);
    }

    @Override
    public void periodic() {
        if (VisionSub.INSTANCE.hastarget() && Math.abs(VisionSub.INSTANCE.getTx()) > Deadbandangle)
            ;
        double tx = VisionSub.INSTANCE.getTx();
        Commandedposition += Tx_sign * KP * tx;

        Commandedposition = Math.max(Minpos, Math.min(Maxpos, Commandedposition));
        turret.setPosition(Commandedposition);
    }
}
