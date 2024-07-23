package lab.solupia.samsungsvc.api;

import lab.solupia.samsungsvc.api.dept.DepartmentInterface;
import lab.solupia.samsungsvc.api.dept.constant.Target;
import lab.solupia.samsungsvc.api.duty.MasterDutyInterface;
import lab.solupia.samsungsvc.api.user.UserInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ExecuteService {
    private final CommonService commonService;
    private final DepartmentInterface departmentInterface;
    private final UserInterface userInterface;
    private final MasterDutyInterface masterDutyInterface;

    @Autowired
    public ExecuteService(CommonService commonService, DepartmentInterface departmentInterface, UserInterface userInterface, MasterDutyInterface masterDutyInterface) {
        this.commonService = commonService;
        this.departmentInterface = departmentInterface;
        this.userInterface = userInterface;
        this.masterDutyInterface = masterDutyInterface;
    }

    @Transactional(rollbackFor = Exception.class)
    public void execute() throws Exception {
        commonService.truncateData();

        departmentInterface.process(Target.PC6, ProcessMethod.INSERT);
        departmentInterface.process(Target.PC8, ProcessMethod.INSERT);
        departmentInterface.process(Target.PC9, ProcessMethod.INSERT);
//        departmentInterface.process(Target.IC5, ProcessMethod.INSERT);

        userInterface.process(Target.PC6);
        userInterface.process(Target.PC8);
        userInterface.process(Target.PC9);
//        userInterface.process(Target.IC5);
//        userInterface.process(Target.PARTNERS);

//        masterDutyInterface.process();
    }
}
