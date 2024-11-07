package com.contactmanager.contact_manager.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
// import java.time.LocalDateTime;

import com.contactmanager.contact_manager.dao.Educational_info_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import com.contactmanager.contact_manager.dao.Personal_Info_Repo;
import com.contactmanager.contact_manager.dao.Programmin_Lan_Repo;
import com.contactmanager.contact_manager.dao.Project_repo;
import com.contactmanager.contact_manager.dao.Resume_Repo;
import com.contactmanager.contact_manager.dao.Speaking_Language_Repo;
import com.contactmanager.contact_manager.dao.UserRepository;
// import com.contactmanager.contact_manager.entities.LastLogin;
import com.contactmanager.contact_manager.entities.User;
import com.contactmanager.contact_manager.entities.Resume_info.Educaion_info;
import com.contactmanager.contact_manager.entities.Resume_info.Personal_info;
import com.contactmanager.contact_manager.entities.Resume_info.Programing_Language_info;
import com.contactmanager.contact_manager.entities.Resume_info.Project_info;
import com.contactmanager.contact_manager.entities.Resume_info.Resume;
import com.contactmanager.contact_manager.entities.Resume_info.Speaking_Languages_info;
import com.contactmanager.contact_manager.service.DatabasePDFService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.web.servlet.ModelAndView;


// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    public Resume r = new Resume();
    public String f_name;
    public String l_name;
    public List<Educaion_info> educaion_infoList = new ArrayList<>();
    public List<Project_info> project_infosList = new ArrayList<>();
    public Programing_Language_info programing_Language_infos = new Programing_Language_info();
    public List<Speaking_Languages_info> speaking_Languages_info = new ArrayList<>();
    public Personal_info p_f = new Personal_info();

    @Autowired
    UserRepository userRepository;

    @Autowired
    Educational_info_repo educational_info_repo;

    @Autowired
    Resume_Repo resume_Repo;

    @Autowired
    Personal_Info_Repo personal_Info_Repo;

    @Autowired
    Project_repo project_repo;

    @Autowired
    Programmin_Lan_Repo programmin_Lan_Repo;

    @Autowired
    Speaking_Language_Repo speaking_Language_Repo;

    @ModelAttribute
    public void addCommonData(Model m, Principal principal) {
        String username = principal.getName();
        User u = userRepository.getUserByUserName(username);
        this.r.setResume(u);
        this.userRepository.save(u);
        m.addAttribute("Resume_ID", this.r);
        // System.out.println(r.getId());
        m.addAttribute("user", u);
    }

    // @ModelAttribute
    // public void dataDemo(Model m){
    // User u = (User) m.getAttribute("user");
    // System.out.println("Object Value" + u.getId());
    // }

    // @ModelAttribute
    // public void addResumeData(Model m, Principal principal) {
    // Resume r = new Resume();
    // String username = principal.getName();
    // User u = userRepository.getUserByUserName(username);
    // r.setResume(u);
    // System.out.println("Resume id = "+r.getResume());
    // this.userRepository.save(u);
    // resume_Repo.save(r);
    // m.addAttribute("Resume_ID", r);
    // }

    @RequestMapping("/index")
    public String requestMethodName(Model m) {
        // String username = principal.getName();
        // User u = userRepository.getUserByUserName(username);

        // LastLogin LastLogin_object = new LastLogin();
        // LastLogin_object.setEmail(username);
        // LastLogin_object.setLastLogin(LocalDateTime.now());
        // LastLogin_object.setLast_login_user(u);

        // u.getDd().add(LastLogin_object);
        // this.userRepository.save(u);
        // System.out.println(LocalDateTime.now());
        // m.addAttribute("user", u);
        // m.addAttribute("mode", "");
        // System.out.println(u);

        return "/user/DashBord";
    }

    // public String DashBordPage(Model m) {
    // m.addAttribute("Title", "DashBord");
    // return "/user/DashBord";
    // }

    @GetMapping("/Fill-Details")
    public String getMethodName(Model m) {
        m.addAttribute("Title", "Fill-Details Page");
        return "/user/Fill_Details";
    }

    // ALL CONTROLLER WHO RELATED TO RESUME CREATION STARTS FROM HERE

    @GetMapping("/personal-info")
    public String personal_info(Model m) {
        try {
            m.addAttribute("personal_info_object", this.p_f);
            System.out.println("another controller = " + this.p_f);
            m.addAttribute("P_First_Name", f_name);
            m.addAttribute("P_Last_Name", l_name);
        } catch (Exception e) {
            m.addAttribute("personal_info_object", p_f);
        }
        m.addAttribute("Page_No_1", true);
        m.addAttribute("Title", "Fill Personal Info");
        return "/user/info/personal_info";
    }

    @GetMapping("/educational-info")
    public String educational_info(Model m) {
        try {
            m.addAttribute("User_education_list", educaion_infoList);
        } catch (Exception e) {
        }
        m.addAttribute("Page_No_2", true);
        m.addAttribute("educational_info_object", new Educaion_info());
        m.addAttribute("Title", "Fill Eductional Info");
        return "/user/info/Education_info";
    }

    @RequestMapping("/project-info")
    public String project_info(Model m) {
        try {
            m.addAttribute("User_project_list", project_infosList);
        } catch (Exception e) {
            // TODO: handle exception
        }
        m.addAttribute("Page_No_3", true);
        m.addAttribute("project_info_object", new Project_info());
        m.addAttribute("Title", "Fill Projecct Info");
        return "/user/info/Project_info";
    }


    @RequestMapping("/programming-info")
    public String programming_info(Model m) {
        try{
            m.addAttribute("programming_info_object", programing_Language_infos);
        }catch(Exception e){

        }
        m.addAttribute("Page_No_4", true);
        m.addAttribute("programming_info_object", new Programing_Language_info());
        m.addAttribute("Title", "Fill Programing_Language_info");
        return "/user/info/programming_info";
    }

    @RequestMapping("/language-info")
    public String language_info (Model m) {
        try {
            m.addAttribute("User_spe_lan", this.speaking_Languages_info);
        } catch (Exception e) {
            // TODO: handle exception
        }

        m.addAttribute("speaking_language_info", new Speaking_Languages_info());
        m.addAttribute("Page_No_5", true);
        return "/user/info/language_info";
    }
    
    

    @RequestMapping("/save-personal-info")
    public String requestMethodName(@ModelAttribute("personal_info_object") Personal_info personal_info, Model m,
            @RequestParam(name = "firstName", required = false) String first_name,
            @RequestParam(name = "surname", required = false) String last_name) {

        personal_info.setFull_Name(first_name + " " + last_name);
        personal_info.setE_mail(personal_info.getE_mail().toLowerCase());

        personal_info.setResume_id((Resume) m.getAttribute("Resume_ID"));
        // System.out.println(personal_info);
        this.p_f = personal_info;
        System.out.println("Value of P_F = " + p_f);

        List<Personal_info> personal_infos = List.of(personal_info);
        this.r.setPersonal_infos(personal_infos);
        // Always Save resume Repo First and then save any other repositery of
        // resume_info

        // resume_Repo.save(this.r);
        // personal_Info_Repo.save(personal_info);

        this.f_name = first_name;
        this.l_name = last_name;
        m.addAttribute("P_First_Name", first_name);
        m.addAttribute("P_Last_Name", last_name);
        m.addAttribute("personal_info_saved", true);
        m.addAttribute("personal_info_object", personal_info);
        return "/user/info/personal_info";

    }

    // @RequestMapping("/save-educational-info")
    @PostMapping("/list-educational-info")
    public String list_educational_info(@ModelAttribute("educational_info_object") Educaion_info educaionInfo,
            Model m, @RequestParam("Start_Date") String Sdate,
            @RequestParam("End_Date") String Edate) {

        // educaionInfo.setCource_Name(coString);
        educaionInfo.setEducaion_Time(Sdate + " to " + Edate);
        this.educaion_infoList.add(educaionInfo);
        this.r.setEducaion_infos(educaion_infoList);
        educaionInfo.setResume_id(r);

        // resume_Repo.save(this.r);
        // educational_info_repo.save(educaionInfo);

        m.addAttribute("User_education_list", this.educaion_infoList);
        educaionInfo = new Educaion_info();
        // educaionInfo = newEducaionInfo;
        m.addAttribute("educational_info_object", educaionInfo);
        System.out.println(educaion_infoList);
        m.addAttribute("educational_info_added_to_list", true);
        // m.addAttribute("Page_No_2", true);
        return "/user/info/Education_info";
    }

    // @RequestMapping("/get-personal-info")
    // public String get_personal_info(Model m) {
    // try {
    // m.addAttribute("personal_info_object", this.p_f);
    // System.out.println("another controller = " + this.p_f);
    // m.addAttribute("P_First_Name", f_name);
    // m.addAttribute("P_Last_Name", l_name);
    // } catch (Exception e) {
    // m.addAttribute("personal_info_object", new Personal_info());
    // }

    // // List<Personal_info> pd = new ArrayList<>();
    // // pd = this.r.getPersonal_infos();
    // // // this.p_f = (Personal_info) m.getAttribute("personal_info_object");
    // // System.out.println("Value of p_f "+pp.getCity());
    // // m.addAttribute("P_First_Name", m.getAttribute("P_First_Name"));
    // // m.addAttribute("P_Last_Name", m.getAttribute("P_Last_Name"));
    // return "/user/info/personal_info";
    // }

    @RequestMapping("/save-educational-info")
    public String list_educational_info(Model m) {
        try {
            if (!this.educaion_infoList.isEmpty()) {
                m.addAttribute("educational_info_saved", true);
                System.out.println(educaion_infoList);
            } else {
                m.addAttribute("educational_info_not_saved", true);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        // m.addAttribute("Page_No_2", true);
        return "/user/info/Education_info";
    }


    @RequestMapping("/list-project-info")
    public String Save_project_info(@ModelAttribute("project_info_object") Project_info project_info,Model m) {
        this.project_infosList.add(project_info);

        r.setProject_infos(project_infosList);
        project_info.setResume_id(r);
    
        m.addAttribute("User_project_list", this.project_infosList);
        m.addAttribute("project_info_added_to_list", true);

        return "/user/info/Project_info";
    }
    
    @RequestMapping("/save-project-info")
    public String list_project_info(Model m) {
        try {
            if (!this.project_infosList.isEmpty()) {
                m.addAttribute("project_info_saved", true);
            }
        } catch (Exception e) {
            m.addAttribute("project_info_not_saved", true);
            // TODO: handle exception
        }
        return "/user/info/Project_info";
    }
    

    @RequestMapping("/save-programming-info")
    public String requestMethodName(@ModelAttribute("programming_info_object") Programing_Language_info programing_Language_info, Model m)  {
        
        
        this.programing_Language_infos = programing_Language_info;
        System.out.println(programing_Language_infos);

        r.setPrograming_Language_infos(List.of(programing_Language_infos));
        programing_Language_info.setResume_id(r);
        // programing_Language_info = new Programing_Language_info();
        m.addAttribute("programming_info_object", this.programing_Language_infos);
        m.addAttribute("Programming_info_saved",true);
        return "/user/info/programming_info";
    }

    @RequestMapping("/languages-list-to-value")
    public String save_speakin_languages(@ModelAttribute("speaking_language_info") Speaking_Languages_info speaking_Languages,Model m,@RequestParam("Language_Level") String value) {
       
        speaking_Languages.setSpeaking_Language_Level(value);
        this.speaking_Languages_info.add(speaking_Languages);
        r.setSpeaking_Languages_infos(speaking_Languages_info);
        speaking_Languages.setResume_id(r);
        m.addAttribute("User_spe_lan", this.speaking_Languages_info);
        m.addAttribute("lan_added_to_list", true);
        return "/user/info/language_info";
    }
    
    @RequestMapping("/save-speaking-value")
    public String save_speaking_value(Model m) {
        m.addAttribute("personal_info_object", this.p_f);
        m.addAttribute("User_education_list", this.educaion_infoList);
        m.addAttribute("User_project_list", this.project_infosList);
        m.addAttribute("User_programming_list", this.programing_Language_infos);
        m.addAttribute("User_spe_lan", this.speaking_Languages_info);
        return "/user/info/check_info";
    }
   
    @RequestMapping("/save-in-database")
    public String save_in_database(Model m) {

        m.addAttribute("personal_info_object", this.p_f);
        m.addAttribute("User_education_list", this.educaion_infoList);
        m.addAttribute("User_project_list", this.project_infosList);
        m.addAttribute("User_programming_list", this.programing_Language_infos);
        m.addAttribute("User_spe_lan", this.speaking_Languages_info);

        r.setPersonal_infos(List.of(p_f));
        r.setEducaion_infos(educaion_infoList);
        r.setProject_infos(project_infosList);
        r.setPrograming_Language_infos(List.of(programing_Language_infos));
        r.setSpeaking_Languages_infos(speaking_Languages_info);
        this.resume_Repo.save(r);
        this.personal_Info_Repo.save(this.p_f);
        // this.educational_info_repo.save(educaion_infoList);
        for (Educaion_info e_d : educaion_infoList) {
            this.educational_info_repo.save(e_d);
        }
        for (Project_info Project_infor : project_infosList) {
            this.project_repo.save(Project_infor);
        }

        programmin_Lan_Repo.save(programing_Language_infos);

        for (Speaking_Languages_info speaking : speaking_Languages_info) {
            speaking_Language_Repo.save(speaking);
        }
        m.addAttribute("Database_done", true);
        // demo();
        return "/user/info/check_info";
    }
    
    // public void demo(){
    //     System.out.println(this.r);
    // }

    @GetMapping(value = "/make-resume-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        Resume resume_list =  this.r;

        ByteArrayInputStream bis = DatabasePDFService.employeePDFReport(resume_list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Resume.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
