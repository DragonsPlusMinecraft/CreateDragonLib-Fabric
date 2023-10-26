package plus.dragons.createdragonlib.advancement;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.PathProvider;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@ApiStatus.Internal
class AdvancementGen implements DataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String name;
    private final String modid;
    FabricDataOutput output;

    AdvancementGen(String name, String modid) {
        this.name = name;
        this.modid = modid;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        PathProvider pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "advancements");

        return CompletableFuture.runAsync(() -> {
            Set<ResourceLocation> set = Sets.newHashSet();
            Consumer<Advancement> consumer = advancement -> {
                var id = advancement.getId();
                if (!set.add(id))
                    throw new IllegalStateException("Duplicate advancement " + advancement.getId());
                Path advancementPath = pathProvider.json(id);
                DataProvider.saveStable(cache, advancement.deconstruct().serializeToJson(), advancementPath);
            };
            var advancements = AdvancementHolder.ENTRIES_MAP.get(modid);
            if (advancements != null)
                for (var advancement : advancements) {
                    advancement.save(consumer);
                }
        });
    }

    @Override
    public String getName() {
        return name + " Advancements";
    }
}
