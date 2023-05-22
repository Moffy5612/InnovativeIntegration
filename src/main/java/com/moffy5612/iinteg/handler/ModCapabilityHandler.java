package com.moffy5612.iinteg.handler;

import java.util.HashSet;
import java.util.Set;

import com.moffy5612.iinteg.capability.ModCapabilityBase;
import com.moffy5612.iinteg.capability.item.CapabilityCrystalBall;
import com.moffy5612.iinteg.misc.annotation.IIntegContentInstance;

public class ModCapabilityHandler {
    public static final Set<ModCapabilityBase> REG_CAPABILITIES = new HashSet<ModCapabilityBase>();

    @IIntegContentInstance(contentClass = CapabilityCrystalBall.class) public static CapabilityCrystalBall CAPABILITY_CRYSTAL_BALL;
}
