package com.company.application.domain.projectdocuments.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.domain.projectdocuments.data.ServerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;

@Service
public class EditFileUseCase {

    @Autowired
    private SecurityController securityController;

    public boolean storeFile(ServerFile serverFile, String newText) {
        try (PrintWriter writer = new PrintWriter(serverFile.getAbsolutePath())){
            writer.write(newText);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public boolean deleteFile(ServerFile selectedFile) {
        File file = new File(selectedFile.getAbsolutePath());
        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

    public boolean isAllowedToEdit() {
        Optional<Role> role = securityController.getCurrentUserRole();
        Optional<Occupation> occupation = securityController.getCurrentUserOccupation();
        return role.isPresent() && occupation.isPresent() && (role.get() == Role.ADMIN ||occupation.get() == Occupation.DEVELOPER);
    }
}
