package net.tozcentral.spaceturtles;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public abstract class Peripheral implements IPeripheral
{
	public List<String> methodNames = new ArrayList<String>();
	public List<IComputerAccess> computers = new ArrayList<IComputerAccess>();
	
	public World world;
	public int x;
	public int y;
	public int z;

	public Peripheral ( World world, int x, int y, int z )
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void update ( )
	{
	}
	
	public boolean hasUpdate ( )
	{
		return false;
	}
	
	public void queueEvent ( String event, Object[] arguments )
	{
		List<Object> args = new ArrayList<Object>();
		args.add ( "" );
		args.addAll ( Arrays.asList ( arguments ) );
		//SpaceTurtles.logger.info ( "queueEvent: " + this + " " + computers.size ( ) );
		
		for ( IComputerAccess computer : computers )
		{
			args.set ( 0, computer.getAttachmentName ( ) );
			//SpaceTurtles.logger.info ( "computer: " + computer );
			computer.queueEvent ( event, args.toArray ( ) );
		}
	}

	@Override
	public String[] getMethodNames ( )
	{
		return methodNames.toArray ( new String[0] );
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{
		return this.callMethod ( computer, context, this.methodNames.get ( method ), arguments );
	}

    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		return null;
	}
	
	@Override
	public void attach ( IComputerAccess computer )
	{
		//SpaceTurtles.logger.info ( "attach: " + this + " " + computer );
		
		if ( this.computers.size ( ) == 0 && this.hasUpdate ( ) )
			SpaceTurtles.tickHandler.peripherals.add ( this );
		
		this.computers.add ( computer );
	}

	@Override
	public void detach ( IComputerAccess computer )
	{
		//SpaceTurtles.logger.info ( "detach: " + this + " " + computer );
		
		this.computers.remove ( computer );
		
		if ( this.computers.size ( ) == 0 && this.hasUpdate ( ) )
			SpaceTurtles.tickHandler.peripherals.remove ( this );
	}
}