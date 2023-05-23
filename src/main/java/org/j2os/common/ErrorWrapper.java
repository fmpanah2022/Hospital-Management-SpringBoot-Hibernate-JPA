package org.j2os.common;

import org.j2os.common.exception.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ErrorWrapper  {
    public static void wrap(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        if (e instanceof NumberFormatException) {
            request.setAttribute("msg", "NUMBER FORMAT ERROR");
            response.sendError(700);
        } else if (e instanceof SQLException) {
            request.setAttribute("msg", "DATABASE ERROR");
            response.sendError(701);
        } else if (e instanceof NullPointerException) {
            request.setAttribute("msg", "INITIALIZE ERROR");
            response.sendError(702);
        } else if (e instanceof ValidationException) {
            request.setAttribute("msg", "VALIDATION ERROR");
            response.sendError(703);
        } else if (e instanceof RepeatedRecordException) {
            request.setAttribute("msg", "Repeated Record ERROR");
            response.sendError(704);
        } else if (e instanceof RecordDoesNotExistsException) {
            request.setAttribute("msg", "Record Does Not Exists ERROR");
            response.sendError(705);
        } else if (e instanceof CanNotRemoveRecordException) {
            request.setAttribute("msg", "Can Not Remove Record ERROR");
            response.sendError(706);
        } else if (e instanceof AccessError) {
            request.setAttribute("msg", "Access ERROR");
            response.sendError(707);
        }  else if (e instanceof InvalidUsernameOrPassword ) {
            request.setAttribute("msg", "Invalid Username Or Password ERROR");
            response.sendError(708);
        }  else  {
            request.setAttribute("msg", "CONNECT TO SUPPORT");
            response.sendError(800);
        }
    }
}
