package crazypants.enderio.base.item.darksteel.upgrade.sound;

import java.util.List;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.NNList;

import crazypants.enderio.api.upgrades.IDarkSteelUpgrade;
import crazypants.enderio.api.upgrades.IRule;
import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.config.config.DarkSteelConfig;
import crazypants.enderio.base.handler.darksteel.AbstractUpgrade;
import crazypants.enderio.base.handler.darksteel.Rules;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = EnderIO.MODID)
public class SoundDetectorUpgrade extends AbstractUpgrade {

  private static final @Nonnull String UPGRADE_NAME = "soundDetector";

  public static final @Nonnull SoundDetectorUpgrade INSTANCE = new SoundDetectorUpgrade();

  @SubscribeEvent
  public static void registerDarkSteelUpgrades(@Nonnull RegistryEvent.Register<IDarkSteelUpgrade> event) {
    event.getRegistry().register(INSTANCE);
  }

  public SoundDetectorUpgrade() {
    super(UPGRADE_NAME, "enderio.darksteel.upgrade.sound", DarkSteelConfig.soundLocatorCost);
  }

  @Override
  @Nonnull
  public List<IRule> getRules() {
    return new NNList<>(Rules.forSlot(EntityEquipmentSlot.HEAD), Rules.itemTypeTooltip(EntityEquipmentSlot.HEAD));
  }

}
