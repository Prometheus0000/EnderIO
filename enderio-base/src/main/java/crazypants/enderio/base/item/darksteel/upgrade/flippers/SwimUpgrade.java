package crazypants.enderio.base.item.darksteel.upgrade.flippers;

import java.util.List;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.NNList;

import crazypants.enderio.api.upgrades.IDarkSteelItem;
import crazypants.enderio.api.upgrades.IDarkSteelUpgrade;
import crazypants.enderio.api.upgrades.IRule;
import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.config.config.DarkSteelConfig;
import crazypants.enderio.base.handler.darksteel.AbstractUpgrade;
import crazypants.enderio.base.handler.darksteel.Rules;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = EnderIO.MODID)
public class SwimUpgrade extends AbstractUpgrade {

  private static final @Nonnull String UPGRADE_NAME = "swim";

  public static final @Nonnull SwimUpgrade INSTANCE = new SwimUpgrade();

  @SubscribeEvent
  public static void registerDarkSteelUpgrades(@Nonnull RegistryEvent.Register<IDarkSteelUpgrade> event) {
    event.getRegistry().register(INSTANCE);
  }

  public SwimUpgrade() {
    super(UPGRADE_NAME, "enderio.darksteel.upgrade.swim", DarkSteelConfig.swimCost);
  }

  @Override
  @Nonnull
  public List<IRule> getRules() {
    return new NNList<>(Rules.forSlot(EntityEquipmentSlot.FEET), Rules.itemTypeTooltip(EntityEquipmentSlot.FEET));
  }

  @Override
  public void onPlayerTick(@Nonnull ItemStack stack, @Nonnull IDarkSteelItem item, @Nonnull EntityPlayer player) {
    if (player.isInWater() && !player.capabilities.isFlying) {
      player.motionX *= 1.1;
      player.motionZ *= 1.1;
    }
  }

}
