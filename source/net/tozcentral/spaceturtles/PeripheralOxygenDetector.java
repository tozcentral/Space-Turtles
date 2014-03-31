package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenDetector;

public class PeripheralOxygenDetector extends PeripheralTileEntity implements IPeripheral
{
	public GCCoreTileEntityOxygenDetector oxygenDetector;
	
	public boolean lastDetected;

	public PeripheralOxygenDetector ( GCCoreTileEntityOxygenDetector oxygenDetector, World world, int x, int y, int z )
	{
		super ( oxygenDetector, world, x, y, z );
		
		this.oxygenDetector = oxygenDetector;
		
		this.lastDetected = this.isAirDetected ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isAirDetected"
		} ) );
		
		//SpaceTurtles.logger.info ( "PeripheralOxygenDetector: " + this + " " + oxygenDetector );
	}
	
	@Override
	public void update ( )
	{
		boolean detected = isAirDetected ( );
		//SpaceTurtles.logger.info ( "this.lastDetected: " + lastDetected );
		//SpaceTurtles.logger.info ( "detected: " + detected );
		if ( this.lastDetected != detected )
		{
			this.queueEvent ( "spaceturtles_oxygen_detect", new Object[] { detected } );
			this.lastDetected = detected;
		}
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}
	
	public boolean isAirDetected ( )
	{
		return this.world.getBlockMetadata ( this.x, this.y, this.z ) == 1;//oxygenDetector.getBlockMetadata ( ) == 1; //
	}

	@Override
	public String getType() {
		return "spaceturtles_oxygen_detector";
	}

	/*@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"isAirDetected"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{	
		switch ( method )
		{
			case 0:
				return new Object[] { this.isAirDetected ( ) };
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "isAirDetected" ) )
			return new Object[] { this.isAirDetected ( ) };

		return super.callMethod ( computer, context, method, arguments );
	}
}