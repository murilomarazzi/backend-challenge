package com.invillia.acme.controller;

import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.exception.NoResultFoundException;
import com.invillia.acme.exception.ResourceNotFoundException;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.IStoreService;
import com.invillia.acme.util.GlobalResponseMessage;
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

    @PostMapping("/new")
    public ResponseEntity<StoreDTO> createPost(@RequestBody StoreDTO storeDTO) throws ParseException {
        Store store = convertToEntity(storeDTO);
        Store storeCreated = storeService.createStore(store);
        return new ResponseEntity<>(convertToDto(storeCreated), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public void updatePost(@RequestBody StoreDTO storeDTO) throws ParseException, NoResultFoundException {

        Store oldStore = storeRepository.findById(storeDTO.getId()).orElse(null);

        if (oldStore == null)
            throw new ResourceNotFoundException(storeDTO.getId(), StoreController.class.getName(), Store.class.getName());

        Store store = convertToEntity(storeDTO);
        storeService.updateStore(store);
    }

    @GetMapping("/id/{storeId}")
    public ResponseEntity<StoreDTO> findByStoreId(@PathVariable("storeId") Long storeId) {

        StoreDTO storeDTO = new StoreDTO();

        try {
            storeDTO = convertToDto(storeRepository.findById(storeId).orElse(null));

        } catch (NoResultFoundException ex) {
            storeDTO.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.info,
                    HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        }
        return new ResponseEntity<>(storeDTO, HttpStatus.OK);
    }

    @GetMapping("/name/{page}/{storeName}/{size}/{sortDir}/{sort}")
    public ResponseEntity<List<StoreDTO>> getListStoreByName(@PathVariable("page") int page,
                                                             @PathVariable("storeName") String storeName,
                                                             @PathVariable("size") int size,
                                                             @PathVariable("sortDir") String sortDir,
                                                             @PathVariable("sort") String sort) {

        List<StoreDTO> dtos = new ArrayList<>();

        try {
            List<Store> stores = storeService.getListStoreByName(storeName, page, size, sortDir, sort);

            dtos = stores.stream()
                    .map(store -> convertToDto(store))
                    .collect(Collectors.toList());

            if (page > dtos.size())
                throw new ResourceNotFoundException("No results found");


        } catch (ResourceNotFoundException ex) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.info,
                    HttpStatus.NOT_FOUND.value(), ex.getMessage()));
            dtos.add(storeDTO);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

   /* @GetMapping("/address/{storeAddress}")
    public ResponseEntity<List<StoreDTO>> findByStoreAddress(@PathVariable("storeAddress") String storeAddress) {

        StoreDTO storeDTO = new StoreDTO();
        List<StoreDTO> dtos = new ArrayList<>();

        try {

            List<Store> stores = storeRepository.findByAddressContainingAllIgnoringCase(storeAddress);

            if (CollectionUtils.isEmpty(stores)) {
                throw new NoResultFoundException("Store(s) not found");
            }

            for (Store store : stores) {
                storeDTO = convertToDto(store);

                dtos.add(storeDTO);
            }

        } catch (NoResultFoundException ex) {
            storeDTO.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.info,
                    HttpStatus.NOT_FOUND.value(), ex.getMessage()));

            dtos.add(storeDTO);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }*/

    private StoreDTO convertToDto(Store store) {
        StoreDTO dto = modelMapper.map(store, StoreDTO.class);
        dto.setMenssage(new GlobalResponseMessage(GlobalResponseMessage.Type.success,
                HttpStatus.OK.value()));
        return dto;
    }

    private Store convertToEntity(StoreDTO storeDTO) throws ParseException {
        return modelMapper.map(storeDTO, Store.class);
    }
}
