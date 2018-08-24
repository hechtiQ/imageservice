package nl.bijenkorf.imageservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ImageServiceController {


    @RequestMapping(value = {"/image/show/{predefinedType}/", "/image/show/{predefinedType}/{seo}/"}, method = RequestMethod.GET)
    public String getImage(@PathVariable String predefinedType, @PathVariable Optional<String> seo, @RequestParam("reference") String relativePath)
    {
        return "";
    }

    @RequestMapping(value = "/image/flush/{predefinedType}/")
    public String flushImage(@PathVariable String predefinedType, @RequestParam("reference") String relativePath)
    {
        return "";
    }
}