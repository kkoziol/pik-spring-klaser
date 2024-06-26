package pl.pik.kk.web.controlers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pik.kk.entities.Moneta;
import pl.pik.kk.entities.Status;
import pl.pik.kk.repositories.MonetaAlreadyExistsException;
import pl.pik.kk.repositories.MonetyRepository;
import pl.pik.kk.repositories.NoSuchMonetaException;


@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    MonetyRepository baza;
    
    @RequestMapping(path = "/wprawki-th", method = RequestMethod.GET)
    public String wprawki(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th/{cos}")
    public String wprawki(@PathVariable String cos, ModelMap model) {
        model.addAttribute("cos", cos);
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        return "th/wprawki";
    }

    @GetMapping("/wprawki-th2")
    @ResponseBody
    public String wprawkiParam(@RequestParam("cos") String cosParam, ModelMap model) {
        return "Wprawki z param cos=" + cosParam;
    }
    
    @GetMapping("/wprawki-th3")
    @ResponseBody
    public String wprawkiHeader(@RequestHeader("User-Agent") String cosParam, ModelMap model) {
        return "Uzywasz przegladarki:=" + cosParam;
    }
    
    @GetMapping(value = "/wprawki-th/monety/{id}/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Moneta> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Moneta m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Moneta>(m, HttpStatus.OK);
            
        } catch (NoSuchMonetaException e) {
            System.out.println(e.getClass().getName());
            m = new Moneta();
            m.setNumerKatalogowy(id);
            m.setKrajPochodzenia("Polska");
            m.setStatus(Status.NOWA);
            m.setNominal(10L);
            try {
                baza.create(m);
            } catch (MonetaAlreadyExistsException e1) {
                System.out.println(e1.getClass().getName());
            }
            return new ResponseEntity<Moneta>(m, HttpStatus.CREATED);
        }
    }

}
