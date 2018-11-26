package com.dieyidezui.lancet.plugin;

import com.android.build.gradle.BaseExtension;
import me.ele.lancet.plugin.LancetExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.ProjectConfigurationException;

public class LancetPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        if (project.getPlugins().findPlugin("com.android.application") == null
                && project.getPlugins().findPlugin("com.android.library") == null) {
            throw new ProjectConfigurationException("Need android application/library plugin to be applied first", null);
        }

        BaseExtension baseExtension = (BaseExtension) project.getExtensions().getByName("android");
        LancetExtension lancetExtension = project.getExtensions().create("lancet", LancetExtension.class);
        baseExtension.registerTransform(new LancetTransform());
    }
}
