package team.mediasoft.probation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.mediasoft.probation.controllers.converter.Converter;
import team.mediasoft.probation.controllers.converter.CreatedResponseError;
import team.mediasoft.probation.controllers.converter.Response;
import team.mediasoft.probation.controllers.converter.Result;
import team.mediasoft.probation.controllers.converter.requests.AdditionNewDepartureRequest;
import team.mediasoft.probation.postalserver.postaldeparture.TypePostalDeparture;
import team.mediasoft.probation.services.DepartureService;
import team.mediasoft.probation.services.entity.PostalDepartureEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * Resource controller.
 */
@Controller
@RequestMapping(value = "/registrationdeparture")
public class RegistrationNewDepartureController {

    /**
     * Variable-object CreatedJSONError.
     * Initialized by container
     */
    @Autowired
    @Qualifier("createdError")
    private CreatedResponseError createResponseError;

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
     * Variable-object service DepartureService.
     * Initialized by container
     */
    @Autowired
    @Qualifier("departureServise")
    private DepartureService departureService;

    /**
     * Method for handling POST-request.
     * Registers new departure.
     * @param request - request
     * @param response - response server
     * @throws IOException - exception in case of write error response
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrationNewDeparture(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String data = request.getReader().lines().collect(Collectors.joining());

        AdditionNewDepartureRequest requestAddNewDeparture
                = (AdditionNewDepartureRequest) converter.convertToJavaObject(
                        data, AdditionNewDepartureRequest.class);

        try {

            TypePostalDeparture.valueOf(requestAddNewDeparture.getType());

            PostalDepartureEntity postalDeparture
                    = departureService.addNewDeparture(
                    requestAddNewDeparture.getType(),
                    requestAddNewDeparture.getIndex(),
                    requestAddNewDeparture.getName(),
                    requestAddNewDeparture.getAddress(),
                    requestAddNewDeparture.getIndexPostalDepartment());

            if (postalDeparture != null) {

                resultObject.setStatus(true);
                responseObject.setResult(resultObject);

                responseObject.setDataResult(postalDeparture);

            } else {
                responseObject
                        = createResponseError.createError(
                                "Неверные данные");
            }


        } catch (IllegalArgumentException e) {

            responseObject = createResponseError.createError(
                    "Некорректный тип. Можно обьявить: "
                            + "letter, parcel, postcard, wrapper");

        }

        response.setCharacterEncoding("Windows-1251");

        PrintWriter printWriter = response.getWriter();

        String responseString = converter.convertTo(responseObject);

        printWriter.write(responseString);
    }

}
