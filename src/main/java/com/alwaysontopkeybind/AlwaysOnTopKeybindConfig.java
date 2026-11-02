package com.alwaysontopkeybind;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;

@ConfigGroup("alwaysontopkeybind")
public interface AlwaysOnTopKeybindConfig extends Config
{
	@ConfigItem(
		keyName = "hotkey",
		name = "Toggle hotkey",
		description = "Pressing this toggles RuneLite's Always on top setting. Works while the RuneLite window is focused."
	)
	default Keybind hotkey()
	{
		return Keybind.NOT_SET;
	}

	@ConfigItem(
		keyName = "chatMessage",
		name = "Chat message",
		description = "Show a game chat message saying whether Always on top is now on or off."
	)
	default boolean chatMessage()
	{
		return true;
	}
}
