package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class Turretsub implements Subsystem {
    public static final Turretsub Instance= new Turretsub();
    private Turretsub(){}
    private static final double ServoRange = 355;
    private static final double kP = 0.6;
    private static final double MinPos = -100;
    private static final double MaxPos = 100;
    private static final double Centerpos = 0.50;
    private static final double Deadbandangle = 0.08;
    private static final double txOffset = 8;
    // 7.75
    private static final double Tx_sign = 1.0;
    public final ServoEx turret = new ServoEx("turretaxon");
    private double Commandposition = 0.0;
    @Override
    public void initialize(){
        turret.setPosition(Centerpos);
    }
    @Override
    public void periodic() {
        if (VisionSub.INSTANCE.hastarget() && Math.abs(VisionSub.INSTANCE.getTx()) > Deadbandangle) {
            double tx = VisionSub.INSTANCE.getTx();
            Commandposition += Tx_sign * kP * (tx-txOffset);
            Commandposition = Math.max(MinPos, Math.min(MaxPos, Commandposition));
            turret.setPosition(angleToPosition(Commandposition));
        }
    }
    private double angleToPosition(double angleDegrees){
        return Centerpos + (angleDegrees/ServoRange);
    }
}
