package net.tozcentral.spaceturtles;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleCommandResult;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import dan200.computercraft.api.turtle.TurtleVerb;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;

public class TurtleOxygenDetector implements ITurtleUpgrade
{
	public int id;
	public static Icon icon;
	public ItemStack craftingItem;
	public int ticks = 20;
	
	public TurtleOxygenDetector ( int id )
	{
		this.id = id;
	}
	
	public int getUpgradeID ( )
	{
		return this.id;
	}
	
	public String getAdjective ( )
	{
		return "Air Detector";
	}

	public TurtleUpgradeType getType ( )
	{
		return TurtleUpgradeType.Peripheral;
	}
			
	public ItemStack getCraftingItem ( )
	{
		if ( this.craftingItem == null )
			this.craftingItem = new ItemStack ( GCCoreBlocks.oxygenDetector );
		return this.craftingItem;
	}
	
	public IPeripheral createPeripheral ( ITurtleAccess turtle, TurtleSide side )
	{
		return new TurtleOxygenDetectorPeripheral ( turtle );
	}

	public TurtleCommandResult useTool ( ITurtleAccess turtle, TurtleSide side, TurtleVerb verb, int direction )
	{
		return null;
	}

	public Icon getIcon ( ITurtleAccess turtle, TurtleSide side )
	{
		return GCCoreBlocks.oxygenDetector.getIcon ( 2, 0 );
	}

    public void update ( ITurtleAccess turtle, TurtleSide side )
	{
		if ( this.ticks == 20 )
		{
			IPeripheral peripheral = turtle.getPeripheral ( side );
			World world = turtle.getWorld ( );
			ChunkCoordinates pos = turtle.getPosition ( );
			
			if ( peripheral != null && peripheral instanceof TurtleOxygenDetectorPeripheral )
			{
				((TurtleOxygenDetectorPeripheral)peripheral).setAirDetected ( OxygenUtil.isAABBInBreathableAirBlock ( world, new Vector3(pos.posX,pos.posY,pos.posZ).translate(new Vector3(-1, -1, -1)), new Vector3(pos.posX,pos.posY,pos.posZ).translate(new Vector3(2, 2, 2))) );
			}
			this.ticks = 0;
		}
		
		this.ticks++;
	}
}