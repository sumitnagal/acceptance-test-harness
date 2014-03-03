package org.jenkinsci.test.acceptance.junit;

import org.jenkinsci.test.acceptance.guice.World;
import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * JUnit test {@link Runner} that uses Guice to instantiate the test class,
 * among with all the components provided by the test harness.
 *
 * @author Kohsuke Kawaguchi
 * @deprecated
 *      Use {@link JenkinsAcceptanceTestRule}
 */
public class JenkinsAcceptanceTestRunner extends BlockJUnit4ClassRunner {
    public JenkinsAcceptanceTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * {@link BlockJUnit4ClassRunner} uses this to instantiate the test class.
     * Here we do so with Guice.
     */
    @Override
    protected Object createTest() {
        World world = World.get();
        world.onNewTest();
        return world.getInjector().getInstance(getTestClass().getJavaClass());
    }
}
