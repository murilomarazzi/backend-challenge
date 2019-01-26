package com.invillia.acme.controller;

import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.exception.InvalidRequestException;
import com.invillia.acme.exception.NoResultFoundException;
import com.invillia.acme.exception.ResourceNotFoundException;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.IStoreService;
import com.invillia.acme.util.GlobalResponseMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Murilo Marazzi on 25/01/2019.
 */

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;
    private final IStoreService storeService;

    public StoreController(StoreRepository storeRepository, ModelMapper modelMapper, IStoreService storeService) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
        this.storeService = storeService;
    }

    @ApiOperation(value = "Create a new Store", response = StoreDTO.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Return the created Store and the success message",
                    response = StoreDTO.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Return a unsuccess message",
                    response = StoreDTO.class
            )

    })
    @PostMapping("/new")
    public ResponseEntity<StoreDTO> createPost(@RequestBody StoreDTO storeDTO) throws ParseException {
        Store store = convertToEntity(storeDTO);
        Store storeCreated = storeService.createStore(store);
        return new ResponseEntity<>(convertToDto(storeCreated), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a Store", response = StoreDTO.class)
    @ApiResponse(
            code = 404,
            message = "Return a unsuccess message",
            response = StoreDTO.class)
    @PutMapping("/update")
    public void updatePost(@RequestBody StoreDTO storeDTO) throws ParseException, NoResultFoundException {

        Store oldStore = storeRepository.findById(storeDTO.getId()).orElse(null);

        if (oldStore == null)
            throw new ResourceNotFoundException(storeDTO.getId(), StoreController.class.getName(), Store.class.getName());

        Store store = convertToEntity(storeDTO);
        storeService.updateStore(store);
    }

    @ApiOperation(value = "Find a Store by id", response = StoreDTO.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Return a Store and the success message",
                    response = StoreDTO.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Return a unsuccess message",
                    response = StoreDTO.class
            )

    })
    @GetMapping("/id/{storeId}")
    public ResponseEntity<StoreDTO> findByStoreId(@PathVariable("storeId") Long storeId) {

        StoreDTO storeDTO;

        try {
            storeDTO = convertToDto(storeRepository.findById(storeId).orElse(null));

        } catch (NoResultFoundException ex) {
            storeDTO = builderException(ex.getMessage());
        }
        return new ResponseEntity<>(storeDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a Store by name", response = StoreDTO.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Return a Store and the success message",
                    response = StoreDTO.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Return a unsuccess message",
                    response = StoreDTO.class
            )

    })
    @GetMapping("/name/{page}/{storeName}/{size}/{sortDir}/{sort}")
    public ResponseEntity<List<StoreDTO>> getListStoreByName(@PathVariable("page") int page,
                                                             @PathVariable("storeName") String storeName,
                                                             @PathVariable("size") int size,
                                                             @PathVariable("sortDir") String sortDir,
                                                             @PathVariable("sort") String sort) {

        List<StoreDTO> dtos = new ArrayList<>();

        try {
            List<Store> stores = storeService.getListStoreByName(storeName, page, size, sortDir, sort);

            dtos = mapToList(stores);

            if (page > dtos.size()) {
                throw new InvalidRequestException("Page invalid");
            } else if (dtos.size() == 0) {
                String message = String.format("No result was found with this name: %s", storeName);
                throw new ResourceNotFoundException(message);
            }


        } catch (ResourceNotFoundException ex) {
            dtos.add(builderException(ex.getMessage()));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a Store by address", response = StoreDTO.class)
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Return a Store and the success message",
                    response = StoreDTO.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "Return a unsuccess message",
                    response = StoreDTO.class
            )

    })
    @GetMapping("/address/{page}/{storeAddress}/{size}/{sortDir},{sort}")
    public ResponseEntity<List<StoreDTO>> getListStoreByAdress(@PathVariable("page") int page,
                                                               @PathVariable("storeAddress") String storeAddress,
                                                               @PathVariable("size") int size,
                                                               @PathVariable("sortDir") String sortDir,
                                                               @PathVariable("sort") String sort) {

        List<StoreDTO> dtos = new ArrayList<>();

        try {
            List<Store> stores = storeService.getListStoreByAddress(storeAddress, page, size, sortDir, sort);
            dtos = mapToList(stores);

            if (page > dtos.size()) {
                throw new InvalidRequestException("Page invalid");
            } else if (dtos.size() == 0) {
                String message = String.format("No result was found with this address: %s", storeAddress);
                throw new ResourceNotFoundException(message);
            }

        } catch (ResourceNotFoundException ex) {
            dtos.add(builderException(ex.getMessage()));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    private StoreDTO convertToDto(Store store) {
        StoreDTO dto = modelMapper.map(store, StoreDTO.class);
        dto.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.success,
                HttpStatus.OK.value()));
        return dto;
    }

    private Store convertToEntity(StoreDTO storeDTO) throws ParseException {
        return modelMapper.map(storeDTO, Store.class);
    }

    private List<StoreDTO> mapToList(List<Store> stores) {
        return stores.stream()
                .map(store -> convertToDto(store))
                .collect(Collectors.toList());
    }

    private StoreDTO builderException(String message) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.info,
                HttpStatus.NOT_FOUND.value(), message));

        return storeDTO;
    }
}
