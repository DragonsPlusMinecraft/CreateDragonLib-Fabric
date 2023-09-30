package plus.dragons.createdragonlib.mixin;

import net.minecraft.data.DataGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.nio.file.Path;

@Mixin(DataGenerator.class)
public interface DataGeneratorAccessor {
    @Accessor Path getRootOutputFolder();
}
