package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityUniversalElectrical;

public class PeripheralElectric extends PeripheralTileEntity implements IPeripheral
{
	GCCoreTileEntityUniversalElectrical electric;

	public PeripheralElectric ( GCCoreTileEntityUniversalElectrical electric, World world, int x, int y, int z )
	{
		super ( electric, world, x, y, z );
		
		this.electric = electric;
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"getStoredEnergy",
			"getMaxEnergy"
		} ) );
	}

	@Override
	public String getType() {
		return "spaceturtles_electric";
	}
/*
	@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"getStoredEnergy",
			"getMaxEnergy"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{
		switch ( method )
		{
			case 0:
				return new Object[] { this.electric.getEnergyStored ( ) };
			case 1:
				return new Object[] { this.electric.getMaxEnergyStored ( ) };
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "getStoredEnergy" ) )
			return new Object[] { this.electric.getEnergyStored ( ) };

		if ( method.equals ( "getMaxEnergy" ) )
			return new Object[] { this.electric.getMaxEnergyStored ( ) };

		return super.callMethod ( computer, context, method, arguments );
	}
}