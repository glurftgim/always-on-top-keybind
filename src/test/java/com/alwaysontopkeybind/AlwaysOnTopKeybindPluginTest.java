package com.alwaysontopkeybind;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

/** Launches RuneLite with the plugin loaded, for development. */
public class AlwaysOnTopKeybindPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AlwaysOnTopKeybindPlugin.class);
		RuneLite.main(args);
	}
}
