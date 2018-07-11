package com.accolite.furlough.dto;

import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.entity.MSEmployee;

public class EmployeeDetailsResponse {
    private AccoliteEmployee accoliteEmployee;
    private MSEmployee msEmployee;

    public AccoliteEmployee getAccoliteEmployee() {
        return accoliteEmployee;
    }

    public void setAccoliteEmployee(final AccoliteEmployee accoliteEmployee) {
        this.accoliteEmployee = accoliteEmployee;
    }

    public MSEmployee getMsEmployee() {
        return msEmployee;
    }

    public void setMsEmployee(final MSEmployee msEmployee) {
        this.msEmployee = msEmployee;
    }

    public EmployeeDetailsResponse() {
        super();
    }

    public EmployeeDetailsResponse(final AccoliteEmployee accoliteEmployee, final MSEmployee msEmployee) {
        super();
        this.accoliteEmployee = accoliteEmployee;
        this.msEmployee = msEmployee;
    }

}
