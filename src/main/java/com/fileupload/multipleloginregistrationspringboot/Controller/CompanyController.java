package com.fileupload.multipleloginregistrationspringboot.Controller;

import com.fileupload.multipleloginregistrationspringboot.Entity.Stocks;
import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Form.AddStockForm;
import com.fileupload.multipleloginregistrationspringboot.Repository.StockRepository;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserRepository;
import com.fileupload.multipleloginregistrationspringboot.Services.StockService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CompanyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @ModelAttribute
    private void userDetails(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            model.addAttribute("name", user.getFullName());
            model.addAttribute("email", user.getEmail());
        }
    }

    @GetMapping("/home")
    public String home() {
        return "company/home";
    }



    @GetMapping("/addStocks")
    public String addStocks(Model model) {
        AddStockForm form = new AddStockForm();
        model.addAttribute("form", form);
        return "company/addStocks";
    }

    @GetMapping("/updateStockPrice")
    public String updateStockPrice() {
        return "company/updateStocks";
    }

    // Add stock by fetching data from the form
    @RequestMapping(value = "/addStocks", method = RequestMethod.POST)
    public String addStockData(@ModelAttribute("form") @Valid AddStockForm form,
                               BindingResult bindingResult,
                               HttpSession session,
                               Model model) {

        if (bindingResult.hasErrors()) {
            // Return to the form with error messages
            return "company/addStocks";
        }

        // Create and save the stock
        Stocks stocks = new Stocks();
        stocks.setStockUniqueId(form.getStockUniqueId());
        stocks.setCompanyName(form.getName());
        stocks.setStockPrice(form.getPrice());
        stocks.setImage("/images/Default.png");

        stockRepository.save(stocks);

        session.setAttribute("msg", "Stock added successfully!");
        return "company/addStocks";
    }


    @GetMapping("/viewStocks")
    public String viewStocks(Model model) {

        List<Stocks> list=stockRepository.findAll();
        System.out.println(list);

        model.addAttribute("stocks",list);

        return "company/viewStock";
    }

    @PostMapping("/api/stocks/{uniqueStockId}")
    public void deleteStock(@PathVariable String uniqueStockId) {
        stockService.deleteStockByUniqueId(uniqueStockId);
    }


}
