package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.MenuDTO;
import ChildCareTech.model.menu.Menu;

public class MenuDTOEntityAssembler extends AbstractDTOEntityAssembler<Menu, MenuDTO> {
    @Override
    public Menu assembleWithoutRelations(MenuDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Menu entity, MenuDTO dto) {

    }
}
