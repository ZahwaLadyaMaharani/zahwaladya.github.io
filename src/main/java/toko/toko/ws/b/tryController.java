/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package toko.toko.ws.b;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zahwa Ladya Maharani_060
 */
@RestController
@CrossOrigin

public class tryController {
    
    Barang mydata = new Barang();
    BarangJpaController ctrl = new BarangJpaController();
    
    @GetMapping("/{id}")
    public String getNameById(@PathVariable("id") int id){
        try{
            mydata = ctrl.findBarang(id);
        } catch (Exception e){
            
        }
        return mydata.getNama();
    }
    
    @RequestMapping(value = "/GET",
            method = RequestMethod.GET,
            consumes = APPLICATION_JSON_VALUE)
    public List<Barang> getAll(){
        List<Barang> dummy = new ArrayList();
        try{
            dummy = ctrl.findBarangEntities();
        } catch (Exception e){
            dummy = List.of();
        }
        return dummy;
    }
    
    @RequestMapping(value = "/POST",
            method = RequestMethod.POST,
            consumes = APPLICATION_JSON_VALUE)
    public String createData(HttpEntity<String> paket){
        String message ="Data Saved";
        
        try{
            String json_receive  = paket.getBody();
                        
            ObjectMapper map = new ObjectMapper();
                    
            Barang data = new Barang();
            
            data = map.readValue( json_receive, Barang.class);
            
            ctrl.create(data);
            message = data.getNama() + "Data Saved";
            
        }catch(Exception e) {message="Failed";}
        return message;
    }
    
    @RequestMapping(value = "/PUT",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();
            
            Barang newbrg = new Barang();
            
            newbrg = mapper.readValue(json_receive, Barang.class);
            
            ctrl.edit(newbrg);
            message = newbrg.getNama() + "Data Edit";
        } catch (Exception e) {message="Failed";}
        return message;
    }
    
    @RequestMapping(value = "/DELETE",
            method = RequestMethod.DELETE,
            consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();
            
            Barang newbrgs = new Barang();
            
            newbrgs = mapper.readValue(json_receive, Barang.class);
            
            ctrl.destroy(newbrgs.getId());
            message = newbrgs.getNama() + "has been updated";
        } catch (Exception e) {
        }
        return message;
    }
}