package com.alwaysontopkeybind;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.HotkeyListener;

/**
 * Toggles RuneLite's own "Always on top" setting from a hotkey. It flips the same config value as the checkbox in
 * RuneLite's settings, so the window and the setting never disagree.
 */
@PluginDescriptor(
	name = "Always On Top Keybind",
	description = "Toggle RuneLite's Always on top setting with a hotkey",
	tags = {"always on top", "hotkey", "keybind", "window"}
)
public class AlwaysOnTopKeybindPlugin extends Plugin
{
	private static final String ALWAYS_ON_TOP_KEY = "gameAlwaysOnTop";

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ConfigManager configManager;

	@Inject
	private RuneLiteConfig runeLiteConfig;

	@Inject
	private AlwaysOnTopKeybindConfig config;

	private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.hotkey())
	{
		@Override
		public void hotkeyPressed()
		{
			toggle();
		}
	};

	@Provides
	AlwaysOnTopKeybindConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AlwaysOnTopKeybindConfig.class);
	}

	@Override
	protected void startUp()
	{
		hotkeyListener.setEnabledOnLoginScreen(true);
		keyManager.registerKeyListener(hotkeyListener);
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(hotkeyListener);
	}

	private void toggle()
	{
		boolean enabled = !runeLiteConfig.gameAlwaysOnTop();
		configManager.setConfiguration(RuneLiteConfig.GROUP_NAME, ALWAYS_ON_TOP_KEY, enabled);

		if (config.chatMessage())
		{
			clientThread.invokeLater(() ->
			{
				if (client.getGameState() == GameState.LOGGED_IN)
				{
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
						"Always on top: " + (enabled ? "on" : "off"), null);
				}
			});
		}
	}
}
