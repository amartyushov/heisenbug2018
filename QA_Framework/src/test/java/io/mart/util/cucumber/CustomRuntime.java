package io.mart.util.cucumber;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.ResourceLoader;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Tag;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CustomRuntime extends Runtime {

    // constructor will be  called during initialisation of this
    public static CustomRuntime customRuntime;

    public CustomRuntime(ResourceLoader resourceLoader, ClassFinder classFinder, ClassLoader classLoader, RuntimeOptions runtimeOptions) {
        super(resourceLoader, classFinder, classLoader, runtimeOptions);
        customRuntime = this;
    }

    public static CustomRuntime getInstance(){
        return customRuntime;
    }

    @Override
    public void runBeforeHooks(Reporter reporter, Set<Tag> tags) {
        super.runBeforeHooks(reporter, filterTags(tags));
    }

    @Override
    public void runAfterHooks(Reporter reporter, Set<Tag> tags) {
        super.runAfterHooks(reporter, filterTags(tags));
    }

    private Set<Tag> filterTags(Set<Tag> tags) {
        Set<Tag> filtered = tags.stream()
                .filter(tag -> tag.getName().contains("override"))
                .collect(Collectors.toSet());
        return filtered.isEmpty()? tags : filtered;
    }
}

