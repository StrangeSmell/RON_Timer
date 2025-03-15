package com.strangesmell.rontimer;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RONTimer.MODID)
public class RONTimer
{
    public static float timer = 1;
    public static float livingEntityTimer = 1;
    public static final String MODID = "rontimer";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static Random random=new Random();
    public RONTimer(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.register(this);


    }
    @SubscribeEvent
    public void onServerStaring(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        RONTimerCommand.register(dispatcher);
    }

}
