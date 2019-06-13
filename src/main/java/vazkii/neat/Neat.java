package vazkii.neat;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Neat.MOD_ID, name = Neat.MOD_NAME, version = Neat.VERSION, dependencies = Neat.DEPENDENCIES, guiFactory = Neat.GUI_FACTORY, clientSideOnly = true)
public class Neat {

	public static final String MOD_ID = "neat";
	public static final String MOD_NAME = "Neat";
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION-" + BUILD;
	public static final String DEPENDENCIES = "";
	public static final String GUI_FACTORY = "vazkii.neat.GuiFactory";

	public static Map<Integer, String> prefixCache = new HashMap<Integer, String>();

	public static SimpleNetworkWrapper network;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NeatConfig.init(event.getSuggestedConfigurationFile());

		MinecraftForge.EVENT_BUS.register(new ToggleKeybind());
		MinecraftForge.EVENT_BUS.register(new HealthBarRenderer());

		network = NetworkRegistry.INSTANCE.newSimpleChannel("neat");
		network.registerMessage(NeatInfoPacket.Handler.class, NeatInfoPacket.class, 1, Side.CLIENT);
	}
}
