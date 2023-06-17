package plus.dragons.createdragonlib.mixin;

import com.simibubi.create.infrastructure.item.BaseCreativeModeTab;
import com.simibubi.create.infrastructure.item.CreateCreativeModeTab;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import plus.dragons.createdragonlib.init.FillCreateItemGroupEvent;

@Mixin(CreateCreativeModeTab.class)
public class CreateItemGroupBaseMixin {

    @Inject(method = "fillItemList", at = @At("TAIL"))
    private void postFillCreateItemGroupEvent(NonNullList<ItemStack> items, CallbackInfo ci) {

        InteractionResult result = FillCreateItemGroupEvent
                .CallBack
                .EVENT
                .invoker()
                .interact((CreateCreativeModeTab) (Object) this, items);

        if (result == InteractionResult.FAIL) {
            ci.cancel();
        }
    }

}
