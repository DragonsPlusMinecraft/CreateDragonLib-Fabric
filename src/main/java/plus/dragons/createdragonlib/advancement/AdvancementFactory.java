package plus.dragons.createdragonlib.advancement;

import com.simibubi.create.foundation.advancement.AllAdvancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.PackOutput;
import plus.dragons.createdragonlib.advancement.critereon.TriggerFactory;

public class AdvancementFactory {
    private final String modid;
    private final AdvancementGen advancementGen;
    private final TriggerFactory triggerFactory = new TriggerFactory();
    private final Runnable preTask;

    private AdvancementFactory(String name, String modid, Runnable preTask) {
        this.modid = modid;
        this.advancementGen = new AdvancementGen(name, modid);
        this.preTask = preTask;
    }

    public static AdvancementFactory create(String name, String modid, Runnable preTask) {
        return new AdvancementFactory(name, modid, preTask);
    }

    public AdvancementHolder.Builder builder(String id) {
        return new AdvancementHolder.Builder(modid, id, triggerFactory);
    }

    public TriggerFactory getTriggerFactory() {
        return triggerFactory;
    }

    public void datagen(final FabricDataGenerator datagen) {
        preTask.run();
        advancementGen.generator = datagen;
        FabricDataGenerator.Pack pack = datagen.createPack();
        pack.addProvider((PackOutput output) -> advancementGen);
    }

    public void register() {
        triggerFactory.register();
    }

}
