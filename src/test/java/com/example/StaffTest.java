package com.example;
import com.example.domain.Staff;
import com.example.service.Imp.StaffServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StaffTest {
    @Autowired
    private StaffServiceImp staffServiceImp;

    @Test
    public void staffTest() {
        Staff staff = staffServiceImp.getById(2115);
        System.out.println(staff);
    }
}
