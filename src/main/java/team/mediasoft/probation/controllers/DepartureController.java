package team.mediasoft.probation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import team.mediasoft.probation.controllers.converter.Converter;
import team.mediasoft.probation.controllers.converter.CreatedResponseError;
import team.mediasoft.probation.controllers.converter.Result;
import team.mediasoft.probation.controllers.converter.Response;
import team.mediasoft.probation.services.DepartureService;
import team.mediasoft.probation.services.entity.PostalDepartureEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Resource controller.
 */
@Controller
@RequestMapping(value = "/departure")
public class DepartureController {

    /**
     * Variable-object CreatedJSONError.
     * Initialized by container
     */
    @Autowired
    @Qualifier("createdError")
    private CreatedResponseError createResponseError;

    /**
     * Variable-object service DepartureService.
     * Initialized by container
     */
    @Autowired
    @Qualifier("departureServise")
    private DepartureService departureService;

    /**
     * Variable-object Converter.
     * Initialized by container
     */
    @Autowired
    @Qualifier("converter")
    private Converter converter;

    /**
     * Variable-object Response.
     * Initialized by container
     */
    @Autowired
    @Qualifier("response")
    private Response responseObject;

    /**
     * Variable-object Result.
     * Initialized by container
     */
    @Autowired
    @Qualifier("result")
    private Result resultObject;

    /**
     * Method for handling GET-request.
     * Receives departure information
     * @param id - id departure
     * @param response - response server
     * @throws IOException - exception in case of write error response
     */
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void getInfoAboutDeparture(
            @PathVariable("id") Integer id,
            HttpServletResponse response)
            throws IOException {

        PostalDepartureEntity postalDeparture = null;

        if (id != null) {

            try {

                postalDeparture
                        = departureService.getDeparture(id);

            } catch (Exception ex) {
                responseObject = createResponseError.createError(
                        "Ошибка поиска");
            }

            if (postalDeparture != null) {

                resultObject.setStatus(true);

                responseObject.setResult(resultObject);
                responseObject.setDataResult(postalDeparture);

            } else {
                responseObject = createResponseError.createError(
                        "Отправление с данным id не найдено");
            }

        } else {

            responseObject = createResponseError.createError("Некорректный id");

        }

        response.setCharacterEncoding("Windows-1251");

        PrintWriter printWriter = response.getWriter();

        String responseString = converter.convertTo(responseObject);

        printWriter.write(responseString);
    }



}
