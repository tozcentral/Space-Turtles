package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public abstract class PeripheralTileEntity extends Peripheral implements IPeripheral
{
	public TileEntity tileEntity;

	public PeripheralTileEntity ( TileEntity tileEntity, World world, int x, int y, int z )
	{
		super ( world, x, y, z );
		
		this.tileEntity = tileEntity;
		
		/*this.methodNames.addAll ( Arrays.asList ( new String[] {
		} ) );*/
	}

	@Override
	public boolean equals ( IPeripheral other )
	{
		if ( !(other instanceof PeripheralTileEntity) )
			return false;
			
		return this.tileEntity == ((PeripheralTileEntity)other).tileEntity;
	}
}