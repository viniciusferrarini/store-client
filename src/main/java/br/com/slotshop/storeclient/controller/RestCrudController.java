package br.com.slotshop.storeclient.controller;

import br.com.slotshop.server.service.CrudService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public abstract class RestCrudController<T, ID extends Serializable>  {

    protected abstract CrudService<T, ID> getService();

    @GetMapping
    public @ResponseBody
    List<T> get() {
        return getService().findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    T get(@PathVariable("id") ID id) {
        return getService().findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") ID id) {
        try {
            getService().delete(id);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/remove/{id}")
    public @ResponseBody
    ResponseEntity delete2(@PathVariable("id") ID id) {
        try {
            getService().delete(id);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity insert(@Valid @RequestBody T entidade) {
        try {
            preInsert(entidade);
            prePersist(entidade);
            getService().save(entidade);
            postInsert(entidade);
            postPersist(entidade);
            return ResponseEntity.ok(entidade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    protected T preInsert(T entidade) {
        return entidade;
    }

    protected T preUpdate(T entidade) {
        return entidade;
    }

    protected T prePersist(T entidade) {
        return entidade;
    }

    protected T postInsert(T entidade) {
        return entidade;
    }

    protected T postUpdate(T entidade) {
        return entidade;
    }

    protected T postPersist(T entidade) {
        return entidade;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity update(@Valid @RequestBody T entidade) {
        try {
            preUpdate(entidade);
            prePersist(entidade);
            getService().update(entidade);
            postUpdate(entidade);
            postPersist(entidade);
            return ResponseEntity.ok(entidade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public @ResponseBody
    Long count() {
        return getService().count();
    }

}
