package com.ryouhei.Voice;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod("voice_attenuation")
public class Main {
public static final String MOD_ID = "voice_attenuation";
public static final Logger LOGGER = LogUtils.getLogger();

public Main() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    MinecraftForge.EVENT_BUS.register(this);

}




}