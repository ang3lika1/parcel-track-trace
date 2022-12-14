package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
@RequiredArgsConstructor
@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T13:39:57.022856Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {

    @Autowired
    private final WarehouseService warehouseService;
    private final NativeWebRequest request;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Warehouse>> exportWarehouses() {
        List<Warehouse> warehouseHierarchy;
        try {
            warehouseHierarchy = warehouseService.exportWarehouses();
        }catch (DataAccessException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<List<Warehouse>>(HttpStatus.BAD_REQUEST);
        }
        if(warehouseHierarchy == null) return new ResponseEntity<List<Warehouse>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Warehouse>>(warehouseHierarchy,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        Hop hop = null;
        try {
            hop = warehouseService.getWarehouse(code);
        } catch (SQLException e) {
            log.warn(e.getSQLState(), e.getMessage());
            return new ResponseEntity<Hop>(HttpStatus.BAD_REQUEST);
        }
        if(hop == null) return new ResponseEntity<Hop>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Hop>(hop, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        Warehouse createdWarehouse = null;
        try {
            createdWarehouse = warehouseService.importWarehouses(warehouse);
        } catch (DataAccessException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
