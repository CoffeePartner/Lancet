package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.CaptInternal;
import coffeepartner.capt.plugin.api.Plugin;
import coffeepartner.capt.plugin.api.annotations.Def;

import java.io.IOException;

@Def
public class LancetPlugin extends Plugin<CaptInternal> {
    @Override
    public void onCreate(CaptInternal capt) throws IOException, InterruptedException {

    }
}
