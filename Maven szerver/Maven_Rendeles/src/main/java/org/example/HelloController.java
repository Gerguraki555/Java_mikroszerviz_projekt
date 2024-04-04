package org.example;

import org.example.Models.OrderDto;
import org.example.Models.TableEntity;
import org.example.Models.TableIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //@Validated(TableAvailableIsFalseGroup.class)
    @PatchMapping("/enable")
    public TableEntity enable( @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.TRUE);
        return repoTable.save(tbl);
    }

    //@Validated(TableAvailableIsTrueGroup.class)
    @PatchMapping("/disable")
    public TableEntity disable( @RequestBody TableIdRequest pId){
        TableEntity tbl = repoTable.findById(pId.getId()).get();
        tbl.setAvailable(Boolean.FALSE);
        return repoTable.save(tbl);
    }

    @PatchMapping("/getall")
    public Iterable<TableEntity> getAllTable(){
        var result = repoTable.findAll();
        return result;
    }

    @PatchMapping("/addrandom")
    public TableEntity addRandomTable(){

        TableEntity table=new TableEntity();
        table.setName("Worn: ");
        return repoTable.save(table);
    }


    //@DeleteMapping("")
    public void delete(TableIdRequest pdata){
        this.repoTable.deleteById(pdata.getId());
    }

    @GetMapping("/hello") //A /table/hello hívja meg.
    public String HelloMethod(){
        return "Hallo Welt!";
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
