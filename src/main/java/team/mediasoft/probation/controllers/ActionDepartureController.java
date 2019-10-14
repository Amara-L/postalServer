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
import team.mediasoft.probation.controllers.converter.requests.RegistrationActionRequest;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.services.ActionDepartureService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * Resource controller.
 */
@Controller
@RequestMapping(value = "/actiondeparture")
public class ActionDepartureController {

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
     * Variable-object service ActionDepartureService.
     * Initialized by container
     */
    @Autowired
    @Qualifier("departureService")
    private ActionDepartureService departureService;

    /**
     * Method for handling POST-request.
     * Registers an action on a departure.
     * @param request - request
     * @param response - response server
     * @throws IOException - exception in case of write error response
     */
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public void registrationActionOnDeparture(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String data
                = request.getReader().lines().collect(Collectors.joining());

        RegistrationActionRequest requestAddAction =
                (RegistrationActionRequest) converter.convertToJavaObject(
                        data, RegistrationActionRequest.class);

        boolean result = false;

        try {

            if ((!requestAddAction.getIdPostalDeparture().equals(""))
            && (!requestAddAction.getIndexPostalDepartment().equals(""))) {

                result = departureService.registrationActionDeparture(
                        Action.valueOf(requestAddAction.getAction()),
                        requestAddAction.getIndexPostalDepartment(),
                        requestAddAction.getIdPostalDeparture());

            } else {
                responseObject = createResponseError.createError(
                        "Получен запрос с пустыми данными");
            }

        } catch (Exception e) {

            responseObject = createResponseError.createError(
                    "Некорректное действие. Можно зарегистрировать: "
                            + "registrationArrival, registrationDispatch "
                            + "или receivedByTheAddressee");

        }

        if (result) {

            resultObject.setStatus(true);

            responseObject.setResult(resultObject);

        } else if (responseObject == null) {

            responseObject = createResponseError.createError(
                    "Ошибка регистрации действия");

        }

        response.setCharacterEncoding("Windows-1251");

        PrintWriter printWriter = response.getWriter();

        String responseString = converter.convertTo(responseObject);

        printWriter.write(responseString);

    }


}
