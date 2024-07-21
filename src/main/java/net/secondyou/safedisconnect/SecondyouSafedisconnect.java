package net.secondyou.safedisconnect;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondyouSafedisconnect implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("safedisconnect");

	@Override
	public void onInitialize() {
		LOGGER.info("SafeDisconnect mod initialized");
	}
}
