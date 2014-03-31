package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.items.GCCoreItemOxygenTank;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenDecompressor;

public class PeripheralOxygenDecompressor extends PeripheralOxygen implements IPeripheral
{
	public GCCoreTileEntityOxygenDecompressor oxygenDecompressor;
	
	public boolean lastActive;

	public PeripheralOxygenDecompressor ( GCCoreTileEntityOxygenDecompressor oxygenDecompressor, World world, int x, int y, int z )
	{
		super ( oxygenDecompressor, world, x, y, z );
		
		this.oxygenDecompressor = oxygenDecompressor;
		
		this.lastActive = this.isActive ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isActive"
		} ) );
	}
	
	@Override
	public void update ( )
	{
		boolean active = isActive ( );
		
		if ( this.lastActive != active )
		{
			this.queueEvent ( "spaceturtles_active", new Object[] { active } );
			this.lastActive = active;
		}
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}
	
	public boolean isActive ( )
	{
		return ( ((IInventory)this.oxygenDecompressor).getStackInSlot(0) != null &&
		  ((IInventory)this.oxygenDecompressor).getStackInSlot(0).getItem ( ) instanceof GCCoreItemOxygenTank && 
		  ((IInventory)this.oxygenDecompressor).getStackInSlot(0).getItemDamage ( ) < ((IInventory)this.oxygenDecompressor).getStackInSlot(0).getMaxDamage ( ) &&
		  this.oxygenDecompressor.getEnergyStored ( ) == 0 );
		  //this.oxygenDecompressor.storedOxygen >= 1.0D
	}

	@Override
	public String getType ( )
	{
		return "spaceturtles_oxygen_compressor";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "isActive" ) )
			return new Object[] { this.isActive ( ) };

		return super.callMethod ( computer, context, method, arguments );
	}
}