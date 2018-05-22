package ChildCareTech.services;

public interface AlertMethodService {
    String setFirstButtonLabel();
    void firstButtonAction(AccessorWindowService accessorWindowService);
    String setSecondButtonLabel();
    void secondButtonAction(AccessorWindowService accessorWindowService);
    String setLabelText();
}
