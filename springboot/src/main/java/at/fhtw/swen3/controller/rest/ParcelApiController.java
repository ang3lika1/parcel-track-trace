package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@RequiredArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T13:39:57.022856Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {

    @Autowired
    private final ParcelService parcelService;

    /*public ParcelApiController(
            @org.springframework.beans.factory.annotation.Autowired(required = true) ParcelService parcelService) {
        this.parcelService = parcelService;
    }*/

    private final NativeWebRequest request;
    // private final ParcelService parcelService;

   @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }
}
