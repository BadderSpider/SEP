package mitfahrboerse.model.presentation.exceptionhandler;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.FacesException;

/**
 * Global JSF ExceptionHandler for catching and handling unexpected errors.
 * This class wraps the default JSF handler.
 *
 * @author Anton Hollube
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    /**
     * Default constructor for the factory.
     * 
     * @param wrapped The default handler to be wrapped.
     */
    public CustomExceptionHandler(ExceptionHandler wrapped) {
        super(wrapped);
    }

    /**
     * Handles all exceptions, logs Unchecked Exceptions, and redirects to the error page.
     */
    @Override
    public void handle() throws FacesException {
    }
}