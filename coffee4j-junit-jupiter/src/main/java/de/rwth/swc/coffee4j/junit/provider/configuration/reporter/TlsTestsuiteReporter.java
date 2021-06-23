package de.rwth.swc.coffee4j.junit.provider.configuration.reporter;

import de.rub.nds.tlstest.framework.execution.AnnotatedStateContainer;
import de.rwth.swc.coffee4j.model.Combination;
import de.rwth.swc.coffee4j.model.TestInputGroupContext;
import de.rwth.swc.coffee4j.model.report.ExecutionReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

//Implements coffee4j's ExecutionReporter
public class TlsTestsuiteReporter implements ExecutionReporter {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ExtensionContext extensionContext;
    
    public TlsTestsuiteReporter(ExtensionContext context) {
        extensionContext = context;
    }
    
    @Override
    public void testInputGroupGenerated(TestInputGroupContext context, List<Combination> testInputs) {
        LOGGER.trace("Test Inputs generated for " + extensionContext.getRequiredTestMethod().getName());
    }
    
    @Override
    public void faultCharacterizationFinished(TestInputGroupContext context, List<Combination> failureInducingCombinations) {
        AnnotatedStateContainer.forExtensionContext(extensionContext).setFailureInducingCombinations(failureInducingCombinations);
    }

    @Override
    public void testInputGroupFinished(TestInputGroupContext context) {
        AnnotatedStateContainer.forExtensionContext(extensionContext).finished();
    }
}
