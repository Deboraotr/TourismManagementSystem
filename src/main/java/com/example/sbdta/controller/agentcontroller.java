package com.example.sbdta.controller;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.sbdta.model.bus;
import com.example.sbdta.model.costumer;
import com.example.sbdta.model.hotel;
import com.example.sbdta.model.travel_agent;
import com.example.sbdta.model.user;

@Controller
public class agentcontroller {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Model model;

    @GetMapping("/")
    public String index(Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE DELETED = 'N'";
        List<costumer> costumerList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(costumer.class));
        model.addAttribute("costumer", costumerList);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        String sql = "SELECT * FROM COSTUMER ";
        List<costumer> costumerList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(costumer.class));
        model.addAttribute("costumer", costumerList);
        return "admin";
    }

    @GetMapping("/detailbus")
    public String detailbus(Model model) {
        String sql = "SELECT * FROM BUS";
        List<bus> busList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(bus.class));
        model.addAttribute("bus", busList);
        return "detailbus";
    }

    @GetMapping("/detailhotel")
    public String detailhotel(Model model) {
        String sql = "SELECT * FROM HOTEL";
        List<hotel> hotelList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(hotel.class));
        model.addAttribute("hotel", hotelList);
        return "detailhotel";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getUser() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "email") user user, Model model) {
        String email = user.getEmail();
        String password = user.getPassword();
        try {
            String sql = "SELECT * FROM USER_LOGIN WHERE EMAIL = ?";
            user asli = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(user.class), email);
            model.addAttribute("asli", asli);
            String emailasli = asli.getEmail();
            String passasli = asli.getPassword();
            if (password.equals(passasli)) {
                return "redirect:/";
            }
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            model.addAttribute("invalidCredentials", true);
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/addcostumer")
    public String addcostumer(Model model) {
        return "addcostumer";
    }

    @PostMapping(value ="/addcostumer")
    public String addcostumer(costumer costumer, Model model) {

        String sql = "INSERT INTO COSTUMER VALUES (?, ?, ?, ?, ?, 'N')";
        jdbcTemplate.update(sql,
                costumer.getId_costumer(),
                costumer.getCostumer_name(),
                costumer.getContact_costumer(),
                costumer.getAge(),
                costumer.getAddress());
        return "redirect:/";

    }

    @GetMapping("/travel/{id_costumer}")
    public String guestList(@PathVariable("id_costumer") String id_costumer, Model model) {
        String sql = "SELECT * FROM travel_agent WHERE id_costumer = ?";
        List<travel_agent> travelList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(travel_agent.class), id_costumer);
        model.addAttribute("travel_agent", travelList);
        return "travel";
    }

    @GetMapping("/addtravel")
    public String addtravel(Model model) {
        return "addtravel";
    }

    @PostMapping(value ="/addtravel")
    public String addtravel(travel_agent travel, Model model) {

        String sql = "INSERT INTO TRAVEL_AGENT VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                travel.getId_agent(),
                travel.getAgent_name(),
                travel.getId_bus(),
                travel.getId_hotel(),
                travel.getAgent_contact(),
                travel.getId_costumer());

        return "redirect:/";

    }

    @GetMapping("/editcostumer/{id_costumer}")
    public String edit(@PathVariable("id_costumer") String id_costumer, Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE ID_COSTUMER = ?";
        costumer costumer = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(costumer.class), id_costumer);
        model.addAttribute("costumer", costumer);
        return "editcostumer";
    }

    @PostMapping("/editcostumer")
    public String edit(costumer costumer) {
        String sql = "UPDATE COSTUMER SET id_costumer = ?, costumer_name = ?, contact_costumer = ?, age = ?, address = ?  WHERE id_costumer = ?";
        jdbcTemplate.update(sql, costumer.getId_costumer(), costumer.getCostumer_name(), costumer.getContact_costumer(),costumer.getAge(),
                costumer.getAddress(), costumer.getId_costumer());
        return "redirect:/";
    }

    @GetMapping("/editbus/{id_bus}")
    public String editbus(@PathVariable("id_bus") String id_bus, Model model) {
        String sql = "SELECT * FROM BUS WHERE ID_BUS = ?";
        bus bus = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(bus.class), id_bus);
        model.addAttribute("Bus", bus);
        return "editbus";
    }

    @PostMapping("/editbus")
    public String editbus(bus bus) {
        String sql = "UPDATE BUS SET arrival_time = ?, departure_time = ?, price = ?, destination = ?, seats = ? WHERE id_bus = ?";
        jdbcTemplate.update(sql, bus.getArrival_time(),
                bus.getDeparture_time(), bus.getPrice(), bus.getDestination(), bus.getSeats(),
                bus.getId_bus());
        return "redirect:/admin";
    }

    @GetMapping("/edithotel/{id_hotel}")
    public String edithotel(@PathVariable("id_hotel") String id_hotel, Model model) {
        String sql = "SELECT * FROM HOTEL WHERE ID_HOTEL = ?";
        hotel hotel = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(hotel.class), id_hotel);
        model.addAttribute("Hotel", hotel);
        return "edithotel";
    }

    @GetMapping("/detailtravel/{id_agent}")
    public String detailtravel(@PathVariable("id_agent") Integer id_agent, Model model) {
        String sql = "SELECT * FROM travel_agent JOIN bus ON (travel_agent.id_bus = bus.id_bus) JOIN hotel ON (hotel.id_hotel = travel_agent.id_hotel) WHERE id_agent = ?";
        travel_agent agent = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(travel_agent.class), id_agent);
        bus bus1 = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(bus.class), id_agent);
        hotel hotel1 = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(hotel.class), id_agent);
        model.addAttribute("travel_agent", agent);
        model.addAttribute("bus", bus1);
        model.addAttribute("hotel", hotel1);
        return "detailtravel";}

    @PostMapping("/edithotel")
    public String edithotel(hotel hotel) {
        String sql = "UPDATE HOTEL SET hotel_type = ?, hotel_name = ?, price = ?  WHERE id_hotel = ?";
        jdbcTemplate.update(sql, hotel.getHotel_type(), hotel.getHotel_name(), hotel.getPrice(),
                hotel.getId_hotel());
        return "redirect:/admin";
    }

    @GetMapping("/harddelete/{id_costumer}")
    public String harddelete(@PathVariable("id_costumer") String id_costumer) {
        String sql = "DELETE COSTUMER WHERE ID_COSTUMER = ?";
        jdbcTemplate.update(sql, id_costumer);
        return "redirect:/";
    }

    @GetMapping("/trash")
    public String trash(Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE DELETED = 'Y'";
        List<costumer> deletedList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(costumer.class));
        model.addAttribute("costumer", deletedList);
        return "trash";
    }

    @GetMapping("/softdelete/{id_costumer}")
    public String softdelete(@PathVariable("id_costumer") String id_costumer) {
        String sql = "UPDATE COSTUMER SET DELETED = 'Y' WHERE ID_COSTUMER = ?";
        jdbcTemplate.update(sql, id_costumer);
        return "redirect:/";
    }

    @GetMapping("/restore/{id_costumer}")
    public String restore(@PathVariable("id_costumer") String id_costumer) {
        String sql = "UPDATE COSTUMER SET DELETED = 'N' WHERE ID_COSTUMER = ?";
        jdbcTemplate.update(sql, id_costumer);
        return "redirect:/";
    }

    @GetMapping("/indexdetail/{id_costumer}")
    public String indexdetails(@PathVariable("id_costumer") String age, Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE ID_COSTUMER=? AND DELETED='N'";
        List<costumer> detailList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(costumer.class), age);
        model.addAttribute("detaillist", detailList);
        return "indexdetail";
    }

    @GetMapping("/indexdetailadmin/{id_costumer}")
    public String indexdetailadmin(@PathVariable("id_costumer") String age, Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE ID_COSTUMER=?  AND DELETED='N'";
        List<travel_agent> detailList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(travel_agent.class), age);
        model.addAttribute("detaillist", detailList);
        return "indexdetailadmin";
    }

    @RequestMapping("/searchid")
    public String hasilsearch(@PathParam("costumer_name") String costumer_name, Model model) {
        String sql = "SELECT * FROM COSTUMER WHERE LOWER (COSTUMER_NAME) LIKE CONCAT(CONCAT ('%', ?), '%')";
        List<costumer> costumerSearch = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(costumer.class), costumer_name);
        model.addAttribute("costumer", costumerSearch);
        return ("searchid");
    }


}

