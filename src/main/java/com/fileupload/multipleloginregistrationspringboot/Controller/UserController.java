package com.fileupload.multipleloginregistrationspringboot.Controller;

import com.fileupload.multipleloginregistrationspringboot.Entity.Stocks;
import com.fileupload.multipleloginregistrationspringboot.Entity.User;
import com.fileupload.multipleloginregistrationspringboot.Entity.UserStock;
import com.fileupload.multipleloginregistrationspringboot.Form.BuyStockForm;
import com.fileupload.multipleloginregistrationspringboot.Repository.StockRepository;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserRepository;
import com.fileupload.multipleloginregistrationspringboot.Repository.UserStockRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserStockRepo userStockRepo;

    // Model attribute to fetch user details and add them to the model for every view
    @ModelAttribute
    private void userDetails(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            model.addAttribute("name", user.getFullName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("balance", user.getUserBalance());
        }
    }

    // Profile page
    @GetMapping("/profile")
    private String profile(Model model, Principal principal) {
        return "user/profile";
    }

    // Home page
    @GetMapping("/home")
    public String home() {
        return "user/home";
    }

    // View all available stocks
    @GetMapping("/viewStocks")
    public String viewStocks(Model model) {
        List<Stocks> list = stockRepository.findAll();
        model.addAttribute("stocks", list);
        return "user/viewStock";
    }

    // User's stocks available (to show their own portfolio)
    @GetMapping("/userStock")
    public String userStockAvailable(Model model) {
        List<Stocks> list = stockRepository.findAll();
        model.addAttribute("stocks", list);
        return "buy-stock";
    }


    @GetMapping("/buy-stock")
    public String buyStockPage(Model model){
        List<Stocks> list = stockRepository.findAll();
        model.addAttribute("stocks", list);
        BuyStockForm form=new BuyStockForm();
        model.addAttribute("form",form);

        return "user/buy-stock";
    }

    // Buying stock logic (stock ID from the URL)
    @PostMapping("/buy-stock")
    public String buyStock(@ModelAttribute("form2") BuyStockForm form, Principal principal) {
        // Fetch the stock based on the UniqueStockID
        Optional<Stocks> stockOptional = stockRepository.findStocksByStockUniqueId(form.getUniqueStockID());
        if (stockOptional.isPresent()) {
            Stocks stock = stockOptional.get();

            // Create a new UserStock object
            UserStock userStock = new UserStock();
            userStock.setStockUniqueId(form.getUniqueStockID());

            // Use the stock price from the database, not the form, for data integrity
            userStock.setStockPrice(stock.getStockPrice()); // Get price directly from the Stocks table
            userStock.setStockQuantity(form.getStockQuantity()); // Quantity comes from the form
            userStock.setCompanyName(stock.getCompanyName());

            // Fetch and set the logged-in user
            String email = principal.getName();
            User user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                user.setUserBalance(String.valueOf(Integer.parseInt(user.getUserBalance())+Integer.parseInt(userStock.getStockPrice())*Integer.parseInt(userStock.getStockQuantity())));
                userStock.setUser(user); // Associate the stock purchase with the user
            }

            // Save the UserStock entity to the database
            userStockRepo.save(userStock);
        }

        // Redirect to the portfolio page after purchase
        return "redirect:/user/portfolio";
    }


    @GetMapping("/portfolio")
    public String userPortFolio(Model model){
        List<UserStock> stocks=userStockRepo.findAll();
        model.addAttribute("stocks",stocks);
        return "user/portfolio";
    }

    @RequestMapping("/delete/{stock_unique_id}")
    public String deleteHandler(@PathVariable("stock_unique_id") String id,
                                HttpSession session, Principal principal) {
        // Fetch the logged-in user based on the principal
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            // Fetch the user's stock based on the unique stock ID
            Optional<UserStock> userStockOptional = userStockRepo.findByStockUniqueIdAndUser(id, user);

            if (userStockOptional.isPresent()) {
                // Delete the stock from the user's portfolio
                UserStock stock=userStockOptional.get();
                user.setUserBalance(String.valueOf(Integer.parseInt(user.getUserBalance())+Integer.parseInt(stock.getStockQuantity())*Integer.parseInt(stock.getStockPrice())));
                userRepository.save(user);
                userStockRepo.delete(userStockOptional.get());
                session.setAttribute("msg", "Stock Sold Successfully!");
            } else {
                session.setAttribute("msg", "Stock not found or doesn't belong to you.");
            }
        }

        // Redirect to the portfolio page after deletion
        return "redirect:/user/portfolio";
    }



}
