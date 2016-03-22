package ru.bona.guice.vaadin.mvp.addressbook.ui.main;

import com.google.code.vaadin.localization.TextBundle;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import javax.inject.Inject;

/**
 * HelpWindow - Session scoped Windows don't currently function well so we need to use
 * Dependent (default) scope.
 *
 * @author Kontsur Alex (bona)
 * @since 04.02.13
 */
public class HelpWindow extends Window {

	/*===========================================[ STATIC VARIABLES ]=============*/

    private static final long serialVersionUID = -3102823753434627297L;

	/*===========================================[ INSTANCE VARIABLES ]===========*/

    @Inject
    private TextBundle textBundle;

    private Label label;

	/*===========================================[ CONSTRUCTORS ]=================*/

    public void init() {
        setCaption(textBundle.getText("helpwindow-caption"));
        label = new Label(textBundle.getText("helpwindow-content"), ContentMode.HTML);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(label);
        setContent(layout);
    }

	/*===========================================[ CLASS METHODS ]================*/

    protected void updateText() {
        setCaption(textBundle.getText("helpwindow-caption"));
        if (label != null) {
            label.setValue(textBundle.getText("helpwindow-content"));
        }
    }
}
