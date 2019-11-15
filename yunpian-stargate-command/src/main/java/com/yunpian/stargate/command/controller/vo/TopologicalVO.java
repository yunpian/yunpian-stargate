package com.yunpian.stargate.command.controller.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA. User: ZhouKaifan Date:2019/11/11 Time:4:04 PM
 */
public class TopologicalVO {

  Map<String, TopologicalNodeVO> nodes = new HashMap<>();
  Set<TopologicalLinkVO> links = new HashSet<>();

  public List<TopologicalNodeVO> getNodes() {
    return new ArrayList<>(nodes.values());
  }

  public void setNodes(List<TopologicalNodeVO> nodes) {
    for (TopologicalNodeVO topologicalNodeVO : nodes) {
      addNode(topologicalNodeVO);
    }
  }

  public Set<TopologicalLinkVO> getLinks() {
    return links;
  }

  public void setLinks(Set<TopologicalLinkVO> links) {
    this.links = links;
  }

  public void addLink(TopologicalLinkVO link) {
    if (link != null) {
      this.links.add(link);
    }
  }

  public void addNode(TopologicalNodeVO node) {
    if (node != null) {
      TopologicalNodeVO topologicalNodeVO = this.nodes.get(node.getName());
      if (topologicalNodeVO == null) {
        node.setSymbolSize(node.getSymbolSize() + 15);
        this.nodes.put(node.getName(), node);
      } else {
        int symbolSize = topologicalNodeVO.getSymbolSize();
        int value = topologicalNodeVO.getValue();
        topologicalNodeVO.setSymbolSize(node.getSymbolSize() + symbolSize);
        topologicalNodeVO.setValue(node.getValue() + value);
        this.nodes.put(topologicalNodeVO.getName(), topologicalNodeVO);
      }
    }
  }

}
