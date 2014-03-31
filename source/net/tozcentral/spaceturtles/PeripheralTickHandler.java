package net.tozcentral.spaceturtles;

import java.util.EnumSet;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PeripheralTickHandler implements ITickHandler
{
	public List<Peripheral> peripherals = new ArrayList<Peripheral>();
	public int lastRun = 0;
	
    public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}
    
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		lastRun++;
		if ( lastRun == 10 )
		{
			//SpaceTurtles.logger.info ( "Updates: " + this.peripherals.size ( ) );
			lastRun = 0;
			
			for ( int i = 0; i < peripherals.size ( ); i++ )
			{
				Peripheral peripheral = peripherals.get(i);
				
				if ( peripheral != null )
					peripheral.update ( );
			}
		}
	}
    
    public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.SERVER);//new EnumSet<TickType> ( TickType.WORLD );
	}
    
    public String getLabel()
	{
		return "Space Turtles";
	}
}
