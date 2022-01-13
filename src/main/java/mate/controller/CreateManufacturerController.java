package mate.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Manufacturer;
import mate.service.ManufacturerService;

@WebServlet("/manufacturers/new")
public class CreateManufacturerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private final ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Manufacturer> manufacturerList = manufacturerService.getAll();
        req.setAttribute("manufacturersList", manufacturerList);
        req.getRequestDispatcher("/WEB-INF/views/manufacturers/newmanufacturer.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String manufacturerName = req.getParameter("manufacturername");
        String manufacturerCountry = req.getParameter("manufacturercountry");
        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName(manufacturerName);
        newManufacturer.setCountry(manufacturerCountry);
        manufacturerService.create(newManufacturer);
        resp.sendRedirect("/index");
    }
}

