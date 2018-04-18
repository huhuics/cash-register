package cn.cash.register.dao.domain;

import java.util.Set;

/**
 * GoodsCategory结点
 * @author HuHui
 * @version $Id: GoodsCategoryNode.java, v 0.1 2018年4月17日 下午7:33:14 HuHui Exp $
 */
public class GoodsCategoryNode extends BaseDomain {

    /**  */
    private static final long      serialVersionUID = 4096081926736292660L;

    private Long                   id;

    private String                 categoryName;

    private Long                   parentId;

    private String                 key;

    private Integer                level;

    /** 子结点集合 */
    private Set<GoodsCategoryNode> childrend        = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<GoodsCategoryNode> getChildrend() {
        return childrend;
    }

    public void setChildrend(Set<GoodsCategoryNode> childrend) {
        this.childrend = childrend;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}