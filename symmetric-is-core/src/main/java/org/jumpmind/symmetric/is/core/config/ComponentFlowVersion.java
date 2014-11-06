package org.jumpmind.symmetric.is.core.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jumpmind.symmetric.is.core.config.data.ComponentFlowVersionData;

public class ComponentFlowVersion extends AbstractObject<ComponentFlowVersionData> {

    private static final long serialVersionUID = 1L;

    ComponentFlow componentFlow;

    List<ComponentFlowNode> componentFlowNodes;

    List<ComponentFlowNodeLink> componentFlowNodeLinks;

    public ComponentFlowVersion(ComponentFlow flow, ComponentFlowVersionData data) {
        super(data);
        this.componentFlow = flow;
        this.componentFlowNodes = new ArrayList<ComponentFlowNode>();
        this.componentFlowNodeLinks = new ArrayList<ComponentFlowNodeLink>();
    }

    public ComponentFlowNode findComponentFlowNodeWithId(String id) {
        for (ComponentFlowNode componentFlowNode : componentFlowNodes) {
            if (componentFlowNode.getData().getId().equals(id)) {
                return componentFlowNode;
            }
        }
        return null;
    }
    
    public String getName() {
        return componentFlow.getData().getName();
    }
    
    public String getVersion() {
        return data.getVersionName();
    }
    
    public String getFolderName() {
        return componentFlow.getFolder().getData().getName();
    }

    public List<ComponentFlowNode> getComponentFlowNodes() {
        return componentFlowNodes;
    }

    public ComponentFlow getComponentFlow() {
        return componentFlow;
    }
    
    public void setComponentFlow(ComponentFlow componentFlow) {
        this.componentFlow = componentFlow;
    }

    public List<ComponentFlowNodeLink> getComponentFlowNodeLinks() {
        return componentFlowNodeLinks;
    }

    
    public ComponentFlowNode removeComponentFlowNode(ComponentFlowNode flowNode) {
        Iterator<ComponentFlowNode> i = componentFlowNodes.iterator();
        while (i.hasNext()) {
            ComponentFlowNode node = i.next();
            if (node.getData().getId().equals(flowNode.getData().getId())) {
                i.remove();
                return node;
            }
        }
        return null;
    }

    public List<ComponentFlowNodeLink> removeComponentFlowNodeLinks(String flowNodeId) {
        List<ComponentFlowNodeLink> links = new ArrayList<ComponentFlowNodeLink>();
        Iterator<ComponentFlowNodeLink> i = componentFlowNodeLinks.iterator();
        while (i.hasNext()) {
            ComponentFlowNodeLink link = i.next();
            if (link.getData().getSourceNodeId().equals(flowNodeId)
                    || link.getData().getTargetNodeId().equals(flowNodeId)) {
                i.remove();
                links.add(link);
            }
        }
        return links;
    }

    public ComponentFlowNodeLink removeComponentFlowNodeLink(String sourceNodeId, String targetNodeId) {
        Iterator<ComponentFlowNodeLink> i = componentFlowNodeLinks.iterator();
        while (i.hasNext()) {
            ComponentFlowNodeLink link = i.next();
            if (link.getData().getSourceNodeId().equals(sourceNodeId)
                    && link.getData().getTargetNodeId().equals(targetNodeId)) {
                i.remove();
                return link;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return getData().getVersionName();
    }

}