package mitfahrboerse.model.presentation.util;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;

//import static mitfahrboerse.model.business.service.ProfileService.getProfilePicture;
import static mitfahrboerse.model.business.service.SettingsService.getLogo;

import mitfahrboerse.global.dto.ImageDTO;


/**
 * Servlet that delivers images (logos, profile pictures) from the database.
 * Mapped to the /imagehandler URL.
 *
 * @author Anton Hollube
 */
@WebServlet("/imagehandler")
public class ImageHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;


    /**
     * Handles the HTTP GET request to retrieve an image.
     * It identifies which image to send based on request parameters.
     *
     * Required Parameters:
     * - "id=logo" : Retrieves the application logo.
     * - "userId=[number]" : Retrieves the profile picture for the given user ID.
     *
     * @param req The HTTP request (containing the image ID parameter).
     * @param resp The HTTP response (where the image data will be written).
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logoIdParam = req.getParameter("id");
        String userIdParam = req.getParameter("userId");
    }
}
