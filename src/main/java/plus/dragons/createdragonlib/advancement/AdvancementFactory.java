package plus.dragons.createdragonlib.advancement;

import com.simibubi.create.foundation.advancement.AllAdvancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import plus.dragons.createdragonlib.advancement.critereon.TriggerFactory;

public class AdvancementFactory {
    private final String modid;
    private final TriggerFactory triggerFactory = new TriggerFactory();
    private final Runnable preTask;
    private final String name;
    private final AdvancementGen advancementGen;

    private AdvancementFactory(String name, String modid, Runnable preTask) {
        this.name = name;
        this.modid = modid;
        this.preTask = preTask;
        this.advancementGen = new AdvancementGen(name, modid);
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

    public void datagen(final Pack pack) {
        preTask.run();
        pack.addProvider((FabricDataOutput output) -> {
            advancementGen.output = output;
            return advancementGen;
        });
    }

    public void register() {
        triggerFactory.register();
    }

}
