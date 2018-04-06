package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.WorkDayDTO;
import ChildCareTech.model.workday.WorkDay;

public class WorkDayDTOEntityAssembler extends AbstractDTOEntityAssembler<WorkDay, WorkDayDTO> {
    @Override
    public WorkDay assembleWithoutRelations(WorkDayDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(WorkDay entity, WorkDayDTO dto) {

    }
}
