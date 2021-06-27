package com.lahirusandeepa.spd1.springbootdemoitemscrud;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class InventoryController {

    ArrayList<Item> inventory =new ArrayList<Item>(
        Arrays.asList(
            new Item(1,"Keyboard",29.99,76),
            new Item(2,"Mouse",19.99,43),
            new Item(3,"Monitor",79.99,7),
            new Item(4,"PC",749.99,2),
            new Item(5,"Headphones",19.99,14)
        )
    );

    private Item findItem(Long id){
        return inventory.stream()
            .filter(i -> id.equals(i.getId()))
            .findAny()
            .orElse(null);
    }

    @GetMapping("/items")
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable("id") Long id){
        Item item=findItem(id);

        if (item==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not Found");
        }else{
            return item;
        }
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item req){
        Long newID = inventory.get(inventory.size()-1).getId()+1;
        Item newItem =new Item(newID, req.getName(),req.getprice(),req.getCount());
        inventory.add(newItem);
        return newItem;
    }
    
    @PutMapping("/items/{id}")
    public Item updateItem(@PathVariable("id") Long id,@RequestBody Map<String, String> req){
        Item item=findItem(id);

        if(item==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not Found");
        }else{
            for(String k:req.keySet()){
                if(k.equals("price")){
                    item.setPrice(Double.parseDouble(req.get(k)));
                }else if (k.equals("count")){
                    item.setCount(Integer.parseInt(req.get(k)));
                }
            }
        }
        return item;
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") Long id){
        Item item=findItem(id);
        if(item==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }else{
            inventory.remove(item);
        }
    }
}
