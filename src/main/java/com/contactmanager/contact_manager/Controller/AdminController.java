package com.contactmanager.contact_manager.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.contactmanager.contact_manager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.contact;
import com.contactmanager.contact_manager.service.UserService;
import com.contactmanager.contact_manager.service.ContactService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserRepository userRepository;

    // ✅ COMMON DATA
    @ModelAttribute
    public void addCommonData(Model m, Principal principal) {

        String username = principal.getName();

        User admin = userService.getAdminByUsername(username);
        List<User> users = userService.getAllActiveUsers();
        List<contact> messages = contactService.getAllMessages();

        m.addAttribute("admin", admin);
        m.addAttribute("users", users);
        m.addAttribute("contact", messages);
        m.addAttribute("Total_Users", userService.getTotalUsers());
        m.addAttribute("Total_Messages", contactService.getTotalMessages());
    }

    // ✅ PAGES
    @GetMapping("/index")
    public String dashboard() {
        return "admin/index";
    }

    @GetMapping("/user-lists")
    public String usersPage() {
        return "admin/user_management";
    }

    @GetMapping("/show-messages")
    public String messagePage() {
        return "admin/messages";
    }

    // ✅ MESSAGE APIs
    @GetMapping("/get-message")
    @ResponseBody
    public String getMessage(@RequestParam int id) {
        return contactService.getMessageById(id);
    }

    @GetMapping("/delete-message")
    @ResponseBody
    public String deleteMessage(@RequestParam int id) {
        contactService.deleteMessage(id);
        return "success";
    }

    // ✅ USER APIs
    @GetMapping("/delete-user")
    @ResponseBody
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "success";
    }

    @GetMapping("/get-user")
    @ResponseBody
    public Map<String, String> getUser(@RequestParam int id) {

        User user = userService.getUser(id);

        return Map.of(
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
    }

    @PostMapping("/update-user")
    @ResponseBody
    public String updateUser(int id, String name, String email, String role) {

        boolean updated = userService.updateUser(id, name, email, role);

        return updated ? "success" : "error";
    }

    @GetMapping("/user-list")
    public String userList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            Model model) {

        Page<User> usersPage = userService.getUsers(page, keyword);

        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "admin/user_management";
    }
    @GetMapping("/users-data")
    @ResponseBody
    public Map<String, Object> usersData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword) {

        Page<User> userPage;

        if (keyword == null || keyword.trim().isEmpty()) {
            userPage = userRepository.findAll(PageRequest.of(page, 10));
        } else {
            userPage = userRepository
                    .findByNameContainingIgnoreCase(keyword, PageRequest.of(page, 5));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("users", userPage.getContent());
        User u = new User();
        map.put("totalPages", userPage.getTotalPages());
//        System.out.println("Users returned: " + userPage.getContent().size());
//        System.out.println("Size of map"+ map.size()+"    "+map);
        return map;
    }
    @GetMapping("/messages-data")
    @ResponseBody
    public Map<String, Object> getMessages(
            @RequestParam int page,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isRead) {

        Page<contact> messagePage = contactService.getMessages(page, keyword, isRead);

        Map<String, Object> map = new HashMap<>();
        map.put("messages", messagePage.getContent());
        map.put("totalPages", messagePage.getTotalPages());

        return map;
    }

    @GetMapping("/admin-management")
    public String adminManagement() {
        return "admin/admin-management";
    }

    @PostMapping("/read-message")
    @ResponseBody
    public void markAsRead(@RequestParam int id) {
        contactService.markAsRead(id);
    }

    @GetMapping("/unread-count")
    @ResponseBody
    public long getUnreadCount() {
        return contactService.countByIsReadFalse();
    }

    @PostMapping("/change-role")
    @ResponseBody
    public void changeRole(@RequestParam int id, @RequestParam String role) {
        userService.changeRole(id, role);
    }
}