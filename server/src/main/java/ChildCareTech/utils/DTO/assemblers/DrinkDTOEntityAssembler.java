package ChildCareTech.utils.DTO.assemblers;

import ChildCareTech.common.DTO.DrinkDTO;
import ChildCareTech.model.drink.Drink;

public class DrinkDTOEntityAssembler extends AbstractDTOEntityAssembler<Drink, DrinkDTO> {
    @Override
    public Drink assembleWithoutRelations(DrinkDTO dto) {
        return null;
    }

    @Override
    public void assembleRelations(Drink entity, DrinkDTO dto) {

    }
}
