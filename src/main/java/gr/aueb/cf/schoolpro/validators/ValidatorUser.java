package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dto.UserDTO;
import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.service.IUserService;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUser {
    public static Map<String, String> checkUser(UserInsertDTO user, IUserService service) throws DAOException {
        Map<String, String> entries = new HashMap<>();

        if(user.getUsername().length() < 2) entries.put("Username", "length");
        if(service.checkExist(user.getUsername())) entries.put("Username", "already exists");

        Pattern pattern = Pattern.compile("(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])");
        Matcher matcher = pattern.matcher(user.getPassword());


        if(!matcher.find()) entries.put("Password", "Does not contain at least one capital, one small letter and a digit");
        if(user.getPassword().length() < 8) entries.put("Password", "length");

        return entries;
    }

    public static Map<String, String> checkUser(UserDTO user, IUserService service) {
        Map<String, String> entries = new HashMap<>();

        if(user.getId() < 0) entries.put("Userid", "Invalid value");

        if(user.getUsername().length() < 2) entries.put("Username", "length");


        Pattern pattern = Pattern.compile("(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])");
        Matcher matcher = pattern.matcher(user.getPassword());
        if(!matcher.find()) entries.put("Password", "Does not contain at least one capital, one small letter and a digit");
        if(user.getPassword().length() < 8) entries.put("Password", "length");

        return entries;
    }
}
