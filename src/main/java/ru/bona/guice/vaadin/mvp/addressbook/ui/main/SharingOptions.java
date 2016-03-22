package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.localization.TextBundle;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import javax.inject.Inject;

/**
 * SharingOptions
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
@SuppressWarnings("serial")
public class SharingOptions extends Window {

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private TextBundle textBundle;

    private Label label;
    private Button okButton;
    private CheckBox macCheck;
    private CheckBox gmailCheck;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public void init() {
        setModal(true);
        setWidth(50.0f, Unit.PERCENTAGE);

        center();

        setCaption(textBundle.getText("sharingoptions-caption"));

        label = new Label(textBundle.getText("sharingoptions-content"));
        gmailCheck = new CheckBox(textBundle.getText("sharingoptions-gmail"));
        macCheck = new CheckBox(textBundle.getText("sharingoptions-mac"));
        okButton = new Button(textBundle.getText("ok"));
        okButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                close();
            }
        });

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(label);
        layout.addComponent(gmailCheck);
        layout.addComponent(macCheck);
        layout.addComponent(okButton);
        setContent(layout);
    }

	/*===========================================[ CLASS METHODS ]================*/

    protected void updateText() {
        setCaption(textBundle.getText("sharingoptions-caption"));
        label.setValue(textBundle.getText("sharingoptions-content"));
        gmailCheck.setCaption(textBundle.getText("sharingoptions-gmail"));
        macCheck.setCaption(textBundle.getText("sharingoptions-mac"));
        okButton.setCaption(textBundle.getText("ok"));
    }
}
