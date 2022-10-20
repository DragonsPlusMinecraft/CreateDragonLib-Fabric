package plus.dragons.createdragonlib.foundation.utility;

import com.simibubi.create.foundation.utility.LangBuilder;
import com.simibubi.create.foundation.utility.LangNumberFormat;
import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;


public class ModLang {

    public static LangBuilder builder(String namespace) {
        return new ModLangBuilder(namespace);
    }


    public static LangBuilder blockName(String namespace, BlockState state) {
        return builder(namespace).add(state.getBlock()
                .getName());
    }

    public static LangBuilder itemName(String namespace, ItemStack stack) {
        return builder(namespace).add(stack.getHoverName()
                .copy());
    }

    public static LangBuilder fluidName(String namespace, FluidStack stack) {
        return builder(namespace).add(stack.getDisplayName()
                .copy());
    }
    
    public static LangBuilder tooltip(String namespace, Item item, String suffix, Object... args) {
        return builder(namespace).translate(item.getDescriptionId() + ".tooltip." + suffix, args);
    }

    public static LangBuilder number(String namespace,double d) {
        return builder(namespace).text(LangNumberFormat.format(d));
    }

    public static LangBuilder translate(String namespace,String langKey, Object... args) {
        return builder(namespace).translate(langKey, args);
    }

    public static LangBuilder text(String namespace,String text) {
        return builder(namespace).text(text);
    }



}
