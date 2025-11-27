package mitfahrboerse.model.presentation.exceptionhandler;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

/**
 * Factory class required by JSF to register the CustomExceptionHandler.
 * This class wraps the default JSF factory.
 *
 * @author Anton Hollube
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    /**
     * Default constructor for JSF.
     * 
     * @param wrapped The default factory to be wrapped.
     */
    public CustomExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
        super(wrapped);
    }

    /**
     * Returns an instance of the CustomExceptionHandler.
     *
     * @return The CustomExceptionHandler instance.
     */
    @Override
    public CustomExceptionHandler getExceptionHandler() {
        return null;
    }
}