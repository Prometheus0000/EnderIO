package crazypants.enderio.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import crazypants.enderio.conduit.ConduitDisplayMode;

public class YetaWrenchPacketProcessor implements IMessage, IMessageHandler<YetaWrenchPacketProcessor, IMessage> {

  private int slot;
  private ConduitDisplayMode mode;

  public YetaWrenchPacketProcessor() {
  }

  public YetaWrenchPacketProcessor(int slot, ConduitDisplayMode mode) {
    this.slot = slot;
    this.mode = mode;
  }

  @Override
  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(slot);
    buffer.writeShort(mode.ordinal());
  }

  @Override
  public void fromBytes(ByteBuf buffer) {
    slot = buffer.readInt();
    mode = ConduitDisplayMode.values()[buffer.readShort()];
  }

  @Override
  public IMessage onMessage(YetaWrenchPacketProcessor message, MessageContext ctx) {
    ItemStack stack = null;
    if(message.slot > -1 && message.slot < 9) {
      stack = ctx.getServerHandler().playerEntity.inventory.getStackInSlot(message.slot);
    }
    if(stack != null) {
      ConduitDisplayMode.setDisplayMode(stack, message.mode);
    }
    return null;
  }
}
