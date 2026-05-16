package jp.main.taikun.justdirefixes.mixin;

import com.direwolf20.justdirethings.common.blocks.baseblocks.BaseMachineBlock;
import jp.main.taikun.justdirefixes.Justdirefixes;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.NoSuchElementException;

@Mixin(BaseMachineBlock.class)
public class BaseMachineBlockMixin {
    @Redirect(
            method = "getDrops",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/storage/loot/LootParams$Builder;getParameter(Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;)Ljava/lang/Object;"
            )
    )
    private Object getParamRedirect(LootParams.Builder instance, LootContextParam<BlockEntity> p_287646_){
        try{
            return instance.getParameter(p_287646_);
        } catch (NoSuchElementException e){
            Justdirefixes.LOGGER.warn("JDE just tried to get empty be, so fixed it.");
            return null;
        }
    }
}
