package com.alwaysontopkeybind;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.Range;

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
		keyName = "showOverlay",
		name = "On-screen notice",
		description = "Show Always On Top: On or Always On Top: Off at the top of the screen when you press the hotkey. Hold Alt to drag it."
	)
	default boolean showOverlay()
	{
		return true;
	}

	@Range(min = 1, max = 10)
	@ConfigItem(
		keyName = "overlaySeconds",
		name = "Notice seconds",
		description = "How many seconds the on-screen notice stays up."
	)
	default int overlaySeconds()
	{
		return 3;
	}

	@ConfigItem(
		keyName = "chatMessage",
		name = "Chat message",
		description = "Also show a game chat message saying whether Always on top is now on or off."
	)
	default boolean chatMessage()
	{
		return false;
	}
}
