package org.jumpmind.symmetric.is.ui.views;

import javax.annotation.PostConstruct;

import org.jumpmind.symmetric.is.ui.common.AppConstants;
import org.jumpmind.symmetric.is.ui.common.ApplicationContext;
import org.jumpmind.symmetric.is.ui.common.Category;
import org.jumpmind.symmetric.is.ui.common.TabbedPanel;
import org.jumpmind.symmetric.is.ui.common.TopBarLink;
import org.jumpmind.symmetric.ui.common.UiComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@UiComponent
@Scope(value = "ui")
@TopBarLink(category = Category.DESIGN, name = "Design", id = "design", icon = FontAwesome.SHARE_ALT, menuOrder = 1, useAsDefault = true)
public class DesignView extends HorizontalLayout implements View {

    private static final long serialVersionUID = 1L;

    @Autowired
    ApplicationContext context;

    ProjectNavigator projectNavigator;

    TabbedPanel tabbedPanel;

    @PostConstruct
    protected void init() {
        setSizeFull();

        tabbedPanel = new TabbedPanel();

        HorizontalSplitPanel leftSplit = new HorizontalSplitPanel();
        leftSplit.setSizeFull();
        leftSplit.setSplitPosition(AppConstants.DEFAULT_LEFT_SPLIT, Unit.PIXELS);

        projectNavigator = new ProjectNavigator(context, tabbedPanel);

        leftSplit.setFirstComponent(projectNavigator);
        VerticalLayout container = new VerticalLayout();
        container.setSizeFull();
        container.addComponent(tabbedPanel);
        leftSplit.setSecondComponent(container);

        addComponent(leftSplit);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        projectNavigator.refresh();
    }

}
