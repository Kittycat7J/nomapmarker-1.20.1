package net.kittycat7j;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfig {
    public boolean mapRender = true;
    public String exampleString = "Hello, NMMR!";

    public Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("NMMR Config"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(Text.of("maprender"), mapRender)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> {
                    mapRender = newValue;
                    save(); // Save the configuration immediately
                })
                .build());

        general.addEntry(entryBuilder.startStrField(Text.of("type whatever"), exampleString)
                .setDefaultValue("Kittycat7J was here!")
                .setSaveConsumer(newValue -> exampleString = newValue)
                .build());

        return builder.build();
    }

    public void save() {
        // Implement saving logic here, e.g., writing to a file or updating a config manager
        Nmmr.config = this;
    }
}