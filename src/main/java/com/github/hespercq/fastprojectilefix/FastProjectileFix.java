package com.github.hespercq.fastprojectilefix;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FastProjectileFix.MODID)
public class FastProjectileFix
{
    public static final String MODID = "fastprojectilefix";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FastProjectileFix(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
