package com.mfcustomerapi.resources;

import com.mfcustomerapi.entities.request.CustomerCreateRequest;
import com.mfcustomerapi.entities.request.CustomerUpdateRequest;
import com.mfcustomerapi.entities.response.CustomerResponse;
import com.mfcustomerapi.mappers.CustomerMapper;
import com.mfcustomerapi.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerResource {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;


    @GetMapping
    @ResponseStatus(OK)
    public List<CustomerResponse> findAll() {

        return customerMapper.toResponseList(customerService.findAll());
    }


    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CustomerResponse findById(@PathVariable("id") final Long id) {

        return customerMapper.toReponse(customerService.findById(id));
    }


    @GetMapping("/search")
    @ResponseStatus(OK)
    public CustomerResponse findByEmail(@RequestParam("email") @NotNull final String email) {

        return customerMapper.toReponse(customerService.findByEmail(email));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delte(@PathVariable("id") final Long id) {

        customerService.deleteById(id);
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public URI create(@Valid @RequestBody final CustomerCreateRequest customerCreateRequest) {

        final Long id = customerService.create(customerMapper.to(customerCreateRequest)).getId();

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }


    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void update(@PathVariable("id") final Long id,
                       @Valid @RequestBody final CustomerUpdateRequest customerUpdateRequest) {

        customerService.update(customerMapper.to(id, customerUpdateRequest));

    }
}
