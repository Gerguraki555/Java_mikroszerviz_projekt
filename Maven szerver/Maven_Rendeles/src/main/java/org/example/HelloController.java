package org.example;

import org.example.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("table")
@RequestMapping("/table")
public class HelloController { //Kontroller (Hasonlóan, mint asp.net coreban)

    //Repository implementálása
    @Autowired //Ez kell a lepéldányosításhoz
    private TableRepository repoTable;
    @PostMapping("")
    public void save(@RequestBody TableEntity pdata){
        this.repoTable.save(pdata);
    }

    @GetMapping("/getall")
    public Iterable<TableEntity> getAllTable(){
        var result = repoTable.findAll();
        return result;
    }

    @PostMapping("/addnewtable")
    public ResponseEntity<?> AddNewTable(@RequestBody TableDto dto){
        if(dto.capacity<2){
            return ResponseEntity.badRequest().body("Error: capacity is less than the minimum capacity");
        }
        TableEntity table=new TableEntity();
        table.setAvailable(dto.avaliable);
        table.setName(dto.Name);
        table.setCapacity(dto.capacity);
        return ResponseEntity.ok(repoTable.save(table));
    }

    @PostMapping("/deletetable")
    public ResponseEntity<?> DeleteTable(@RequestBody long id){
        if(!repoTable.existsById(id)){
            return ResponseEntity.badRequest().body("Table does not exist with this identifier: "+id);
        }
        repoTable.deleteById(id);
        if(!repoTable.existsById(id)){
            return ResponseEntity.ok("The has been successfully deleted.");
        }
        return ResponseEntity.badRequest().body("Table deletion failed due to unknown error");

    }

    @PostMapping("/modifytable")
    public ResponseEntity<?> ModifyTable(@RequestBody ModifyTableDto dto){
        if(dto.capacity<2){
            return ResponseEntity.badRequest().body("Error: capacity is less than the minimum capacity");
        }
        Optional<TableEntity> result = repoTable.findById(dto.id);

        if(result.get()!=null){
            TableEntity table=result.get();
            table.setAvailable(dto.avaliable);
            table.setName(dto.Name);
            table.setCapacity(dto.capacity);
            return ResponseEntity.ok(repoTable.save(table));
        }
        return ResponseEntity.badRequest().body("Table not found with thisid: "+dto.id);
    }

    //@Validated(TableAvailableIsFalseGroup.class)
    @PostMapping("/enableTable")
    public TableEntity enable( @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.TRUE);
        return repoTable.save(tbl);
    }

    //@Validated(TableAvailableIsTrueGroup.class)
    @PostMapping("/disableTable")
    public TableEntity disable( @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.FALSE);
        return repoTable.save(tbl);
    }






    //Post kérés
    @PostMapping("/order")
    public  String Order(@RequestBody OrderDto order){
        return "Asztal azonosító: "+order.TableId+
                ", menu item azonosító: "+order.MenuItemId+
                ", darabszám: "+order.Count+
                ", megjegyzés: "+order.Comment;
    }





}
