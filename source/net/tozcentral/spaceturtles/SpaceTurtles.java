package net.tozcentral.spaceturtles;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Logger;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.world.WorldEvent;

import micdoodle8.mods.galacticraft.core.items.GCCoreItems;

import dan200.computercraft.api.ComputerCraftAPI;

@Mod(modid="MOD:ID", name="MOD:NAME", version="MOD:VERSION", dependencies = "required-after:ComputerCraft;required-after:GalacticraftCore;before:OpenPeripheralCore")
@NetworkMod(clientSideRequired=false, serverSideRequired=true)
public class SpaceTurtles
{
    @Instance("MOD:ID")
    public static SpaceTurtles instance;
	
	//@SidedProxy(clientSide = "net.tozcentral.spaceturtles.ClientProxy", serverSide = "net.tozcentral.spaceturtles.CommonProxy")
	//public static CommonProxy proxy;
	public static PeripheralTickHandler tickHandler = new PeripheralTickHandler ( );
	
	public static Logger logger;
	
	public static int oxygenDetectorTurtleUpgradeId;
	public static int launchPadInterfaceBlockId;
	
	public static Block launchPadInterface;
	
	public final static String ASSET_PREFIX = "MOD:ID:";
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = Logger.getLogger ( "MOD:ID" );
		logger.setParent ( FMLLog.getLogger ( ) );
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		new ConfigCategory("TurtleUpgrades");
		
		SpaceTurtles.oxygenDetectorTurtleUpgradeId = config.get("TurtleUpgrades", "oxygenDetectorId", 128).getInt(128);
		SpaceTurtles.launchPadInterfaceBlockId = config.getBlock("launchPadInterfaceId", 1234).getInt(1234);
		
		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		launchPadInterface = new BlockLaunchPadInterface ( SpaceTurtles.launchPadInterfaceBlockId, Material.ground ).setUnlocalizedName("launchPadInterface");

		//LanguageRegistry.addName ( launchPadInterface, "Launch Pad" );
			
		GameRegistry.registerBlock ( launchPadInterface, "launchPadInterface" );
		GameRegistry.registerTileEntity ( TileEntityLaunchPadInterface.class, "LaunchPadInterface" );
		
		GameRegistry.addRecipe (
			new ItemStack ( SpaceTurtles.launchPadInterface ),
			"www", "xyx", "zzz",
			'w', new ItemStack ( GCCoreItems.basicItem, 1, 9 ), 'x', new ItemStack ( Block.stone, 1 ),
			'y', new ItemStack ( Item.redstone, 1 ),            'z', new ItemStack ( GCCoreItems.basicItem, 1, 8 ) );
		
		ComputerCraftAPI.registerPeripheralProvider ( new PeripheralHandler ( ) );
		
		ComputerCraftAPI.registerTurtleUpgrade ( new TurtleOxygenDetector ( SpaceTurtles.oxygenDetectorTurtleUpgradeId ) );
		
		TickRegistry.registerTickHandler ( tickHandler, Side.SERVER );
	}
    
    @EventHandler
	public void init(FMLInitializationEvent event)
	{
    }
    
    @EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
    }
}
