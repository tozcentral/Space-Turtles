package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenSealer;

public class PeripheralOxygenSealer extends PeripheralOxygen implements IPeripheral
{
	GCCoreTileEntityOxygenSealer oxygenSealer;
	World world;
	int x;
	int y;
	int z;
	
	boolean lastSealed;

	public PeripheralOxygenSealer ( GCCoreTileEntityOxygenSealer oxygenSealer, World world, int x, int y, int z )
	{
		super ( oxygenSealer, world, x, y, z );
		
		this.oxygenSealer = oxygenSealer;
		
		this.lastSealed = this.isSealed ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isSealed",
			"getEnabled",
			"setEnabled"
		} ) );
	}
	
	@Override
	public void update ( )
	{
		boolean sealed = isSealed ( );
		
		//SpaceTurtles.logger.info ( "this.lastSealed: " + lastSealed );
		//SpaceTurtles.logger.info ( "sealed: " + sealed );
		
		if ( this.lastSealed != sealed )
		{
			this.queueEvent ( "spaceturtles_sealed", new Object[] { sealed } );
			this.lastSealed = sealed;
		}
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}
	
	public boolean isSealed ( )
	{
		return this.oxygenSealer.sealed;
	}

	@Override
	public String getType() {
		return "spaceturtles_oxygen_sealer";
	}

	/*@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"isSealed",
			"getStoredOxygen",
			"getMaxOxygen",
			"getStoredEnergy",
			"getMaxEnergy",
			"getEnabled",
			"setEnabled"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{	
		switch ( method )
		{
			case 0:
				return new Object[] { this.isSealed ( ) };
			case 1:
				return new Object[] { this.oxygenSealer.getOxygenStored ( ) };
			case 2:
				return new Object[] { this.oxygenSealer.getMaxOxygenStored ( ) };
			case 3:
				return new Object[] { this.oxygenSealer.getEnergyStored ( ) };
			case 4:
				return new Object[] { this.oxygenSealer.getMaxEnergyStored ( ) };
			case 5:
				return new Object[] { !((Boolean)this.oxygenSealer.getDisabled ( 0 )) };
			case 6:
				this.oxygenSealer.setDisabled ( 0, !((Boolean)arguments[0]) );
				return null;
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		/*Object [] result;
		
		if ( ( result = super.callMethod ( computer, context, method, arguments ) ) != null )
			return result;*/

		if ( method.equals ( "isSealed" ) )
			return new Object[] { this.isSealed ( ) };
/*
		if ( method.equals ( "getStoredOxygen" ) )
			return new Object[] { this.oxygenSealer.getOxygenStored ( ) };

		if ( method.equals ( "getMaxOxygen" ) )
			return new Object[] { this.oxygenSealer.getMaxOxygenStored ( ) };

		if ( method.equals ( "getStoredEnergy" ) )
			return new Object[] { this.oxygenSealer.getEnergyStored ( ) };

		if ( method.equals ( "getMaxEnergy" ) )
			return new Object[] { this.oxygenSealer.getMaxEnergyStored ( ) };*/

		if ( method.equals ( "getEnabled" ) )
			return new Object[] { !((Boolean)this.oxygenSealer.getDisabled ( 0 )) };

		if ( method.equals ( "setEnabled" ) )
		{
			this.oxygenSealer.setDisabled ( 0, !((Boolean)arguments[0]) );
			return new Object[0];
		}

		return super.callMethod ( computer, context, method, arguments );
	}
}