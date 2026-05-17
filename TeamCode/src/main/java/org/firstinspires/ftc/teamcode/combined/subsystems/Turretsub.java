package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class Turretsub implements Subsystem {
    public static final Turretsub Instance= new Turretsub();
    private Turretsub(){}
    private static final double KP = 0.005;
    private static final double MinPos = 0.1;
    private static final double Maxpos = 0.9;
    private static final double Centerpos = 0.5;
    private static final double Deadbandangle = 0.5;
    private static final double Tx_sign = -1.0;
    public final ServoEx turret = new ServoEx("turretaxon");
    private double Commandposition = Centerpos;
    @Override
    public void initialize(){turret.setPosition(Commandposition);}
    @Override
    public void periodic(){
        if(VisionSub.INSTANCE.hastarget() && Math.abs(VisionSub.INSTANCE.getTx()) > Deadbandangle) {
            double tx = VisionSub.INSTANCE.getTx();
            Commandposition += Tx_sign * KP * tx;
        }
        Commandposition = Math.max(MinPos,Math.min(Maxpos, Commandposition));
        turret.setPosition(Commandposition);
    }







}
